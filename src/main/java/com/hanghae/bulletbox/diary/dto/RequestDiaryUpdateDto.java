package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestDiaryUpdateDto {

    private Long diaryId;

    private String diaryContent;

    private String emotion;

    private Long year;

    private Long month;

    private Long day;
}
