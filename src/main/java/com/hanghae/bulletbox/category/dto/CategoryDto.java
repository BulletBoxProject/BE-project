package com.hanghae.bulletbox.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDto {

    private Long categoryId;

    private Long memberId;

    private String categoryName;

    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private CategoryDto(Long categoryId, Long memberId, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public static CategoryDto toCategoryDto(Long categoryId, String categoryName) {
        return CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }

    public static CategoryDto toCategoryDto(Long categoryId, String categoryName, String categoryColor) {
        return CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }

    public static CategoryDto toCategoryDto(Long memberId) {
        return CategoryDto.builder()
                .memberId(memberId)
                .build();
    }
}
