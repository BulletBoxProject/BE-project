package com.hanghae.bulletbox.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "일기장 수정 요청 Dto")
public class RequestDiaryUpdateDto {

    @Schema(description = "일기장 ID", example = "1", type = "Long")
    private Long diaryId;

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
}
