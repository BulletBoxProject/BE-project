package com.hanghae.bulletbox.daily.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "루틴 불러오기 요청 Dto")
public class RequestLoadFavoriteDto {

    @Schema(description = "루틴 id", example = "1", type = "Long")
    private Long favoriteId;

    @Schema(description = "연도", example = "2023", type = "Long")
    private Long year;

    @Schema(description = "월", example = "12", type = "Long")
    private Long month;

    @Schema(description = "날짜", example = "12", type = "Long")
    private Long day;
}
