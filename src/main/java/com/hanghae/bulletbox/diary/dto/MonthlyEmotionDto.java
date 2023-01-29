package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MonthlyEmotionDto {

    private Long day;

    private String emotion;

    @Builder(access = AccessLevel.PRIVATE)
    private MonthlyEmotionDto(Long day, String emotion) {
        this.day = day;
        this.emotion = emotion;
    }

    public static MonthlyEmotionDto toMonthlyEmotionDto(Long day, String emotion){
        return MonthlyEmotionDto.builder()
                .day(day)
                .emotion(emotion)
                .build();
    }
}
