package com.hanghae.bulletbox.todo.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.member.entity.Member;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID", nullable = false)
    private Todo todo;

    @Column(nullable = true)
    private String memoContent;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoMemo(Member member, Todo todo, String memoContent) {
        this.member = member;
        this.todo = todo;
        this.memoContent = memoContent;
    }

    public static TodoMemo todoMemo(Member member, Todo todo, String memoContent){
        return TodoMemo.builder()
                .member(member)
                .todo(todo)
                .memoContent(memoContent)
                .build();
    }

}
