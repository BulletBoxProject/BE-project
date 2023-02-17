package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.common.redis.RedisUtil;
import com.hanghae.bulletbox.common.security.jwt.JwtUtil;
import com.hanghae.bulletbox.member.dto.MemberDto;

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
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_MATCH_REFRESH_TOKEN;
import static com.hanghae.bulletbox.common.security.jwt.JwtUtil.AUTHORIZATION_ACCESS;
import static com.hanghae.bulletbox.common.security.jwt.JwtUtil.AUTHORIZATION_REFRESH;

@RequiredArgsConstructor
@Service
public abstract class AuthService implements MemberFacade {

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final RedisUtil redisUtil;

    private String email;

    private final MemberService memberService;

    // 회원가입
    @Override
    @Transactional
    public void signup(MemberDto memberDto) {

        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        memberDto.setPassword(encodedPassword);

        memberService.save(memberDto);
    }

    @Override
    @Transactional(readOnly = true)
    public void login(MemberDto memberDto, HttpServletResponse response) {

        String email = memberDto.getEmail();
        String password = memberDto.getPassword();

        MemberDto loginMemberDto = memberService.findDtoByEmail(email);

        if (!passwordEncoder.matches(password, loginMemberDto.getPassword())) {
            throw new NoSuchElementException(DIFFERENT_PASSWORD_MSG.getMsg());
        }

        issueTokens(response, email);
    }

    // 토큰 발급
    @Override
    public void issueTokens(HttpServletResponse response, String email) {

        String accessToken = jwtUtil.createAccessToken(email);
        String refreshToken = jwtUtil.createRefreshToken();

        response.addHeader(AUTHORIZATION_ACCESS, accessToken);
        response.addHeader(AUTHORIZATION_REFRESH, refreshToken);

        redisUtil.setDataExpire(email, refreshToken, 2 * 60 * 60 * 1000L);
    }

    @Override
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

    // 체험하기 로그인
    @Override
    @Transactional
    public void testLogin(HttpServletResponse response) {

        email = createEmail();
        String nickname = "체험하기 계정";
        String password = "TestPassword";

        MemberDto randomMemberDto = MemberDto.toMemberDto(email, nickname, password);

        memberService.save(randomMemberDto);

        issueTokens(response, email);
    }

    @Override
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
}
