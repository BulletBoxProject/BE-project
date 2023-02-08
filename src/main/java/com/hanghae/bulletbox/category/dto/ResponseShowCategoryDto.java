package com.hanghae.bulletbox.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "카테고리 목록 조회 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowCategoryDto {

    @Schema(description = "카테고리 목록 조회 응답 변수", type = "List")
    private List<CategoryDto> categories;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowCategoryDto(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public static ResponseShowCategoryDto toResponseShowCategoryDto(List<CategoryDto> categoryDtoList) {
        return ResponseShowCategoryDto.builder()
                .categories(categoryDtoList)
                .build();
    }
}
