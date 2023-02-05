package com.hanghae.bulletbox.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.TodoBullet;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@Schema(description = "데일리 로그 서비스 Dto")
public class DailyTodoDto {

    @Schema(description = "유저 Dto")
    private MemberDto memberDto;

    @Schema(description = "할 일 ID", type = "Long", example = "1")
    private Long todoId;

    @Schema(description = "카테고리 ID", type = "Long", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 색상", type = "String", example = "#000000")
    private String categoryColor;

    @Schema(description = "Bullet 이름", type = "String", example = "Todo")
    private String todoBulletName;

    @Schema(description = "Bullet 이미지", type = "String", example = "img_url...")
    private String todoBulletImgUrl;

    @Schema(description = "할 일 내용", type = "String", example = "할 일 내용")
    private String todoContent;

    @Schema(description = "할 일에 기록된 시간", type = "String", example = "14:00")
    private String time;

    @Schema(description = "연도", example = "2023", type = "Long")
    private Long year;

    @Schema(description = "월", example = "12", type = "Long")
    private Long month;

    @Schema(description = "날짜", example = "12", type = "Long")
    private Long day;

    @Schema(description = "할 일의 메모 변수", type = "List")
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
        String categoryColor = todoDto.getCategoryColor();
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
                .categoryColor(categoryColor)
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

    public static DailyTodoDto toDailyTodoDto(RequestUpdateTodoDto requestUpdateTodoDto, MemberDto memberDto) {
        Long todoId = requestUpdateTodoDto.getTodoId();
        String todoContent = requestUpdateTodoDto.getTodoContent();
        String todoBulletName = requestUpdateTodoDto.getTodoBulletName();
        String time = requestUpdateTodoDto.getTime();
        Long categoryId = requestUpdateTodoDto.getCategoryId();
        String categoryColor = requestUpdateTodoDto.getCategoryColor();
        Long year = requestUpdateTodoDto.getYear();
        Long month = requestUpdateTodoDto.getMonth();
        Long day = requestUpdateTodoDto.getDay();
        List<TodoMemoDto> memos = requestUpdateTodoDto.getMemos();

        return DailyTodoDto.builder()
                .memberDto(memberDto)
                .todoId(todoId)
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
