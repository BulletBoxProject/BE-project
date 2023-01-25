package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
import com.hanghae.bulletbox.favorite.dto.FavoritePageDto;
import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.favorite.entity.FavoriteMemo;
import com.hanghae.bulletbox.favorite.repository.FavoriteMemoRepository;
import com.hanghae.bulletbox.favorite.repository.FavoriteRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.FAVORITE_NOT_FOUND_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Service
@RequiredArgsConstructor
public class FavoriteMemoService {

    private final FavoriteRepository favoriteRepository;

    private final FavoriteMemoRepository favoriteMemoRepository;

    // Member null 체크
    private void checkMemberIsNotNull(Member member){
        if(member == null){
            throw new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg());
        }
    }

    // favorite 유효성 검사(member 매칭 유무)
    @Transactional(readOnly = true)
    protected void checkMemberHasFavoriteId(Member member, Favorite favorite){
        Long favoriteId = favorite.getFavoriteId();
        favoriteRepository.findByFavoriteIdAndMember(favoriteId, member)
                .orElseThrow(() -> new NoSuchElementException(FAVORITE_NOT_FOUND_MSG.getMsg()));
    }

    // 자주 쓰는 할 일의 메모 생성
    @Transactional
    public FavoriteMemoDto saveFavoriteMemo(FavoriteMemoDto favoriteMemoDto) {

        FavoriteMemo favoriteMemo = FavoriteMemo.toFavoriteMemo(favoriteMemoDto);
        Member member = favoriteMemo.getMember();
        Favorite favorite = favoriteMemo.getFavorite();
        String favoriteMemoContent = favoriteMemo.getFavoriteMemoContent();

        if (favoriteMemoContent == null) {
            return null;
        }

        checkMemberIsNotNull(member);
        checkMemberHasFavoriteId(member, favorite);

        favoriteMemoRepository.save(favoriteMemo);

        return FavoriteMemoDto.toFavoriteMemoDto(favoriteMemo);
    }


    // member, favorite 기준으로 자주 쓰는 할 일의 메모 찾기
    @Transactional(readOnly = true)
    public List<FavoriteMemoDto> findAllByMemberAndFavorite(MemberDto memberDto, FavoriteDto favoriteDto) {

        Member member = Member.toMember(memberDto);
        Favorite favorite = Favorite.toFavorite(favoriteDto);

        List<FavoriteMemoDto> favoriteMemoDtoList = new ArrayList<>();
        List<FavoriteMemo> favoriteMemoList = favoriteMemoRepository.findAllByMemberAndFavorite(member, favorite);

        for (FavoriteMemo favoriteMemo : favoriteMemoList) {

            // Entity -> Dto 변환
            FavoriteMemoDto favoriteMemoDto = FavoriteMemoDto.toFavoriteMemoDto(favoriteMemo);
            favoriteMemoDtoList.add(favoriteMemoDto);
        }

        return favoriteMemoDtoList;
    }

    // member, favorite 기준으로 FavoriteMemo 조회하기
    public List<FavoriteMemoDto> findAllByMemberAndFavorite(FavoritePageDto favoritePageDto) {

        MemberDto memberDto = favoritePageDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);
        Favorite favorite = Favorite.toFavorite(favoriteDto);

        List<FavoriteMemo> favoriteMemoList = favoriteMemoRepository.findAllByMemberAndFavorite(member, favorite);
        List<FavoriteMemoDto> favoriteMemoDtoList = new ArrayList<>();

        for (FavoriteMemo favoriteMemo : favoriteMemoList) {
            // Entity -> Dto 변환
            favoriteMemoDtoList.add(FavoriteMemoDto.toFavoriteMemoDto(favoriteMemo));
        }

        return favoriteMemoDtoList;
    }

    // FavoriteTodoMemo 삭제
    @Transactional
    public void deleteAllByFavoriteMemo(FavoritePageDto favoritePageDto) {

        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favoritePageDto);
        Favorite favorite = Favorite.toFavorite(favoriteDto);

        MemberDto memberDto = favoritePageDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        favoriteMemoRepository.deleteAllByMemberAndFavorite(member, favorite);
    }
}
