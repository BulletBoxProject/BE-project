package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Todo;

import lombok.*;

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
}
