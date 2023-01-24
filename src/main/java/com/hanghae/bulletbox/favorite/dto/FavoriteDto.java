package com.hanghae.bulletbox.favorite.dto;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteDto {

    private MemberDto memberDto;

    private Long favoriteId;

    private String favoriteContent;

    private Long categoryId;

    private String categoryName;

    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private FavoriteDto(MemberDto memberDto, Long favoriteId, String favoriteContent, Long categoryId, String categoryName, String categoryColor) {
        this.memberDto = memberDto;
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public static FavoriteDto toFavoriteDto(Favorite favorite) {
        Member member = favorite.getMember();
        MemberDto memberDto = MemberDto.toMemberDto(member);

        Long favoriteId = favorite.getFavoriteId();
        String favoriteContent = favorite.getFavoriteContent();
        Long categoryId = favorite.getCategoryId();
        String categoryName = favorite.getCategoryName();
        String categoryColor = favorite.getCategoryColor();

        return FavoriteDto.builder()
                .memberDto(memberDto)
                .favoriteId(favoriteId)
                .favoriteContent(favoriteContent)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }

    public static FavoriteDto toFavoriteDto(FavoritePageDto favoritePageDto) {
        MemberDto memberDto = favoritePageDto.getMemberDto();
        String favoriteContent = favoritePageDto.getFavoriteContent();
        Long categoryId = favoritePageDto.getCategoryId();
        String categoryName = favoritePageDto.getCategoryName();
        String categoryColor = favoritePageDto.getCategoryColor();

        return FavoriteDto.builder()
                .memberDto(memberDto)
                .favoriteContent(favoriteContent)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }
}
