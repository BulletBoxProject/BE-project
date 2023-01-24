package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.member.dto.MemberDto;

import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.entity.TodoMemo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoMemoDto {

    private Long todoMemoId;

    private String todoMemoContent;

    private MemberDto memberDto;

    private TodoDto todoDto;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoMemoDto(Long todoMemoId, String todoMemoContent, MemberDto memberDto, TodoDto todoDto) {
        this.todoMemoId = todoMemoId;
        this.todoMemoContent = todoMemoContent;
        this.memberDto = memberDto;
        this.todoDto = todoDto;
    }

    public static TodoMemoDto toTodoMemoDto(TodoMemo todoMemo) {
        Long todoMemoId = todoMemo.getTodoMemoId();
        String todoMemoContent = todoMemo.getTodoMemoContent();
        Todo todo = todoMemo.getTodo();
        Member member = todoMemo.getMember();

        TodoDto todoDto = TodoDto.toTodoDto(todo);
        MemberDto memberDto = MemberDto.toMemberDto(member);

        return TodoMemoDto.builder()
                .todoMemoId(todoMemoId)
                .todoMemoContent(todoMemoContent)
                .todoDto(todoDto)
                .memberDto(memberDto)
                .build();

    }
}
