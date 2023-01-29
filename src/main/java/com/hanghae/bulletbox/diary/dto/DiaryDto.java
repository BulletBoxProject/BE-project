package com.hanghae.bulletbox.diary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.diary.entity.Diary;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryDto {

    private Long diaryId;

    @JsonIgnore
    private MemberDto memberDto;

    private String diaryContent;

    private String emotion;

    private Long year;

    private Long month;

    private Long day;

    @Builder(access = AccessLevel.PRIVATE)
    private DiaryDto(Long diaryId, MemberDto memberDto, String diaryContent, String emotion, Long year, Long month, Long day) {
        this.diaryId = diaryId;
        this.memberDto = memberDto;
        this.diaryContent = diaryContent;
        this.emotion = emotion;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Diary를 DiaryDto로 변환
    public static DiaryDto toDiaryDto(Diary diary) {
        Long diaryId = diary.getDiaryId();
        Member member = diary.getMember();
        MemberDto memberDto = MemberDto.toMemberDto(member);
        String diaryContent = diary.getDiaryContent();
        String emotion = diary.getEmotion();
        Long year = diary.getYear();
        Long month = diary.getMonth();
        Long day = diary.getDay();

        return DiaryDto.builder()
                .diaryId(diaryId)
                .memberDto(memberDto)
                .diaryContent(diaryContent)
                .emotion(emotion)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
