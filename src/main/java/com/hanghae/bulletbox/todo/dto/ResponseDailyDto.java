package com.hanghae.bulletbox.todo.dto;

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

    private ResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily){
        this.categories = categories;
        this.daily = daily;
    }

    public static ResponseDailyDto toResponseDailyDto(List<CategoryDto> categories, List<DailyDto> daily){
        return new ResponseDailyDto(categories, daily);
    }
}
