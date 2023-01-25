package com.hanghae.bulletbox.favorite.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.RequestCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoriteTodoPageDto;
import com.hanghae.bulletbox.favorite.service.FavoritePageService;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoritePageService favoritePageService;

    @PostMapping
    public Response<ResponseCreateFavoriteTodoDto> createFavoriteTodo(@RequestBody RequestCreateFavoriteTodoDto requestCreateFavoriteTodoDto,
                                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(requestCreateFavoriteTodoDto, memberDto);

        ResponseCreateFavoriteTodoDto responseCreateFavoriteTodoDto = favoritePageService.createFavoriteTodo(favoritePageDto);

        return Response.success(201, "자주 쓰는 할 일 생성을 성공하였습니다.", responseCreateFavoriteTodoDto);
    }

    @GetMapping
    public Response<ResponseShowFavoriteTodoPageDto> showFavoriteTodoPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(memberDto);

        ResponseShowFavoriteTodoPageDto responseShowFavoriteTodoPageDto = favoritePageService.showFavoriteTodoPage(favoritePageDto);

        return Response.success(200, "자주 쓰는 할 일 페이지 조회를 성공했습니다.", responseShowFavoriteTodoPageDto);
    }
}
