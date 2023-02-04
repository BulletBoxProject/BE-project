package com.hanghae.bulletbox.favorite.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "루틴 페이지 조회 응답 Dto")
public class ResponseShowFavoritePageDto {

    @Schema(description = "루틴 페이지 조회 응답 변수", type = "List")
    private List<FavoriteDto> favorites;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowFavoritePageDto(List<FavoriteDto> favorites) {
        this.favorites = favorites;
    }

    public static ResponseShowFavoritePageDto toResponseShowFavoritePageDto(List<FavoriteDto> favoriteDtoList) {
        return ResponseShowFavoritePageDto.builder()
                .favorites(favoriteDtoList)
                .build();
    }
}
