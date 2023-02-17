package com.hanghae.bulletbox.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanghae.bulletbox.member.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface MemberFacade {

    @Transactional
    void signup(MemberDto memberDto);

    @Transactional(readOnly = true)
    void login(MemberDto memberDto, HttpServletResponse response);

    void issueTokens(HttpServletResponse response, String email);

    void reissueToken(HttpServletRequest request, HttpServletResponse response);

    @Transactional
    void testLogin(HttpServletResponse response);

    String createEmail();

    @Transactional
    void googleLogin(String code, HttpServletResponse response);

    MemberDto getGoogleMemberInfo(String accessToken) throws JsonProcessingException;

    @Transactional
    void kakaoLogin(String code, HttpServletResponse response);

    String getToken(String code) throws JsonProcessingException;

    MemberDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException;

    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    String createCode();

    @Transactional(readOnly = true)
    void sendSimpleMessage(String email) throws Exception;

    @Transactional
    void verifyCode(String email, String code);
}
