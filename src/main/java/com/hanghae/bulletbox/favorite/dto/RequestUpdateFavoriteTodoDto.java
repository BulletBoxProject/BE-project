package com.hanghae.bulletbox.favorite.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUpdateFavoriteTodoDto {

    private String favoriteContent;

    private List<FavoriteMemoDto> favoriteMemos;

    private Long categoryId;

    private String categoryName;

    private String categoryColor;
}
