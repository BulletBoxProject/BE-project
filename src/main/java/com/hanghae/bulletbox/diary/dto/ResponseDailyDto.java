package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Daily 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDailyDto {

    @Schema(description = "카테고리 정보")
    private List<CategoryDto> categories;

    @Schema(description = "할 일 정보")
    private List<DailyDto> daily;

    @Schema(description = "경혐 조회 여부", type = "boolean", example = "false")
    private boolean withExperience;

    private ResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily, boolean withExperience){
        this.categories = categories;
        this.daily = daily;
        this.withExperience = withExperience;
    }

    public static ResponseDailyDto toResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily, boolean withExperience){
        return new ResponseDailyDto(categories, daily, withExperience);
    }
}
