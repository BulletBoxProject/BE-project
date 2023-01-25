package com.hanghae.bulletbox.diary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.TodoBullet;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class DailyTodoDto {

    private MemberDto memberDto;

    private Long todoId;

    private String todoContent;

    private String todoBulletName;

    private String todoBulletImgUrl;

    private String time;

    private Long categoryId;

    private String categoryColor;

    private Long year;

    private Long month;

    private Long day;

    private List<TodoMemoDto> memos;

    @Builder(access = AccessLevel.PRIVATE)
    private DailyTodoDto(MemberDto memberDto, Long todoId, String todoContent, String todoBulletName, String todoBulletImgUrl, String time, Long categoryId, String categoryColor, Long year, Long month, Long day, List<TodoMemoDto> memos) {
        this.memberDto = memberDto;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.todoBulletName = todoBulletName;
        this.todoBulletImgUrl = todoBulletImgUrl;
        this.time = time;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.year = year;
        this.month = month;
        this.day = day;
        this.memos = memos;
    }


    public static DailyTodoDto toDailyTodoDto(MemberDto memberDto, Long year, Long month, Long day){
        return DailyTodoDto.builder()
                .memberDto(memberDto)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }

    public static DailyTodoDto toDailyTodoDto(TodoDto todoDto, List<TodoMemoDto> memos){
        Long todoId = todoDto.getTodoId();
        Long categoryId = todoDto.getCategoryId();
        TodoBullet todoBullet = todoDto.getTodoBullet();
        String todoBulletName = todoBullet.getName();
        String todoBulletImgUrl = todoBullet.getImgUrl();
        String todoContent = todoDto.getTodoContent();
        String time = todoDto.getTime();
        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();

        return DailyTodoDto.builder()
                .todoId(todoId)
                .categoryId(categoryId)
                .todoBulletName(todoBulletName)
                .todoBulletImgUrl(todoBulletImgUrl)
                .todoContent(todoContent)
                .time(time)
                .year(todoYear)
                .month(todoMonth)
                .day(todoDay)
                .memos(memos)
                .build();
    }

    public static DailyTodoDto toDailyTodoDto(RequestCreateTodoDto requestCreateTodoDto, MemberDto memberDto) {
        String todoContent = requestCreateTodoDto.getTodoContent();
        String todoBulletName = requestCreateTodoDto.getTodoBulletName();
        String time = requestCreateTodoDto.getTime();
        Long categoryId = requestCreateTodoDto.getCategoryId();
        String categoryColor = requestCreateTodoDto.getCategoryColor();
        Long year = requestCreateTodoDto.getYear();
        Long month = requestCreateTodoDto.getMonth();
        Long day = requestCreateTodoDto.getDay();
        List<TodoMemoDto> memos = requestCreateTodoDto.getMemos();

        return DailyTodoDto.builder()
                .memberDto(memberDto)
                .todoContent(todoContent)
                .todoBulletName(todoBulletName)
                .time(time)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .year(year)
                .month(month)
                .day(day)
                .memos(memos)
                .build();
    }
}
