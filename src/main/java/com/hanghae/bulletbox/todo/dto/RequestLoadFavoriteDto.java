package com.hanghae.bulletbox.todo.dto;

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
