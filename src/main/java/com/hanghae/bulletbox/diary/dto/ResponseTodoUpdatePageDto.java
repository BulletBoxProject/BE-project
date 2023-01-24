package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ResponseTodoUpdatePageDto {

    private List<CategoryDto> categories;

    private DailyTodoDto todo;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseTodoUpdatePageDto(List<CategoryDto> categories, DailyTodoDto todo) {
        this.categories = categories;
        this.todo = todo;
    }
}
