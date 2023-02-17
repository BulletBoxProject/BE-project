package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseUpdateFavoriteDto;
import org.springframework.transaction.annotation.Transactional;

public interface FavoriteFacade {

    @Transactional
    ResponseCreateFavoriteDto createFavorite(FavoritePageDto favoritePageDto);

    @Transactional(readOnly = true)
    ResponseShowFavoritePageDto showFavoritePage(FavoritePageDto favoritePageDto);

    @Transactional
    void deleteFavoriteTodo(FavoritePageDto favoritePageDto);

    @Transactional
    ResponseUpdateFavoriteDto updateFavoriteTodo(FavoritePageDto favoritePageDto);
}
