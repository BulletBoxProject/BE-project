package com.hanghae.bulletbox.todo.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.MemoBullet;

import lombok.AccessLevel;
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
public class Memo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID", nullable = false)
    private Todo todo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemoBullet memoBullet;

    @Column(nullable = true)
    private String memoContent;

    public Memo(Member member, Todo todo, MemoBullet memoBullet, String memoContent) {
        this.member = member;
        this.todo = todo;
        this.memoBullet = memoBullet;
        this.memoContent = memoContent;
    }
}
