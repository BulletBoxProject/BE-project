package com.hanghae.bulletbox.category.dto;

import com.hanghae.bulletbox.todo.dto.DailyDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCategoryDto {

    private List<DailyDto> daily;

    @Builder
    private ResponseCategoryDto(List<DailyDto> daily){
        this.daily = daily;
    }

    public static ResponseCategoryDto toResponseCategoryDto(List<DailyDto> daily){
        return ResponseCategoryDto.builder()
                .daily(daily)
                .build();
    }
}

