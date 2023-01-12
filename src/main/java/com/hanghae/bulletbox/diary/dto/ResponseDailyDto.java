package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDailyDto {

    private List<CategoryDto> categories;

    private List<DailyDto> daily;

    private boolean withExperience;

    public ResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily, boolean withExperience){
        this.categories = categories;
        this.daily = daily;
        this.withExperience = withExperience;
    }

    public static ResponseDailyDto toResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily, boolean withExperience){
        return new ResponseDailyDto(categories, daily, withExperience);
    }


}
