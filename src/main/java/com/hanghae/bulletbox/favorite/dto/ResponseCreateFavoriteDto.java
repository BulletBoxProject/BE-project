package com.hanghae.bulletbox.favorite.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "루틴 생성 응답 Dto")
public class ResponseCreateFavoriteDto {

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
    private ResponseCreateFavoriteDto(Long favoriteId, String favoriteContent, Long categoryId, String categoryName, String categoryColor, List<FavoriteMemoDto> favoriteMemos) {
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.favoriteMemos = favoriteMemos;
    }


    public static ResponseCreateFavoriteDto toResponseCreateFavoriteDto(FavoriteDto favoriteDto, List<FavoriteMemoDto> favoriteMemos) {
        Long favoriteId = favoriteDto.getFavoriteId();
        String favoriteContent = favoriteDto.getFavoriteContent();
        Long categoryId = favoriteDto.getCategoryId();
        String categoryColor = favoriteDto.getCategoryColor();
        String categoryName = favoriteDto.getCategoryName();


        return ResponseCreateFavoriteDto.builder()
                .favoriteMemos(favoriteMemos)
                .favoriteContent(favoriteContent)
                .favoriteId(favoriteId)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .categoryName(categoryName)
                .build();
    }
}
