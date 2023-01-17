package com.hanghae.bulletbox.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "ResponseDeleteCategoryDto", description = "카테고리 삭제 응답 DTO")
public class ResponseDeleteCategoryDto {

    @Schema(description = "카테고리 id", example = "1", type = "Long")
    private Long categoryId;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseDeleteCategoryDto(Long categoryId) {
        this.categoryId = categoryId;
    }

    public static ResponseDeleteCategoryDto toResponseDeleteCategoryDto(Long categoryId) {
        return ResponseDeleteCategoryDto.builder()
                .categoryId(categoryId)
                .build();
    }
}
