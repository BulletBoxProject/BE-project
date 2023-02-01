package com.hanghae.bulletbox.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "ResponseShowCategoryDto", description = "카테고리 목록 조회 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowCategoryDto {

    @Schema(description = "CategoryDto로부터 받은 데이터를 담는 변수")
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
