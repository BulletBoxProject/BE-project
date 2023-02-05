package com.hanghae.bulletbox.category.dto;

import com.hanghae.bulletbox.todo.dto.DailyDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "카테고리 응답 Dto")
public class ResponseCategoryDto {

    @Schema(description = "데일리 정보를 담는 List", type = "List")
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

