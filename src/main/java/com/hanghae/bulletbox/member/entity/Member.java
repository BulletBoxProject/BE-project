package com.hanghae.bulletbox.member.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.type.SocialTypeEnum;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Setter
    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private SocialTypeEnum socialType;

    @Setter
    @Column(nullable = false)
    private Boolean firstLogin;


    @Builder(access = AccessLevel.PRIVATE)
    private Member(Long memberId, String email, String nickname, String password, SocialTypeEnum socialType, Boolean firstLogin) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.socialType = socialType;
        this.firstLogin = firstLogin;
    }

    // MemberDto를 Member로 변환
    public static Member toMember(MemberDto memberDto) {
        Long memberId = memberDto.getMemberId();
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();
        String nickname = memberDto.getNickname();
        SocialTypeEnum socialType = memberDto.getSocialTypeEnum();
        Boolean firstLogin = memberDto.getFirstLogin();

        return Member.builder()
                .memberId(memberId)
                .email(email)
                .nickname(nickname)
                .password(password)
                .socialType(socialType)
                .firstLogin(firstLogin)
                .build();
    }

    public  Member(String email, SocialTypeEnum socialType) {
        this.email = email;
        this.socialType = socialType;
    }

    public void updateFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
