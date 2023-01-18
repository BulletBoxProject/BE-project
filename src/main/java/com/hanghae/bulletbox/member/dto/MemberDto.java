package com.hanghae.bulletbox.member.dto;

import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "회원 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @Schema(description = "회원 인덱스용 id", example = "1", type = "Long")
    private Long memberId;

    @Schema(description = "이메일", example = "email@email.com", type = "String")
    @NotNull
    private String email;

    @Schema(description = "닉네임", example = "닉네임", type = "String")
    private String nickname;

    @Schema(description = "비밀번호", type = "String", example = "a!1234567", minLength = 8, maxLength = 25)
    private String password;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberDto(Long memberId, String email, String nickname, String password) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    // Member를 MemberDto로
    public static MemberDto toMemberDto(Member member){
        Long memberId = member.getMemberId();
        String email = member.getEmail();
        String password = member.getPassword();
        String nickname = member.getNickname();

        return MemberDto.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

    // 인증객체 userDetails의 Member를 MemberDto로
    public static MemberDto toMemberDto(UserDetailsImpl userDetails){
        Member member = userDetails.getMember();
        return MemberDto.toMemberDto(member);
    }

    public static MemberDto toMemberDto(RequestSignupDto requestSignupDto) {
        String email = requestSignupDto.getEmail();
        String nickname = requestSignupDto.getNickname();
        String password = requestSignupDto.getPassword();

        return MemberDto.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    public static MemberDto toMemberDto(RequestLoginDto requestLoginDto) {
        String email = requestLoginDto.getEmail();
        String password = requestLoginDto.getPassword();

        return MemberDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
