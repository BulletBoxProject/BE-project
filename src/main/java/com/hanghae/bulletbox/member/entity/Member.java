package com.hanghae.bulletbox.member.entity;

import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(Long memberId, String email, String nickname, String password) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    // MemberDto를 Member로 변환
    public static Member toMember(MemberDto memberDto){
        Long memberId = memberDto.getMemberId();
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();
        String nickname = memberDto.getNickname();

        if(memberId == null){
            throw new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg());
        }

        return Member.builder()
                .memberId(memberId)
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    public static Member toMember(String email, String nickname, String password) {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
