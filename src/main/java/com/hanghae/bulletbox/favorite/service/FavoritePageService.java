package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseCreateFavoriteDto;
import com.hanghae.bulletbox.favorite.dto.ResponseShowFavoritePageDto;
import com.hanghae.bulletbox.favorite.dto.ResponseUpdateFavoriteDto;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritePageService implements FavoriteFacade {

    private final FavoriteService favoriteService;

    private final FavoriteMemoService favoriteMemoService;

    // 루틴 생성
    @Override
    @Transactional
    public ResponseCreateFavoriteDto createFavorite(FavoritePageDto favoritePageDto) {

        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);

        favoriteDto = favoriteService.saveFavorite(favoriteDto);

        List<FavoriteMemoDto> favoriteMemoDtoList = favoritePageDto.getFavoriteMemos();
        MemberDto memberDto = favoriteDto.getMemberDto();
        List<FavoriteMemoDto> responseFavoriteMemoDtoList = new ArrayList<>();

        for (FavoriteMemoDto favoriteMemoDto : favoriteMemoDtoList) {
            favoriteMemoDto.setMemberDto(memberDto);
            favoriteMemoDto.setFavoriteDto(favoriteDto);

            // 루틴의 메모 저장
            favoriteMemoDto = favoriteMemoService.saveFavoriteMemo(favoriteMemoDto);

            String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();
            Long favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();

            // 응답 DTO 자주 쓰는 할 일의 정보 저장
            responseFavoriteMemoDtoList.add(FavoriteMemoDto.toFavoriteMemoDto(favoriteMemoId, favoriteMemoContent));
        }

        return ResponseCreateFavoriteDto.toResponseCreateFavoriteDto(favoriteDto, responseFavoriteMemoDtoList);
    }

    // 루틴 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseShowFavoritePageDto showFavoritePage(FavoritePageDto favoritePageDto) {

        MemberDto memberDto = favoritePageDto.getMemberDto();

        List<FavoriteDto> favoriteDtoList = favoriteService.findAllByMember(memberDto);
        for (FavoriteDto favoriteDto : favoriteDtoList) {
            List<FavoriteMemoDto> favoriteMemoDtoList = favoriteMemoService.findAllByMemberAndFavorite(memberDto, favoriteDto);
            favoriteDto.setFavoriteMemos(favoriteMemoDtoList);
        }

        return ResponseShowFavoritePageDto.toResponseShowFavoritePageDto(favoriteDtoList);
    }

    // 루틴 삭제
    @Override
    @Transactional
    public void deleteFavoriteTodo(FavoritePageDto favoritePageDto) {

        MemberDto memberDto = favoritePageDto.getMemberDto();
        FavoriteDto toFavoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);

        // 먼저 member, favoriteId를 기준으로 할 일 불러와야 함.
        List<FavoriteDto> favoriteDtoList = favoriteService.findAllByMemberAndFavoriteId(memberDto, toFavoriteDto);

        for (FavoriteDto favoriteDto : favoriteDtoList) {

            List<FavoriteMemoDto> favoriteMemoDtoList = favoriteMemoService.findAllByMemberAndFavorite(memberDto, favoriteDto);
            favoriteDto.setFavoriteMemos(favoriteMemoDtoList);

            if (!favoriteDto.getFavoriteMemos().isEmpty()) {
                favoriteMemoService.deleteAllByFavoriteMemo(favoriteDto);
                break;
            }
        }

        favoriteService.deleteFavoriteTodo(toFavoriteDto);
    }

    // 루틴 수정
    @Override
    @Transactional
    public ResponseUpdateFavoriteDto updateFavoriteTodo(FavoritePageDto favoritePageDto) {

        // 루틴 업데이트 진행
        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);
        favoriteDto = favoriteService.updateFavorite(favoriteDto);

        MemberDto memberDto = favoritePageDto.getMemberDto();

        // 루틴의 메모 업데이트 진행
        List<FavoriteMemoDto> updateMemoList = favoritePageDto.getFavoriteMemos();
        List<FavoriteMemoDto> responseFavoriteMemoDtoList = new ArrayList<>();

        for (FavoriteMemoDto favoriteMemoDto : updateMemoList) {
            Long favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();
            String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

            // 루틴의 메모가 삭제됐을 경우, favoriteMemoContent == null -> favoriteMemoId를 통해 해당 favoriteMemo 삭제
            if (favoriteMemoContent == null) {
                favoriteMemoService.deleteFavoriteMemoById(favoriteMemoId);

                continue;
            }

            // 루틴의 메모가 추가됐을 경우, favoriteMemoId == null -> 해당 id 값을 가지고 새로 생성
            if (favoriteMemoId == null) {
                favoriteMemoDto.setFavoriteDto(favoriteDto);
                favoriteMemoDto.setMemberDto(memberDto);
                favoriteMemoDto.setFavoriteMemoContent(favoriteMemoContent);

                // 메모 저장
                favoriteMemoDto = favoriteMemoService.saveFavoriteMemo(favoriteMemoDto);

                favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();
                favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

                responseFavoriteMemoDtoList.add(FavoriteMemoDto.toFavoriteMemoDto(favoriteMemoId, favoriteMemoContent));

                continue;
            }

            // 기존 루틴의 메모가 수정됐을 경우, update 진행
            favoriteMemoDto.setMemberDto(memberDto);
            favoriteMemoDto.setFavoriteDto(favoriteDto);

            favoriteMemoDto = favoriteMemoService.updateFavoriteMemo(favoriteMemoDto);

            favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();
            favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

            responseFavoriteMemoDtoList.add(FavoriteMemoDto.toFavoriteMemoDto(favoriteMemoId, favoriteMemoContent));
        }

        return ResponseUpdateFavoriteDto.toResponseUpdateFavoriteDto(favoriteDto, responseFavoriteMemoDtoList);
    }
}
