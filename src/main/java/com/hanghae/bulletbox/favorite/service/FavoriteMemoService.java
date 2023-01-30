package com.hanghae.bulletbox.favorite.service;

import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
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

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_FAVORITEMEMO_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_FAVORITE_MSG;
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
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_FAVORITE_MSG.getMsg()));
    }

    // 루틴의 메모 생성
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


    // member, favorite 기준으로 루틴의 메모 찾기
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

    // 루틴 삭제
    @Transactional
    public void deleteAllByFavoriteMemo(FavoriteDto favoriteDto) {

        Favorite favorite = Favorite.toFavorite(favoriteDto);

        MemberDto memberDto = favoriteDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        favoriteMemoRepository.deleteAllByMemberAndFavorite(member, favorite);
    }

    // 루틴 삭제(By Id)
    @Transactional
    public void deleteFavoriteMemoById(Long favoriteMemoId) {

        favoriteMemoRepository.deleteById(favoriteMemoId);
    }

    // 루틴의 메모 업데이트 (For dirty checking)
    @Transactional
    public void updateFavoriteMemo(FavoriteMemoDto favoriteMemoDto) {

        Long favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();
        MemberDto memberDto = favoriteMemoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

        FavoriteMemo favoriteMemo = findFavoriteMemoByIdAndMember(favoriteMemoId, member);

        // Dto의 메모 내용과 엔티티의 메모 내용이 같을 경우, 수정이 진행되지 않았기 때문에 해당 메서드를 진행할 이유가 없음. => return;
        if (favoriteMemo.getFavoriteMemoContent().equals(favoriteMemoContent)) {
            return;
        }

        favoriteMemo.update(favoriteMemoDto);
    }

    // member, favoriteIdMemo 를 기준으로 루틴의 메모 조회
    @Transactional(readOnly = true)
    protected FavoriteMemo findFavoriteMemoByIdAndMember(Long favoriteMemoId, Member member) {
        return favoriteMemoRepository.findByFavoriteMemoIdAndMember(favoriteMemoId, member).orElseThrow(
                () -> new NoSuchElementException(NOT_FOUND_FAVORITEMEMO_MSG.getMsg())
        );
    }

    // Favorite으로 루틴의 메모 리스트 조회
    @Transactional(readOnly = true)
    public List<FavoriteMemoDto> findAllDtoByFavorite(FavoriteDto favoriteDto) {
        Favorite favorite = Favorite.toFavorite(favoriteDto);
        MemberDto memberDto = favoriteDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        List<FavoriteMemo> favoriteMemoList = favoriteMemoRepository.findAllByMemberAndFavorite(member, favorite);
        List<FavoriteMemoDto> favoriteMemoDtoList = new ArrayList<>();

        // 메모가 없을 경우 null 반환
        if(favoriteMemoList == null){
            favoriteMemoDtoList = null;

            return favoriteMemoDtoList;
        }

        // 메모가 있을 경우 메모 리스트를 dto로 만들어서 반환
        for(FavoriteMemo favoriteMemo : favoriteMemoList){
            FavoriteMemoDto favoriteMemoDto = FavoriteMemoDto.toFavoriteMemoDto(favoriteMemo);

            favoriteMemoDtoList.add(favoriteMemoDto);
        }

        return favoriteMemoDtoList;
    }
}
