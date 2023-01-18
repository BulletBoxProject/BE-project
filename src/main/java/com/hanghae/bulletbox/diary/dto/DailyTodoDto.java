package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DailyTodoDto {

    private MemberDto memberDto;

    private String todoContent;

    private String todoBulletName;

    private String time;

    private Long categoryId;

    private String categoryColor;

    private Long year;

    private Long month;

    private Long day;

    private List<TodoMemoDto> memos;

    @Builder(access = AccessLevel.PRIVATE)
    private DailyTodoDto(MemberDto memberDto, String todoContent, String todoBulletName, String time, Long categoryId, String categoryColor, Long year, Long month, Long day, List<TodoMemoDto> memos) {
        this.memberDto = memberDto;
        this.todoContent = todoContent;
        this.todoBulletName = todoBulletName;
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

    public static DailyTodoDto toDailyTodoDto(RequestCreateTodoDto requestCreateTodoDto, MemberDto memberDto) {
        String todoContent = requestCreateTodoDto.getTodoContent();
        String todoBulletName = requestCreateTodoDto.getTodoBulletName();
        String time = requestCreateTodoDto.getTime();
        Long categoryId = requestCreateTodoDto.getCategoryId();
        String categoryColor = requestCreateTodoDto.getCategoryColor();
        Long year = requestCreateTodoDto.getYear();
        Long month = requestCreateTodoDto.getMonth();
        Long day = requestCreateTodoDto.getDay();

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
                .build();
    }
}
