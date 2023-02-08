package com.hanghae.bulletbox.common.security.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hanghae.bulletbox.common.response.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e){
            log.error("JWT 인증/인가 관련 이슈");
            e.printStackTrace();
            setErrorResponse(response, e);
        }
    }

    private void setErrorResponse(HttpServletResponse response, IllegalArgumentException e) throws IOException {
        // 1. Json Type으로 반환할 것 명시
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 2. HttpStatus 설정
        response.setStatus(401);
        // 3. error msg 생성
        Response<Object> fail = Response.fail(401, e.getMessage(), null);

        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, fail);
            os.flush();
        }
    }
}
