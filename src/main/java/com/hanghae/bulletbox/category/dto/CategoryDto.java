package com.hanghae.bulletbox.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDto {

    private Long categoryId;
    private String categoryName;
    private String categoryColor;

    public CategoryDto(Long categoryId, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    // Entity -> Dto
    public static CategoryDto toCategoryDto(Long categoryId, String categoryName, String categoryColor) {
        return new CategoryDto(categoryId, categoryName, categoryColor);
    }
}
