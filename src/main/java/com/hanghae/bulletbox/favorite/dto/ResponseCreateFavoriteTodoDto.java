package com.hanghae.bulletbox.favorite.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCreateFavoriteTodoDto {

    private Long favoriteId;

    private String favoriteContent;

    private List<FavoriteMemoDto> favoriteMemos;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseCreateFavoriteTodoDto(Long favoriteId, String favoriteContent, List<FavoriteMemoDto> favoriteMemos) {
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.favoriteMemos = favoriteMemos;
    }

    public static ResponseCreateFavoriteTodoDto toResponseCreateFavoriteTodoDto(Long favoriteId, String favoriteContent, List<FavoriteMemoDto> favoriteMemos) {

        return ResponseCreateFavoriteTodoDto.builder()
                .favoriteMemos(favoriteMemos)
                .favoriteContent(favoriteContent)
                .favoriteId(favoriteId)
                .build();
    }
}
