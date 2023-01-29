package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseShowTodoCreatePageDto {

    private List<CategoryDto> categories;

    private Long year;

    private Long month;

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
