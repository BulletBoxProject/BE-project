package com.hanghae.bulletbox.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "할 일 검색 응답 Dto")
public class ResponseSearchTodoDto {

    @Schema(description = "할 일 검색 정보 담는 변수", type = "List")
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
