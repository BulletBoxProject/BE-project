package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DIFFERENT_PASSWORD_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_EMAIL_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_EMAIL_MSG;
import static com.hanghae.bulletbox.common.security.jwt.JwtUtil.AUTHORIZATION_HEADER;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private void checkDuplicatedEmail(String email){
        memberRepository.findByEmail(email).ifPresent(
                m -> {throw new IllegalArgumentException(DUPLICATE_EMAIL_MSG.getMsg());
                });
    }

    @Transactional
    public void signup(MemberDto memberDto){
        String email = memberDto.getEmail();
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        checkDuplicatedEmail(email);

        Member member = Member.toMember(memberDto.getEmail(), memberDto.getNickname(), encodedPassword);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public void login(MemberDto memberDto, HttpServletResponse httpServletResponse){
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(NOT_FOUND_EMAIL_MSG.getMsg())
        );

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new NoSuchElementException(DIFFERENT_PASSWORD_MSG.getMsg());
        }

        String accessToken = jwtUtil.createToken(email);

        httpServletResponse.addHeader(AUTHORIZATION_HEADER, accessToken);
    }
}
