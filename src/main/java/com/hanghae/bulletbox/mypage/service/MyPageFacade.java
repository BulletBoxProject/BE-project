package com.hanghae.bulletbox.mypage.service;

import com.hanghae.bulletbox.mypage.dto.MyPageDto;
import com.hanghae.bulletbox.mypage.dto.ResponseShowMyPageDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageFacade {

    public ResponseShowMyPageDto showMyPage(MyPageDto myPageDto) {

        String email = myPageDto.getEmail();
        String nickname = myPageDto.getNickname();

        return ResponseShowMyPageDto.toResponseShowMyPageDto(email, nickname);
    }
}
