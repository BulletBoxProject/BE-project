package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "메인 페이지 데일리 로그 조회 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowDailyDto {

    private List<CategoryDto> categories;

    @Schema(description = "메인 페이지 데일리 로그 정보")
    private List<DailyDto> daily;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowDailyDto(List<CategoryDto> categories, List<DailyDto> daily) {
        this.categories = categories;
        this.daily = daily;
    }

    public static ResponseShowDailyDto toResponseShowDailyDto(List<CategoryDto> categoryDtoList, List<DailyDto> dailyDtoList) {
        return ResponseShowDailyDto.builder()
                .categories(categoryDtoList)
                .daily(dailyDtoList)
                .build();
    }
}
