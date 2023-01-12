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
        return TodoDto.builder()
                .memberId(memberId)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .build();
    }

    // private 빌더 패턴 적용
    private static TodoDtoBuilder builder() {
        return new TodoDtoBuilder();
    }

    public static class TodoDtoBuilder {
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

        TodoDtoBuilder() {
        }

        private TodoDtoBuilder todoId(Long todoId) {
            this.todoId = todoId;
            return this;
        }

        private TodoDtoBuilder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        private TodoDtoBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        private TodoDtoBuilder categoryColor(String categoryColor) {
            this.categoryColor = categoryColor;
            return this;
        }

        private TodoDtoBuilder todoBullet(TodoBullet todoBullet) {
            this.todoBullet = todoBullet;
            return this;
        }

        private TodoDtoBuilder todoContent(String todoContent) {
            this.todoContent = todoContent;
            return this;
        }

        private TodoDtoBuilder todoYear(Long todoYear) {
            this.todoYear = todoYear;
            return this;
        }

        private TodoDtoBuilder todoMonth(Long todoMonth) {
            this.todoMonth = todoMonth;
            return this;
        }

        private TodoDtoBuilder todoDay(Long todoDay) {
            this.todoDay = todoDay;
            return this;
        }

        private TodoDtoBuilder dayOfTheWeek(String dayOfTheWeek) {
            this.dayOfTheWeek = dayOfTheWeek;
            return this;
        }

        private TodoDtoBuilder time(String time) {
            this.time = time;
            return this;
        }

        private TodoDto build() {
            return new TodoDto(todoId, memberId, categoryId, categoryColor, todoBullet, todoContent, todoYear, todoMonth, todoDay, dayOfTheWeek, time);
        }
    }
}
