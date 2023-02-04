package com.hanghae.bulletbox.favorite.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUpdateFavoriteDto {

    private Long favoriteId;

    private String favoriteContent;

    private Long categoryId;

    private String categoryName;

    private String categoryColor;

    private List<FavoriteMemoDto> favoriteMemos;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseUpdateFavoriteDto(Long favoriteId, String favoriteContent, Long categoryId, String categoryName, String categoryColor, List<FavoriteMemoDto> favoriteMemos) {
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.favoriteMemos = favoriteMemos;
    }

    public static ResponseUpdateFavoriteDto toResponseUpdateFavoriteDto(FavoriteDto favoriteDto, List<FavoriteMemoDto> responseFavoriteMemoDtoList) {
        Long favoriteId = favoriteDto.getFavoriteId();
        String favoriteContent = favoriteDto.getFavoriteContent();
        Long categoryId = favoriteDto.getCategoryId();
        String categoryColor = favoriteDto.getCategoryColor();
        String categoryName = favoriteDto.getCategoryName();

        return ResponseUpdateFavoriteDto.builder()
                .favoriteMemos(responseFavoriteMemoDtoList)
                .favoriteContent(favoriteContent)
                .favoriteId(favoriteId)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .categoryName(categoryName)
                .build();
    }
}
