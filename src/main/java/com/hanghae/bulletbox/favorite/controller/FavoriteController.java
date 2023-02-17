package com.hanghae.bulletbox.favorite.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.RequestCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.RequestUpdateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseUpdateFavoriteDto;
import com.hanghae.bulletbox.favorite.service.FavoriteFacade;
import com.hanghae.bulletbox.favorite.service.FavoritePageService;
import com.hanghae.bulletbox.member.dto.MemberDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Favorite", description = "루틴 API")
public class FavoriteController {

    private final FavoriteFacade favoriteFacade;

    @Operation(tags = {"Favorite"}, summary = "루틴 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "루틴 생성을 성공했습니다."),
            @ApiResponse(responseCode = "404-1", description = "존재하지 않는 사용자입니다."),
            @ApiResponse(responseCode = "404-2", description = "루틴이 존재하지 않습니다.")
    })
    @PostMapping
    public Response<ResponseCreateFavoriteDto> createFavoriteTodo(@RequestBody RequestCreateFavoriteTodoDto requestCreateFavoriteTodoDto,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(requestCreateFavoriteTodoDto, memberDto);

        ResponseCreateFavoriteDto responseCreateFavoriteTodoDto = favoriteFacade.createFavorite(favoritePageDto);

        return Response.success(201, "루틴 생성을 성공하였습니다.", responseCreateFavoriteTodoDto);
    }

    @Operation(tags = {"Favorite"}, summary = "루틴 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "루틴 페이지 조회를 성공했습니다."),
            @ApiResponse(responseCode = "404-1", description = "존재하지 않는 사용자입니다."),
            @ApiResponse(responseCode = "404-2", description = "루틴이 존재하지 않습니다.")
    })
    @GetMapping
    public Response<ResponseShowFavoritePageDto> showFavoriteTodoPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(memberDto);

        ResponseShowFavoritePageDto responseShowFavoritePageDto = favoriteFacade.showFavoritePage(favoritePageDto);

        return Response.success(200, "루틴 페이지 조회를 성공했습니다.", responseShowFavoritePageDto);
    }

    @Operation(tags = {"Favorite"}, summary = "루틴 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "루틴 삭제를 성공했습니다."),
            @ApiResponse(responseCode = "404-1", description = "존재하지 않는 사용자입니다."),
            @ApiResponse(responseCode = "404-2", description = "루틴이 존재하지 않습니다.")
    })
    @DeleteMapping("/{favoriteId}")
    public Response<?> deleteFavoriteTodo(@PathVariable Long favoriteId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(memberDto, favoriteId);

        favoriteFacade.deleteFavoriteTodo(favoritePageDto);

        return Response.success(200, "루틴 삭제를 성공했습니다.", null);
    }

    @Operation(tags = {"Favorite"}, summary = "루틴 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "루틴 수정을 성공했습니다."),
            @ApiResponse(responseCode = "404-1", description = "존재하지 않는 사용자입니다."),
            @ApiResponse(responseCode = "404-2", description = "루틴이 존재하지 않습니다."),
            @ApiResponse(responseCode = "404-3", description = "카테고리가 존재하지 않습니다."),
    })
    @PutMapping("/{favoriteId}")
    public Response<ResponseUpdateFavoriteDto> updateFavoriteTodo(@PathVariable Long favoriteId,
                                                                  @RequestBody RequestUpdateFavoriteTodoDto requestUpdateFavoriteTodoDto,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        FavoritePageDto favoritePageDto = FavoritePageDto.toFavoritePageDto(favoriteId, requestUpdateFavoriteTodoDto, memberDto);

        ResponseUpdateFavoriteDto responseUpdateFavoriteDto = favoriteFacade.updateFavoriteTodo(favoritePageDto);

        return Response.success(200, "루틴 수정을 성공했습니다.", responseUpdateFavoriteDto);
    }
}
