package com.hanghae.bulletbox.favorite.dto;

import com.hanghae.bulletbox.member.dto.MemberDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "루틴 서비스 범용 Dto")
public class FavoritePageDto {

    @Schema(description = "유저 Dto")
    private MemberDto memberDto;

    @Schema(description = "루틴 ID", example = "1", type = "Long")
    private Long favoriteId;

    @Schema(description = "루틴 내용", example = "루틴 내용", type = "String")
    private String favoriteContent;

    @Schema(description = "루틴 메모", type = "List")
    private List<FavoriteMemoDto> favoriteMemos;

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "직장", type = "String")
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#828282", type = "String")
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

    public static FavoritePageDto toFavoritePageDto(MemberDto memberDto, Long favoriteId) {
        return FavoritePageDto.builder()
                .memberDto(memberDto)
                .favoriteId(favoriteId)
                .build();
    }

    public static FavoritePageDto toFavoritePageDto(Long favoriteId, RequestUpdateFavoriteTodoDto requestUpdateFavoriteTodoDto, MemberDto memberDto) {

        String favoriteContent = requestUpdateFavoriteTodoDto.getFavoriteContent();
        List<FavoriteMemoDto> favoriteMemos = requestUpdateFavoriteTodoDto.getFavoriteMemos();
        Long categoryId = requestUpdateFavoriteTodoDto.getCategoryId();
        String categoryName = requestUpdateFavoriteTodoDto.getCategoryName();
        String categoryColor = requestUpdateFavoriteTodoDto.getCategoryColor();

        return FavoritePageDto.builder()
                .memberDto(memberDto)
                .favoriteId(favoriteId)
                .favoriteContent(favoriteContent)
                .favoriteMemos(favoriteMemos)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }
}
