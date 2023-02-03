package com.hanghae.bulletbox.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.ResponseLoginDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;
import com.hanghae.bulletbox.member.type.SocialTypeEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.SOCIAL_LOGIN_ERROR;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    @Value("${kakao.client.id}")
    private String kakaoClientId;
    @Value("${kakao.redirect.uri}")
    private String kakaoRedirectUri;


    @Transactional
    public ResponseLoginDto kakaoLogin(String code, HttpServletResponse response) {
        try {
            String token = getToken(code);
            Member loginMember = getKakaoMemberInfo(token);
            Member member = memberRepository.findByEmail(loginMember.getEmail()).orElse(null);

            if (member == null) {
                member = signupSocialMember(loginMember);
            }
            Boolean firstLogin = member.getFirstLogin();
            if (firstLogin = true) {
                firstLogin = false;
                member.socialUpdate(SocialTypeEnum.KAKAO);
                response.addHeader(JwtUtil.AUTHORIZATION_ACCESS, jwtUtil.createAccessToken(member.getEmail()));
                response.addHeader(JwtUtil.AUTHORIZATION_REFRESH, jwtUtil.createRefreshToken());
                return ResponseLoginDto.toResponseLoginDto(true);
            }
            member.socialUpdate(SocialTypeEnum.KAKAO);
            response.addHeader(JwtUtil.AUTHORIZATION_ACCESS, jwtUtil.createAccessToken(member.getEmail()));
            response.addHeader(JwtUtil.AUTHORIZATION_REFRESH, jwtUtil.createRefreshToken());
            return ResponseLoginDto.toResponseLoginDto(false);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(SOCIAL_LOGIN_ERROR.getMsg());
        }
    }

    private String getToken(String code)throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", kakaoRedirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
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

    private Member getKakaoMemberInfo(String accessToken) throws JsonProcessingException {
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

        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

        JsonNode jsonEmail = jsonNode.get("kakao_account")
                .get("email");
        String email = jsonEmail == null ? id + "kakao.com" : jsonEmail.asText();

        return new Member(email, nickname , SocialTypeEnum.KAKAO);
    }

    private Member signupSocialMember(Member socialMember) {
        String socialEmail = socialMember.getEmail();
        Member member = memberRepository.findByEmail(socialEmail).orElse(null);
        if (member == null) {
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);
            socialMember.setPassword(encodedPassword);
            socialMember.setFirstLogin(true);
            member = memberRepository.save(socialMember);
        }
        member.socialUpdate(SocialTypeEnum.KAKAO);

        return member;
    }
}
