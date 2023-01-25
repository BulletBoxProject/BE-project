package com.hanghae.bulletbox.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.diary.dto.DailyTodoDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.TodoBullet;
import com.hanghae.bulletbox.todo.entity.Todo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "할 일(Todo) Dto")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoDto {

    @Schema(description = "할 일 ID", example = "1", type = "Long")
    private Long todoId;

    @Schema(description = "회원 DTO", example = "1", type = "MemberDto")
    private MemberDto memberDto;

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

    @Schema(description = "할 일에 기록된 시간", example = "14:00", type = "String")
    private String time;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoDto(Long todoId, MemberDto memberDto, Long categoryId, String categoryColor, TodoBullet todoBullet, String todoContent, Long todoYear, Long todoMonth, Long todoDay, String time) {
        this.todoId = todoId;
        this.memberDto = memberDto;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBullet = todoBullet;
        this.todoContent = todoContent;
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoDay = todoDay;
        this.time = time;
    }

    // Todo를 TodoDto로
    public static TodoDto toTodoDto(Todo todo){
        Member member = todo.getMember();
        MemberDto memberDto = MemberDto.toMemberDto(member);

        Long todoId = todo.getTodoId();
        Long categoryId = todo.getCategoryId();
        String categoryColor = todo.getCategoryColor();
        TodoBullet todoBullet = todo.getTodoBullet();
        String todoContent = todo.getTodoContent();
        Long todoYear = todo.getTodoYear();
        Long todoMonth = todo.getTodoMonth();
        Long todoDay = todo.getTodoDay();
        String time = todo.getTime();

        return TodoDto.builder()
                .todoId(todoId)
                .memberDto(memberDto)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .todoBullet(todoBullet)
                .todoContent(todoContent)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .time(time)
                .build();
    }

    public static TodoDto toTodoDto(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay) {
        return TodoDto.builder()
                .memberDto(memberDto)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .build();
    }

    public static TodoDto toTodoDto(MemberDto memberDto, Long categoryId, Long todoYear, Long todoMonth, Long todoDay) {
        return TodoDto.builder()
                .memberDto(memberDto)
                .categoryId(categoryId)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .build();
    }

    public static TodoDto toTodoDto(MemberDto memberDto) {
        return TodoDto.builder()
                .memberDto(memberDto)
                .build();
    }

    // TodoBulletName에 해당하는 TodoBullet으로 변환해서 저장
    public static TodoDto toTodoDto(DailyTodoDto dailyTodoDto){
        String todoBulletName = dailyTodoDto.getTodoBulletName();
        TodoBullet todoBullet = TodoBullet.valueOfName(todoBulletName);

        MemberDto memberDto = dailyTodoDto.getMemberDto();
        String todoContent = dailyTodoDto.getTodoContent();
        Long year = dailyTodoDto.getYear();
        Long month = dailyTodoDto.getMonth();
        Long day = dailyTodoDto.getDay();
        String time = dailyTodoDto.getTime();
        Long categoryId = dailyTodoDto.getCategoryId();
        String categoryColor = dailyTodoDto.getCategoryColor();

        return TodoDto.builder()
                .memberDto(memberDto)
                .todoBullet(todoBullet)
                .todoContent(todoContent)
                .todoYear(year)
                .todoMonth(month)
                .todoDay(day)
                .time(time)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .build();
    }

    public static TodoDto toTodoDto(MemberDto memberDto, Long todoYear, Long todoMonth) {
        return TodoDto.builder()
                .memberDto(memberDto)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .build();
    }

}
