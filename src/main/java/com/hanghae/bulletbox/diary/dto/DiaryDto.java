package com.hanghae.bulletbox.diary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hanghae.bulletbox.diary.entity.Diary;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "일기장 Dto")
public class DiaryDto {

    @Schema(description = "일기장 ID", example = "1", type = "Long")
    private Long diaryId;

    @JsonIgnore
    @Schema(description = "유저 Dto")
    private MemberDto memberDto;

    @Schema(description = "일기장 내용", example = "일기장 내용", type = "String")
    private String diaryContent;

    @Schema(description = "일기장 ID", type = "String")
    private String emotion;

    @Schema(description = "연도", example = "2023", type = "Long")
    private Long year;

    @Schema(description = "월", example = "12", type = "Long")
    private Long month;

    @Schema(description = "날짜", example = "20", type = "Long")
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

    public static DiaryDto toDiaryDto(RequestDiaryUpdateDto requestDiaryUpdateDto, MemberDto memberDto){
        Long diaryId = requestDiaryUpdateDto.getDiaryId();
        String diaryContent = requestDiaryUpdateDto.getDiaryContent();
        String emotion = requestDiaryUpdateDto.getEmotion();
        Long year = requestDiaryUpdateDto.getYear();
        Long month = requestDiaryUpdateDto.getMonth();
        Long day = requestDiaryUpdateDto.getDay();

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

    public static DiaryDto toDiaryDto(MemberDto memberDto, Long year, Long month, Long day) {
        return DiaryDto.builder()
                .memberDto(memberDto)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
