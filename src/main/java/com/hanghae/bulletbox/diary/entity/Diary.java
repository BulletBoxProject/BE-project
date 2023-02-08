package com.hanghae.bulletbox.diary.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.diary.dto.DiaryDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
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
public class Diary extends TimeStamped {

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
    private Diary(Long diaryId, Member member, String diaryContent, String emotion, Long year, Long month, Long day) {
        this.diaryId = diaryId;
        this.member = member;
        this.diaryContent = diaryContent;
        this.emotion = emotion;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // diaryDto를 diary로 변환
    public static Diary toDiary(DiaryDto diaryDto) {
        Long diaryId = diaryDto.getDiaryId();
        MemberDto memberDto = diaryDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        String diaryContent = diaryDto.getDiaryContent();
        String emotion = diaryDto.getEmotion();
        Long year = diaryDto.getYear();
        Long month = diaryDto.getMonth();
        Long day = diaryDto.getDay();

        return Diary.builder()
                .diaryId(diaryId)
                .member(member)
                .diaryContent(diaryContent)
                .emotion(emotion)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }

    // Diary 수정
    public void updateAll(DiaryDto diaryDto) {
        diaryContent = diaryDto.getDiaryContent();
        emotion = diaryDto.getEmotion();
    }
}
