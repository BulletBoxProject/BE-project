package com.hanghae.bulletbox.diary.entity;

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
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = true)
    private String diaryContent;

    @Column(nullable = true)
    private String emotion;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = false)
    private Long month;

    @Column(nullable = false)
    private Long day;

    @Builder(access = AccessLevel.PRIVATE)
    private Diary(Member member, String diaryContent, String emotion, Long year, Long month, Long day) {
        this.member = member;
        this.diaryContent = diaryContent;
        this.emotion = emotion;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
