package com.hanghae.bulletbox.favorite.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowFavoriteTodoPageDto {

    private List<FavoriteDto> favorites;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowFavoriteTodoPageDto(List<FavoriteDto> favorites) {
        this.favorites = favorites;
    }

    public static ResponseShowFavoriteTodoPageDto toResponseShowFavoriteTodoPageDto(List<FavoriteDto> favoriteDtoList) {
        return ResponseShowFavoriteTodoPageDto.builder()
                .favorites(favoriteDtoList)
                .build();
    }
}
