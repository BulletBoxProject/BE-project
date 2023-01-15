package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.todo.TodoBullet;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "할 일(Todo) Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoDto {

    @Schema(description = "할 일 ID", example = "1", type = "Long")
    private Long todoId;

    @Schema(description = "회원 ID", example = "1", type = "Long")
    private Long memberId;

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "카테고리 색상", example = "#000000", type = "String")
    private String categoryColor;

    @Schema(description = "Bullet 종류", example = "Todo", type = "Enum")
    private TodoBullet todoBullet;

    @Schema(description = "할 일 내용", example = "할 일 내용", type = "String")
    private String todoContent;

    @Schema(description = "할 일의 연도", example = "2023", type = "Long")
    private Long todoYear;

    @Schema(description = "할 일의 월", example = "1", type = "Long")
    private Long todoMonth;

    @Schema(description = "할 일의 일", example = "20", type = "Long")
    private Long todoDay;

    @Schema(description = "할 일의 요일", example = "화", type = "String")
    private String dayOfTheWeek;

    @Schema(description = "할 일에 기록된 시간", example = "14:00", type = "String")
    private String time;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoDto(Long todoId, Long memberId, Long categoryId, String categoryColor, TodoBullet todoBullet, String todoContent, Long todoYear, Long todoMonth, Long todoDay, String dayOfTheWeek, String time) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBullet = todoBullet;
        this.todoContent = todoContent;
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoDay = todoDay;
        this.dayOfTheWeek = dayOfTheWeek;
        this.time = time;
    }

    public static TodoDto toTodoDto(Long memberId, Long todoYear, Long todoMonth, Long todoDay) {
        return TodoDto.builder()
                .memberId(memberId)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .build();
    }

    public static TodoDto toTodoDto(Long memberId, Long categoryId, Long todoYear, Long todoMonth, Long todoDay) {
        return TodoDto.builder()
                .memberId(memberId)
                .categoryId(categoryId)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .build();
    }

    public static TodoDto toTodoDto(Long memberId) {
        return TodoDto.builder()
                .memberId(memberId)
                .build();
    }

    public static TodoDto toTodoDto(Long memberId, Long todoYear, Long todoMonth) {
        return TodoDto.builder()
                .memberId(memberId)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .build();
    }

}
