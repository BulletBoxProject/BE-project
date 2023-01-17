package com.hanghae.bulletbox.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "CategoryDto", description = "CategoryPageService 범용 DTO")
public class CategoryDto {

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "Member 참조", type = "Member")
    @JsonIgnore
    private Member member;

    @Schema(description = "카테고리 이름", example = "직장", type = "String")
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#828282", type = "String")
    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private CategoryDto(Long categoryId, Member member, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.member = member;
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
                .categoryColor(categoryColor)
                .build();
    }

    public static CategoryDto toCategoryDto(Member member) {
        return CategoryDto.builder()
                .member(member)
                .build();
    }

    public static CategoryDto toCategoryDto(String categoryName, String categoryColor, Member member) {
        return CategoryDto.builder()
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .member(member)
                .build();
    }

    public static CategoryDto toCategoryDto(Member member, Long categoryId, String categoryName, String categoryColor) {
        return CategoryDto.builder()
                .member(member)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }
}
