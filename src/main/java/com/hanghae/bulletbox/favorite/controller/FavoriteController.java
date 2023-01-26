package com.hanghae.bulletbox.favorite.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.RequestCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.RequestUpdateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoriteTodoPageDto;
import com.hanghae.bulletbox.favorite.service.FavoritePageService;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        return Response.success(201, "루틴 생성을 성공하였습니다.", responseCreateFavoriteTodoDto);
    }

    @GetMapping
    public Response<ResponseShowFavoriteTodoPageDto> showFavoriteTodoPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(memberDto);

        ResponseShowFavoriteTodoPageDto responseShowFavoriteTodoPageDto = favoritePageService.showFavoriteTodoPage(favoritePageDto);

        return Response.success(200, "루틴 페이지 조회를 성공했습니다.", responseShowFavoriteTodoPageDto);
    }

    @DeleteMapping("/{favoriteId}")
    public Response<?> deleteFavoriteTodo(@PathVariable Long favoriteId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(memberDto, favoriteId);

        favoritePageService.deleteFavoriteTodo(favoritePageDto);

        return Response.success(200, "루틴 삭제를 성공했습니다.", null);
    }

    @PutMapping("/{favoriteId}")
    public Response<?> updateFavoriteTodo(@PathVariable Long favoriteId,
                                          @RequestBody RequestUpdateFavoriteTodoDto requestUpdateFavoriteTodoDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(favoriteId, requestUpdateFavoriteTodoDto, memberDto);

        favoritePageService.updateFavoriteTodo(favoritePageDto);

        return Response.success(200, "루틴 수정을 성공했습니다.", null);
    }
}
