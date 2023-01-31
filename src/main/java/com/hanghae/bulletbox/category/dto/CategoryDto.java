package com.hanghae.bulletbox.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.member.dto.MemberDto;
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
    private MemberDto memberDto;

    @Schema(description = "카테고리 이름", example = "직장", type = "String")
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#828282", type = "String")
    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private CategoryDto(Long categoryId, MemberDto memberDto, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.memberDto = memberDto;
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

    public static CategoryDto toCategoryDto(MemberDto memberDto) {
        return CategoryDto.builder()
                .memberDto(memberDto)
                .build();
    }

    public static CategoryDto toCategoryDto(String categoryName, String categoryColor, MemberDto memberDto) {
        return CategoryDto.builder()
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .memberDto(memberDto)
                .build();
    }

    public static CategoryDto toCategoryDto(MemberDto memberDto, Long categoryId, String categoryName, String categoryColor) {
        return CategoryDto.builder()
                .memberDto(memberDto)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }

    public static CategoryDto toCategoryDto(MemberDto memberDto, Long categoryId) {
        return CategoryDto.builder()
                .memberDto(memberDto)
                .categoryId(categoryId)
                .build();
    }
}
