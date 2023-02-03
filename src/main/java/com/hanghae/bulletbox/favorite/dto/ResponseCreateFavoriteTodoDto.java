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

    private Long categoryId;

    private String categoryName;

    private String categoryColor;

    private List<FavoriteMemoDto> favoriteMemos;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseCreateFavoriteTodoDto(Long favoriteId, String favoriteContent, Long categoryId, String categoryName, String categoryColor, List<FavoriteMemoDto> favoriteMemos) {
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.favoriteMemos = favoriteMemos;
    }


    public static ResponseCreateFavoriteTodoDto toResponseCreateFavoriteTodoDto(FavoriteDto favoriteDto, List<FavoriteMemoDto> favoriteMemos) {
        Long favoriteId = favoriteDto.getFavoriteId();
        String favoriteContent = favoriteDto.getFavoriteContent();
        Long categoryId = favoriteDto.getCategoryId();
        String categoryColor = favoriteDto.getCategoryColor();
        String categoryName = favoriteDto.getCategoryName();


        return ResponseCreateFavoriteTodoDto.builder()
                .favoriteMemos(favoriteMemos)
                .favoriteContent(favoriteContent)
                .favoriteId(favoriteId)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .categoryName(categoryName)
                .build();
    }
}
