package com.hanghae.bulletbox.favorite.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCreateFavoriteTodoDto {

    private String favoriteContent;

    private List<FavoriteMemoDto> favoriteMemos;

    private Long categoryId;

    private String categoryName;

    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private RequestCreateFavoriteTodoDto(String favoriteContent, List<FavoriteMemoDto> favoriteMemos, Long categoryId, String categoryName, String categoryColor) {
        this.favoriteContent = favoriteContent;
        this.favoriteMemos = favoriteMemos;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }
}
