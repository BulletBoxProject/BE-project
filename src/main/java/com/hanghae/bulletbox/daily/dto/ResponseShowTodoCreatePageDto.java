package com.hanghae.bulletbox.daily.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "할 일 생성 페이지 응답 Dto")
public class ResponseShowTodoCreatePageDto {

    @Schema(description = "카테고리 정보 담는 변수", type = "List")
    private List<CategoryDto> categories;

    @Schema(description = "연도", example = "2023", type = "Long")
    private Long year;

    @Schema(description = "월", example = "12", type = "Long")
    private Long month;

    @Schema(description = "날짜", example = "12", type = "Long")
    private Long day;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowTodoCreatePageDto(List<CategoryDto> categories, Long year, Long month, Long day) {
        this.categories = categories;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static ResponseShowTodoCreatePageDto toResponseShowTodoCreatePageDto(List<CategoryDto> categories, Long year, Long month, Long day) {
        return ResponseShowTodoCreatePageDto.builder()
                .categories(categories)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
