package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.SignupDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.mapper.MemberMapper;
import com.hanghae.bulletbox.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupDto signupDto){
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();

        String encodedPassword = passwordEncoder.encode(password);

        Member member = MemberMapper.toMember(signupDto, encodedPassword);
        memberRepository.save(member);
    }
}
