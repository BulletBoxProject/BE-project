package com.hanghae.bulletbox.favorite.dto;

import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoritePageDto {

    private MemberDto memberDto;

    private Long favoriteId;

    private String favoriteContent;

    private List<FavoriteMemoDto> favoriteMemos;

    private Long categoryId;

    private String categoryName;

    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private FavoritePageDto(MemberDto memberDto, Long favoriteId, String favoriteContent, List<FavoriteMemoDto> favoriteMemos, Long categoryId, String categoryName, String categoryColor) {
        this.memberDto = memberDto;
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.favoriteMemos = favoriteMemos;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public static FavoritePageDto toFavoritePageDto(RequestCreateFavoriteTodoDto requestCreateFavoriteTodoDto, MemberDto memberDto) {

        String favoriteContent = requestCreateFavoriteTodoDto.getFavoriteContent();
        List<FavoriteMemoDto> favoriteMemos = requestCreateFavoriteTodoDto.getFavoriteMemos();
        Long categoryId = requestCreateFavoriteTodoDto.getCategoryId();
        String categoryName = requestCreateFavoriteTodoDto.getCategoryName();
        String categoryColor = requestCreateFavoriteTodoDto.getCategoryColor();

        return FavoritePageDto.builder()
                .memberDto(memberDto)
                .favoriteContent(favoriteContent)
                .favoriteMemos(favoriteMemos)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();

    }

    public static FavoritePageDto toFavoritePageDto(MemberDto memberDto) {
        return FavoritePageDto.builder()
                .memberDto(memberDto)
                .build();
    }
}
