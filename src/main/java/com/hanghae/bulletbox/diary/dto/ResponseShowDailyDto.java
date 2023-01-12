package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowDailyDto {

    private List<DailyDto> dailyDtoList;

    private ResponseShowDailyDto(List<DailyDto> dailyDtoList) {
        this.dailyDtoList = dailyDtoList;
    }

    public static ResponseShowDailyDto toResponseShowDailyDto(List<DailyDto> dailyDtoList) {
        return new ResponseShowDailyDto(dailyDtoList);
    }
}
