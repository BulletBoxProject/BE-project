package com.hanghae.bulletbox.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.TodoBullet;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = true)
    private Long categoryId;

    @Column(nullable = true)
    private String categoryColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoBullet todoBullet;

    @Column(nullable = true)
    private String todoContent;

    @Column(nullable = true)
    private Long todoYear;

    @Column(nullable = true)
    private Long todoMonth;

    @Column(nullable = true)
    private Long todoDay;

    @Column(nullable = true)
    private String time;

    @Builder(access = AccessLevel.PRIVATE)
    private Todo(Long todoId, Member member, Long categoryId, String categoryColor, TodoBullet todoBullet, String todoContent, Long todoYear, Long todoMonth, Long todoDay, String time) {
        this.todoId = todoId;
        this.member = member;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBullet = todoBullet;
        this.todoContent = todoContent;
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoDay = todoDay;
        this.time = time;
    }

    // TodoDto를 Todo로 변환
    public static Todo toTodo(TodoDto todoDto){
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Long todoId = todoDto.getTodoId();
        Long categoryId = todoDto.getCategoryId();
        String categoryColor = todoDto.getCategoryColor();
        TodoBullet todoBullet = todoDto.getTodoBullet();
        String todoContent = todoDto.getTodoContent();
        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();
        String time = todoDto.getTime();

        return Todo.builder()
                .todoId(todoId)
                .member(member)
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
}