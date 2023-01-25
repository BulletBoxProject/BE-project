package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.favorite.repository.FavoriteRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_CATEGORY_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final CategoryRepository categoryRepository;

    // 사용자(member) 카테고리 유효성 검사
    @Transactional(readOnly = true)
    protected void checkMemberHasCategoryId(Long categoryId, Member member){
        categoryRepository.findByCategoryIdAndMember(categoryId, member)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CATEGORY_MSG.getMsg()));
    }

    // member(사용자) null 체크
    private void checkMemberIsNotNull(Member member){
        if(member == null){
            throw new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg());
        }
    }

    // FavoriteTodo 유효성 검사
    private void checkFavoriteTodoIsSafe(Favorite favorite){
        Member member = favorite.getMember();
        Long categoryId = favorite.getCategoryId();

        checkMemberIsNotNull(member);

        if (categoryId == null) {
            return;
        }

        checkMemberHasCategoryId(categoryId, member);
    }

    // 자주 쓰는 할 일 생성
    @Transactional
    public FavoriteDto saveFavoriteTodo(FavoriteDto favoriteDto) {

        Favorite favorite = Favorite.toFavorite(favoriteDto);

        checkFavoriteTodoIsSafe(favorite);

        favoriteRepository.save(favorite);

        return FavoriteDto.toFavoriteDto(favorite);
    }

    @Transactional(readOnly = true)
    public List<FavoriteDto> findAllByMember(MemberDto memberDto) {

        Member member = Member.toMember(memberDto);

        List<Favorite> favoriteList = favoriteRepository.findAllByMember(member);
        List<FavoriteDto> favoriteDtoList = new ArrayList<>();

        for (Favorite favorite : favoriteList) {
            // Entity -> Dto 변환
            favoriteDtoList.add(FavoriteDto.toFavoriteDto(favorite));
        }

        return favoriteDtoList;
    }

    // member, favorite 기준으로 favoriteTodo 조회하기
    @Transactional(readOnly = true)
    public List<FavoriteDto> findAllByMemberAndFavorite(FavoritePageDto favoritePageDto) {

        MemberDto memberDto = favoritePageDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Long favoriteId = favoritePageDto.getFavoriteId();

        List<Favorite> favoriteList = favoriteRepository.findAllByMemberAndFavoriteId(member, favoriteId);
        List<FavoriteDto> favoriteDtoList = new ArrayList<>();

        for (Favorite favorite : favoriteList) {
            // Entity -> Dto 변환
            favoriteDtoList.add(FavoriteDto.toFavoriteDto(favorite));
        }

        return favoriteDtoList;
    }

    // 자주 쓰는 할 일 삭제
    @Transactional
    public void deleteFavoriteTodo(FavoritePageDto favoritePageDto) {

        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);
        Favorite favorite = Favorite.toFavorite(favoriteDto);
        Long favoriteId = favorite.getFavoriteId();

        favoriteRepository.deleteById(favoriteId);
    }
}
