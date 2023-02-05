package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Schema(description = "할 일 수정 페이지 응답 Dto")
public class ResponseTodoUpdatePageDto {

    @Schema(description = "카테고리 정보 담는 변수", type = "List")
    private List<CategoryDto> categories;

    @Schema(description = "데일리 로그 할 일 Dto")
    private DailyTodoDto todo;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseTodoUpdatePageDto(List<CategoryDto> categories, DailyTodoDto todo) {
        this.categories = categories;
        this.todo = todo;
    }

    public static ResponseTodoUpdatePageDto toResponseTodoUpdatePageDto(List<CategoryDto> categories, DailyTodoDto dailyTodoDto){
        return ResponseTodoUpdatePageDto.builder()
                .categories(categories)
                .todo(dailyTodoDto)
                .build();
    }
}
