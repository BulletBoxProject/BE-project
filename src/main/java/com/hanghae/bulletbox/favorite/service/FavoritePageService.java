package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteTodoDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoriteTodoPageDto;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritePageService {

    private final FavoriteService favoriteService;

    private final FavoriteMemoService favoriteMemoService;

    // 자주 쓰는 할 일 생성
    @Transactional
    public ResponseCreateFavoriteTodoDto createFavoriteTodo(FavoritePageDto favoritePageDto) {

        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);
        favoriteDto = favoriteService.saveFavoriteTodo(favoriteDto);

        List<FavoriteMemoDto> favoriteMemoDtoList = favoritePageDto.getFavoriteMemos();
        MemberDto memberDto = favoriteDto.getMemberDto();
        List<FavoriteMemoDto> responseFavoriteMemoDtoList = new ArrayList<>();

        for (FavoriteMemoDto favoriteMemoDto : favoriteMemoDtoList) {
            favoriteMemoDto.setMemberDto(memberDto);
            favoriteMemoDto.setFavoriteDto(favoriteDto);

            // 자주 쓰는 할 일의 메모 저장
            favoriteMemoDto = favoriteMemoService.saveFavoriteMemo(favoriteMemoDto);

            String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();
            Long favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();

            // 응답 DTO 자주 쓰는 할 일의 정보 저장
            responseFavoriteMemoDtoList.add(FavoriteMemoDto.toFavoriteMemoDto(favoriteMemoId, favoriteMemoContent));
        }

        Long favoriteId = favoriteDto.getFavoriteId();
        String favoriteContent = favoriteDto.getFavoriteContent();

        return ResponseCreateFavoriteTodoDto.toResponseCreateFavoriteTodoDto(favoriteId, favoriteContent, responseFavoriteMemoDtoList);
    }

    // 자주 쓰는 할 일 조회
    @Transactional(readOnly = true)
    public ResponseShowFavoriteTodoPageDto showFavoriteTodoPage(FavoritePageDto favoritePageDto) {

        MemberDto memberDto = favoritePageDto.getMemberDto();

        List<FavoriteDto> favoriteDtoList = favoriteService.findAllByMember(memberDto);
        for (FavoriteDto favoriteDto : favoriteDtoList) {
            List<FavoriteMemoDto> favoriteMemoDtoList = favoriteMemoService.findAllByMemberAndFavorite(memberDto, favoriteDto);
            favoriteDto.setFavoriteMemos(favoriteMemoDtoList);
        }

        return ResponseShowFavoriteTodoPageDto.toResponseShowFavoriteTodoPageDto(favoriteDtoList);
    }
}
