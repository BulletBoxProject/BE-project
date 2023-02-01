package com.hanghae.bulletbox.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "ResponseCreateCategoryDto", description = "카테고리 생성 응답 DTO")
public class ResponseCreateCategoryDto {

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "직장", type = "String")
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#828282", type = "String")
    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseCreateCategoryDto(Long categoryId, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public static ResponseCreateCategoryDto toResponseCreateCategoryDto(CategoryDto categoryDto) {
        Long categoryId = categoryDto.getCategoryId();
        String categoryName = categoryDto.getCategoryName();
        String categoryColor = categoryDto.getCategoryColor();

        return ResponseCreateCategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }
}
