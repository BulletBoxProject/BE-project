package com.hanghae.bulletbox.favorite.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "루틴 Dto")
public class FavoriteDto {

    @JsonIgnore
    @Schema(description = "유저 Dto")
    private MemberDto memberDto;

    @Schema(description = "루틴 ID", example = "1", type = "Long")
    private Long favoriteId;

    @Schema(description = "루틴 내용", example = "루틴 내용", type = "String")
    private String favoriteContent;

    @Schema(description = "카테고리 ID", example = "1", type = "Long")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "카테고리 이름", type = "String")
    @JsonIgnore
    private String categoryName;

    @Schema(description = "카테고리 색상", example = "#123456", type = "String")
    private String categoryColor;

    @Schema(description = "루틴 메모", type = "List")
    private List<FavoriteMemoDto> favoriteMemos;

    @Builder(access = AccessLevel.PRIVATE)
    private FavoriteDto(MemberDto memberDto, Long favoriteId, String favoriteContent, Long categoryId, String categoryName, String categoryColor, List<FavoriteMemoDto> favoriteMemos) {
        this.memberDto = memberDto;
        this.favoriteId = favoriteId;
        this.favoriteContent = favoriteContent;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.favoriteMemos = favoriteMemos;
    }

    public void setFavoriteMemos(List<FavoriteMemoDto> favoriteMemos) {
        this.favoriteMemos = favoriteMemos;
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
        Long favoriteId = favoritePageDto.getFavoriteId();
        String favoriteContent = favoritePageDto.getFavoriteContent();
        List<FavoriteMemoDto> favoriteMemos = favoritePageDto.getFavoriteMemos();
        Long categoryId = favoritePageDto.getCategoryId();
        String categoryName = favoritePageDto.getCategoryName();
        String categoryColor = favoritePageDto.getCategoryColor();

        return FavoriteDto.builder()
                .memberDto(memberDto)
                .favoriteId(favoriteId)
                .favoriteContent(favoriteContent)
                .favoriteMemos(favoriteMemos)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }
}
