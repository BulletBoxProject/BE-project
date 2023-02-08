package com.hanghae.bulletbox.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TodoMemo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoMemoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID", nullable = false)
    private Todo todo;

    @Column(nullable = true)
    private String todoMemoContent;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoMemo(Member member, Todo todo, String todoMemoContent) {
        this.member = member;
        this.todo = todo;
        this.todoMemoContent = todoMemoContent;
    }

    public static TodoMemo toTodoMemo(Member member, Todo todo, String todoMemoContent){
        return TodoMemo.builder()
                .member(member)
                .todo(todo)
                .todoMemoContent(todoMemoContent)
                .build();
    }

    // TodoMemoDto를 TodoMemo로 변환
    public static TodoMemo toTodoMemo(TodoMemoDto todoMemoDto){
        TodoDto todoDto = todoMemoDto.getTodoDto();
        MemberDto memberDto = todoMemoDto.getMemberDto();
        String todoMemoContent = todoMemoDto.getTodoMemoContent();

        Todo todo = Todo.toTodo(todoDto);
        Member member = Member.toMember(memberDto);

        return TodoMemo.builder()
                .member(member)
                .todo(todo)
                .todoMemoContent(todoMemoContent)
                .build();
    }

    // TodoMemo의 내용을 todoMemoDto의 내용으로 수정
    public void updateContent(TodoMemoDto todoMemoDto) {
        todoMemoContent = todoMemoDto.getTodoMemoContent();
    }
}
