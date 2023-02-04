package com.hanghae.bulletbox.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Todo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "searchTodoDto", description = "할 일 검색 Dto")
public class SearchTodoDto {

    @Schema(description = "할 일 ID", example = "1", type = "Long")
    private Long todoId;

    @Schema(description = "회원 DtO")
    @JsonIgnore
    private MemberDto memberDto;

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "카테고리 색상", example = "#000000", type = "String")
    private String categoryColor;

    @Schema(description = "Bullet 종류", example = "Todo", type = "Enum")
    private String todoBulletName;

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

    @Schema(description = "할 일의 하위 메모")
    private List<TodoMemoDto> todoMemos;

    @Builder(access = AccessLevel.PRIVATE)
    public SearchTodoDto(Long todoId, MemberDto memberDto, Long categoryId, String categoryColor, String todoBulletName, String todoContent, Long todoYear, Long todoMonth, Long todoDay, String time, List<TodoMemoDto> todoMemos) {
        this.todoId = todoId;
        this.memberDto = memberDto;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBulletName = todoBulletName;
        this.todoContent = todoContent;
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoDay = todoDay;
        this.time = time;
        this.todoMemos = todoMemos;
    }

    public void setTodoMemos(List<TodoMemoDto> todoMemos) {
        this.todoMemos = todoMemos;
    }

    public static SearchTodoDto toSearchTodoDto(Todo todo) {

        Member member = todo.getMember();
        MemberDto memberDto = MemberDto.toMemberDto(member);

        Long todoId = todo.getTodoId();
        String todoContent = todo.getTodoContent();
        Long todoYear = todo.getTodoYear();
        Long todoMonth = todo.getTodoMonth();
        Long todoDay = todo.getTodoDay();
        Long categoryId = todo.getCategoryId();
        String categoryColor = todo.getCategoryColor();
        String todoBulletName = todo.getTodoBullet().getName();
        String time = todo.getTime();

        return SearchTodoDto.builder()
                .memberDto(memberDto)
                .todoId(todoId)
                .todoContent(todoContent)
                .todoYear(todoYear)
                .todoMonth(todoMonth)
                .todoDay(todoDay)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .todoBulletName(todoBulletName)
                .time(time)
                .build();
    }
}
