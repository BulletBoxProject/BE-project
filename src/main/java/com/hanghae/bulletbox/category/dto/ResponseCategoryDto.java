package com.hanghae.bulletbox.category.dto;

import com.hanghae.bulletbox.todo.dto.DailyDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCategoryDto {

    private List<DailyDto> daily;


    public ResponseCategoryDto(List<DailyDto> daily){
        this.daily = daily;
    }

    public static ResponseCategoryDto toResponseCategoryDto(List<DailyDto> daily){
        return new ResponseCategoryDto(daily);
    }
}

