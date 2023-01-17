package com.hanghae.bulletbox.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.member.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDto {

    private Long categoryId;

    @JsonIgnore
    private Member member;

    private String categoryName;

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
}
