package com.hanghae.bulletbox.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "월별 이모션 Dto")
public class MonthlyEmotionDto {

    @Schema(description = "날짜", example = "20", type = "Long")
    private Long day;

    @Schema(description = "일기장 이모션", type = "String")
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
