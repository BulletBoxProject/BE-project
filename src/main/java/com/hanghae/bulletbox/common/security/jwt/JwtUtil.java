package com.hanghae.bulletbox.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;

import java.security.Key;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@PropertySource("classpath:application-security.properties")
@RequiredArgsConstructor
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    // 토큰 만료시간 1시간(분 * 초 * 밀리 sec)
    // 작업의 편리함을 위해 토큰 만료시간 6시간으로 설정
    private static final Long TOKEN_TIME = 6 * 30 * 60 * 1000L;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    // JWT SecretKey
    @Value("${jwt.secret.key.access}")
    private String accessTokenSecretKey;
    private Key accessTokenKey;

    // 암호화 알고리즘 설정
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] accessTokenBytes = Base64.getDecoder().decode(accessTokenSecretKey); // JWT 토큰 값을 Base64 형식으로 디코딩
        accessTokenKey = Keys.hmacShaKeyFor(accessTokenBytes); // key 에 삽입
    }

    // Token 분해 Method
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); // AccessToken value 가져옴
        // bearerToken 값이 존재하고, bearerToken 값이 "Bearer " 로 시작하면
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7); // "Bearer " 이후의 값을 리턴
        }

        return null;
    }

    // AccessToken 생성
    public String createToken(String email) {
        Date date = new Date();

        return BEARER_PREFIX + // BEARER 앞에 붙여주기
                Jwts.builder()
                        .setSubject(email) // subject 부분에 email 넣기
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 토큰 만료 시간 설정
                        .setIssuedAt(date) // 토큰 생성 시간
                        .signWith(accessTokenKey, signatureAlgorithm) // AccessToken 알고리즘 적용
                        .compact(); // URL-Safe string Token 생성
    }

    // AccessToken 검증
    public boolean validateAccessToken(String accessToken) {
        try {
            // String 형태인 토큰을 Thread-safe 하게 parse 하기 위해 AccessToken 가져와 JWS 로 파싱
            Jwts.parserBuilder().setSigningKey(accessTokenKey).build().parseClaimsJws(accessToken);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid Access JWT signature, 유효하지 않는 Access JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired Access JWT, 만료된 Access JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported Access JWT, 지원되지 않는 Access JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("Access JWT claims is empty, 잘못된 Access JWT 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(accessTokenKey).build().parseClaimsJws(token).getBody();
    }

    // Authentication 객체 생성
    public Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email); // 이메일을 통한 사용자 조회
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}