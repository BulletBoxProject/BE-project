package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
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
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_FAVORITE_MSG;
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

    // member, favoriteId 기준으로 FavoriteTodo 가져오기
    @Transactional(readOnly = true)
    public List<FavoriteDto> findAllByMemberAndFavoriteId(MemberDto memberDto, FavoriteDto favoriteDto) {

        Member member = Member.toMember(memberDto);
        Long favoriteId = favoriteDto.getFavoriteId();

        List<Favorite> favoriteList = favoriteRepository.findAllByMemberAndFavoriteId(member, favoriteId);
        List<FavoriteDto> favoriteDtoList = new ArrayList<>();

        for (Favorite favorite : favoriteList) {
            // Entity -> Dto 변환
            favoriteDtoList.add(FavoriteDto.toFavoriteDto(favorite));
        }

        return favoriteDtoList;
    }

    // 루틴 삭제
    @Transactional
    public void deleteFavoriteTodo(FavoriteDto favoriteDto) {

        Favorite favorite = Favorite.toFavorite(favoriteDto);
        Long favoriteId = favorite.getFavoriteId();

        favoriteRepository.deleteById(favoriteId);
    }

    // 루틴 업데이트 (For dirty checking)
    @Transactional
    public void updateFavorite(FavoriteDto favoriteDto) {

        Long favoriteId = favoriteDto.getFavoriteId();
        MemberDto memberDto = favoriteDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Favorite favorite = findFavoriteByIdAndMember(favoriteId, member);

        checkFavoriteTodoIsSafe(favorite);

        favorite.update(favoriteDto);
    }

    // favoriteId, member 를 기준으로 루틴 조회
    private Favorite findFavoriteByIdAndMember(Long favoriteId, Member member) {
        return favoriteRepository.findAllByFavoriteIdAndMember(favoriteId, member).orElseThrow(
                () -> new NoSuchElementException(NOT_FOUND_FAVORITE_MSG.getMsg())
        );
    }
}
