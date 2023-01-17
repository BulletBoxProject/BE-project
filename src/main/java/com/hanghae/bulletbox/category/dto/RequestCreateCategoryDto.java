package com.hanghae.bulletbox.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "RequestCreateCategoryDto", description = "카테고리 생성 요청 DTO")
public class RequestCreateCategoryDto {

    @Schema(description = "카테고리 이름", example = "직장", type = "String")
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#000000", type = "String")
    private String categoryColor;
}
