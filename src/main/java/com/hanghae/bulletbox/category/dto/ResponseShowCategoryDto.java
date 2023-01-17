package com.hanghae.bulletbox.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowCategoryDto {

    private List<CategoryDto> categories;

    private ResponseShowCategoryDto(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public static ResponseShowCategoryDto toResponseShowCategoryDto(List<CategoryDto> categoryDtoList) {
        return new ResponseShowCategoryDto(categoryDtoList);
    }
}
