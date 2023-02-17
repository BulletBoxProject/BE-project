package com.hanghae.bulletbox.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.SOCIAL_LOGIN_ERROR;
import static com.hanghae.bulletbox.member.type.SocialTypeEnum.KAKAO;

@Service
@RequiredArgsConstructor
public abstract class KakaoService implements MemberFacade {

    private final JwtUtil jwtUtil;

    private final MemberService memberService;

    @Value("${kakao.client.id}")
    private String kakaoClientId;
    @Value("${kakao.redirect.uri}")
    private String kakaoRedirectUri;

    @Override
    @Transactional
    public void kakaoLogin(String code, HttpServletResponse response) {

        try {
            String token = getToken(code);
            MemberDto loginMemberDto = getKakaoMemberInfo(token);
            String email = loginMemberDto.getEmail();

            memberService.saveSocial(loginMemberDto);

            response.addHeader(JwtUtil.AUTHORIZATION_ACCESS, jwtUtil.createAccessToken(email));
            response.addHeader(JwtUtil.AUTHORIZATION_REFRESH, jwtUtil.createRefreshToken());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(SOCIAL_LOGIN_ERROR.getMsg());
        }
    }

    @Override
    public String getToken(String code) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", kakaoRedirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    @Override
    public MemberDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoMemberInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoMemberInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String id = jsonNode.get("id").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        JsonNode jsonEmail = jsonNode.get("kakao_account").get("email");
        String email = jsonEmail == null ? id + "kakao.com" : jsonEmail.asText();

        MemberDto memberDto = MemberDto.toMemberDto(email, nickname, KAKAO);

        return memberDto;
    }
}
