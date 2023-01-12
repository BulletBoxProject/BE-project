package com.hanghae.bulletbox.common.security.jwt;

import io.jsonwebtoken.Claims;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtUtil.validateAccessToken(token)) {
            // 핸들러 추가해야함.
            return;
        }

        // 사용자 인증
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        setAuthentication(claims.getSubject());
        filterChain.doFilter(request, response);
    }

    // 인증, 인가 설정
    private void setAuthentication(String email) {
        // security context 생성
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        // Authentication 생성
        Authentication authentication = jwtUtil.createAuthentication(email);
        // context 에 인증 객체 삽입
        context.setAuthentication(authentication);
        // security context holder 에 context 삽입
        SecurityContextHolder.setContext(context);
    }
}