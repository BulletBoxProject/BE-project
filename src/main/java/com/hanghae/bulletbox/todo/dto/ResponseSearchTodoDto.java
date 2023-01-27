package com.hanghae.bulletbox.todo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseSearchTodoDto {

    private List<SearchTodoDto> searches;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseSearchTodoDto(List<SearchTodoDto> searches) {
        this.searches = searches;
    }

    public static ResponseSearchTodoDto toResponseSearchTodoDto(List<SearchTodoDto> searchTodoDtoList) {
        return ResponseSearchTodoDto.builder()
                .searches(searchTodoDtoList)
                .build();
    }
}
