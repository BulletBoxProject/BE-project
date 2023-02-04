package com.hanghae.bulletbox.favorite.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "루틴 생성 요청 Dto")
public class RequestCreateFavoriteTodoDto {

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
    private RequestCreateFavoriteTodoDto(String favoriteContent, List<FavoriteMemoDto> favoriteMemos, Long categoryId, String categoryName, String categoryColor) {
        this.favoriteContent = favoriteContent;
        this.favoriteMemos = favoriteMemos;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }
}
