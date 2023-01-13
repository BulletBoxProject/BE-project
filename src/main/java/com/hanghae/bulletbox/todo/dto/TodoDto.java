package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.todo.TodoBullet;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoDto {
    private Long todoId;

    private Long memberId;

    private Long categoryId;

    private String categoryColor;

    private TodoBullet todoBullet;

    private String todoContent;

    private Long todoYear;

    private Long todoMonth;

    private Long todoDay;

    private String dayOfTheWeek;

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
}
