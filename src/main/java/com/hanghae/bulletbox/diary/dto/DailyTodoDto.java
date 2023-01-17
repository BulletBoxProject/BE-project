package com.hanghae.bulletbox.diary.dto;

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

    private Member member;

    private String todoContent;

    private String todoBulletName;

    private String time;

    private Long categoryId;

    private Long year;

    private Long month;

    private Long day;

    private List<TodoMemoDto> memos;

    @Builder(access = AccessLevel.PRIVATE)
    private DailyTodoDto(Member member, String todoContent, String todoBulletName, String time, Long categoryId, Long year, Long month, Long day, List<TodoMemoDto> memos) {
        this.member = member;
        this.todoContent = todoContent;
        this.todoBulletName = todoBulletName;
        this.time = time;
        this.categoryId = categoryId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.memos = memos;
    }

    public static DailyTodoDto toDailyTodoDto(Member member, Long year, Long month, Long day){
        return DailyTodoDto.builder()
                .member(member)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
