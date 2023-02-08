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
import static com.hanghae.bulletbox.member.type.SocialTypeEnum.GOOGLE;


@Service
@RequiredArgsConstructor
public class GoogleService {

    private final JwtUtil jwtUtil;

    private final MemberService memberService;

    @Value("${app.google.clientId}")
    private String googleClientId;

    @Value("${app.google.client.secret}")
    private String googleClientSecret;

    @Value("${app.google.redirect.uri}")
    private String googleRedirectUrl;

    @Transactional
    public void googleLogin(String code, HttpServletResponse response) {

        try {
            String token = getToken(code);
            MemberDto loginMemberDto = getGoogleMemberInfo(token);
            String email = loginMemberDto.getEmail();

            memberService.saveSocial(loginMemberDto);

            response.addHeader(JwtUtil.AUTHORIZATION_ACCESS, jwtUtil.createAccessToken(email));
            response.addHeader(JwtUtil.AUTHORIZATION_REFRESH, jwtUtil.createRefreshToken());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(SOCIAL_LOGIN_ERROR.getMsg());
        }
    }

    private String getToken(String code) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", googleClientId);
        body.add("client_secret", googleClientSecret);
        body.add("redirect_uri", googleRedirectUrl);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://www.googleapis.com/oauth2/v4/token",
                HttpMethod.POST,
                googleTokenRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    private MemberDto getGoogleMemberInfo(String accessToken) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> googleMemberInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://www.googleapis.com/oauth2/v1/userinfo",
                HttpMethod.GET,
                googleMemberInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String id = jsonNode.get("id").asText();
        String nickname = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();

        MemberDto memberDto = MemberDto.toMemberDto(email, nickname, GOOGLE);

        return memberDto;
    }
}
