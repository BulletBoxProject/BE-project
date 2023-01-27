package com.hanghae.bulletbox.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLoadFavoriteDto {

    private Long favoriteId;

    private Long year;

    private Long month;

    private Long day;
}
