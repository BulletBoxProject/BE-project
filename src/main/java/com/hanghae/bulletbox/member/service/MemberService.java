package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.common.redis.RedisUtil;
import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.ResponseLoginDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import io.jsonwebtoken.Claims;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.NoSuchElementException;
import java.util.Random;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DIFFERENT_PASSWORD_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_EMAIL_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_EMAIL_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_MATCH_REFRESH_TOKEN;
import static com.hanghae.bulletbox.common.security.jwt.JwtUtil.AUTHORIZATION_ACCESS;
import static com.hanghae.bulletbox.common.security.jwt.JwtUtil.AUTHORIZATION_REFRESH;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final RedisUtil redisUtil;

    private String email;

    private void checkDuplicatedEmail(String email) {

        memberRepository.findByEmail(email).ifPresent(
                m -> {
                    throw new IllegalArgumentException(DUPLICATE_EMAIL_MSG.getMsg());
                });
    }

    @Transactional
    public void signup(MemberDto memberDto) {

        String email = memberDto.getEmail();
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        checkDuplicatedEmail(email);

        Member member = Member.toMember(memberDto.getEmail(), memberDto.getNickname(), encodedPassword);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public ResponseLoginDto login(MemberDto memberDto, HttpServletResponse response) {

        String email = memberDto.getEmail();
        String password = memberDto.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(NOT_FOUND_EMAIL_MSG.getMsg())
        );

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new NoSuchElementException(DIFFERENT_PASSWORD_MSG.getMsg());
        }

        issueTokens(response, memberDto.getEmail());

        return ResponseLoginDto.toResponseLoginDto(false);
    }

    @Transactional
    public void issueTokens(HttpServletResponse response, String email) {

        String accessToken = jwtUtil.createAccessToken(email);
        String refreshToken = jwtUtil.createRefreshToken();

        response.addHeader(AUTHORIZATION_ACCESS, accessToken);
        response.addHeader(AUTHORIZATION_REFRESH, refreshToken);

        redisUtil.setDataExpire(email, refreshToken, 2 * 60 * 60 * 1000L);
    }

    @Transactional
    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshTokenFromRequest = request.getHeader(AUTHORIZATION_REFRESH); // 요청헤더에서 온 RTK
        String token = jwtUtil.resolveToken(request, AUTHORIZATION_ACCESS); // 요청헤더에서 온 ATK(bearer 제외)
        Claims info = jwtUtil.getUserInfoFromToken(token, true); // ATK에서 body가지고 옴
        String email = info.getSubject(); // 가지고온 body에서 subject 빼오기 = email
        String refreshTokenFromRedis = redisUtil.getData(email);

        if (!refreshTokenFromRequest.equals(refreshTokenFromRedis)) {
            throw new IllegalArgumentException(NOT_MATCH_REFRESH_TOKEN.getMsg());
        }

        jwtUtil.validateRefreshToken(request, email);

        issueTokens(response, email);
    }

    @Transactional
    public ResponseLoginDto testLogin(HttpServletResponse response, MemberDto memberDto) {

        email = createEmail();
        String nickname = "체험하기 계정";
        String password = "TestPassword";
        Member member = Member.toMember(email, nickname, password);

        memberRepository.save(member);

        issueTokens(response, email);

        return ResponseLoginDto.toResponseLoginDto(false);
    }

    public String createEmail() {

        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) (int) random.nextInt(26) + 97);
                case 1 -> key.append((char) (int) random.nextInt(26) + 65);
                case 2 -> key.append(random.nextInt(9));
            }
        }

        return email = key.toString();
    }

    // 더티 체킹으로 첫 로그인 여부 반영
    @Transactional
    public void updateFirstLogin(MemberDto memberDto) {

        Long memberId = memberDto.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg()));

        member.updateFirstLogin(false);
    }
}
