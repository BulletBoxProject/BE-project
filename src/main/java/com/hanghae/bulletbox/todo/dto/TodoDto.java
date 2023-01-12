package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.todo.TodoBullet;

import lombok.AccessLevel;
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

    public static TodoDto toTodoDto(Long todoId, Long memberId, Long categoryId, String categoryColor, TodoBullet todoBullet, String todoContent, Long todoYear, Long todoMonth, Long todoDay, String dayOfTheWeek, String time) {
        return new TodoDto(todoId, memberId, categoryId, categoryColor, todoBullet, todoContent, todoYear, todoMonth, todoDay, dayOfTheWeek, time);
    }

    public static TodoDto toTodoDto(Long memberId, Long todoYear, Long todoMonth, Long todoDay) {
        return new TodoDto(null, memberId, null, null, null, null, todoYear, todoMonth, todoDay, null, null);
    }
}
