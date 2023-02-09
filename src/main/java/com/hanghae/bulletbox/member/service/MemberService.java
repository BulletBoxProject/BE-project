package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.*;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    // 더티 체킹으로 첫 로그인 여부 반영
    @Transactional(readOnly = true)
    public void updateFirstLogin(MemberDto memberDto) {

        Long memberId = memberDto.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg()));

        member.updateFirstLogin(false);
    }

    // 이메일 중복 확인
    @Transactional(readOnly = true)
    protected boolean checkDuplicatedEmail(String email) {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        return memberOptional.isPresent();
    }

    // 회원 저장
    @Transactional
    public void save(MemberDto memberDto) {
        Member member = Member.toMember(memberDto);

        String email = member.getEmail();

        if(checkDuplicatedEmail(email)){
            throw new IllegalArgumentException(DUPLICATE_EMAIL_MSG.getMsg());
        }

        member.setFirstLogin(true);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findDtoByEmail(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_EMAIL_MSG.getMsg()));

        MemberDto memberDto = MemberDto.toMemberDto(member);

        return memberDto;
    }

    // 소셜 로그인 회원가입
    @Transactional
    public void saveSocial(MemberDto memberDto) {
        String email = memberDto.getEmail();

        if(!checkDuplicatedEmail(email)){
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);

            memberDto.setPassword(encodedPassword);
            save(memberDto);
        }
    }
}
