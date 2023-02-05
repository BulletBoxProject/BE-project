package com.hanghae.bulletbox.favorite.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.favorite.entity.FavoriteMemo;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "루틴 메모 Dto")
public class FavoriteMemoDto {

    @Schema(description = "루틴 메모 ID", example = "1", type = "Long")
    private Long FavoriteMemoId;

    @Setter
    @Schema(description = "루틴 메모 내용", example = "루틴 메모 내용", type = "String")
    private String FavoriteMemoContent;

    @JsonIgnore
    @Setter
    @Schema(description = "유저 Dto")
    private MemberDto memberDto;

    @JsonIgnore
    @Setter
    @Schema(description = "루틴 Dto")
    private FavoriteDto favoriteDto;

    @Builder(access = AccessLevel.PRIVATE)
    private FavoriteMemoDto(Long favoriteMemoId, String favoriteMemoContent, MemberDto memberDto, FavoriteDto favoriteDto) {
        FavoriteMemoId = favoriteMemoId;
        FavoriteMemoContent = favoriteMemoContent;
        this.memberDto = memberDto;
        this.favoriteDto = favoriteDto;
    }

    public static FavoriteMemoDto toFavoriteMemoDto(FavoriteMemo favoriteMemo) {
        Favorite favorite = favoriteMemo.getFavorite();
        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favorite);

        Member member = favorite.getMember();
        MemberDto memberDto = MemberDto.toMemberDto(member);

        Long favoriteMemoId = favoriteMemo.getFavoriteMemoId();
        String favoriteMemoContent = favoriteMemo.getFavoriteMemoContent();

        return FavoriteMemoDto.builder()
                .memberDto(memberDto)
                .favoriteDto(favoriteDto)
                .favoriteMemoId(favoriteMemoId)
                .favoriteMemoContent(favoriteMemoContent)
                .build();
    }

    public static FavoriteMemoDto toFavoriteMemoDto(Long favoriteMemoId, String favoriteMemoContent) {
        return FavoriteMemoDto.builder()
                .favoriteMemoId(favoriteMemoId)
                .favoriteMemoContent(favoriteMemoContent)
                .build();
    }

    public static FavoriteMemoDto toFavoriteMemoDto(FavoriteDto responseFavoriteDto, Long favoriteMemoId, String favoriteMemoContent) {
        return FavoriteMemoDto.builder()
                .favoriteDto(responseFavoriteDto)
                .favoriteMemoId(favoriteMemoId)
                .favoriteMemoContent(favoriteMemoContent)
                .build();
    }

    public static FavoriteMemoDto toFavoriteMemoDto(Long favoriteMemoId, String favoriteMemoContent, Favorite favorite) {
        FavoriteDto favoriteDto = FavoriteDto.toFavoriteDto(favorite);

        return FavoriteMemoDto.builder()
                .favoriteMemoId(favoriteMemoId)
                .favoriteMemoContent(favoriteMemoContent)
                .favoriteDto(favoriteDto)
                .build();
    }
}
