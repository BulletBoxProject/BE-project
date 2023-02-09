package com.hanghae.bulletbox.favorite.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FavoriteMemo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteMemoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FAVORITE_ID", nullable = false)
    private Favorite favorite;

    @Column(nullable = false)
    private String favoriteMemoContent;

    @Builder(access = AccessLevel.PRIVATE)
    private FavoriteMemo(Member member, Favorite favorite, String favoriteMemoContent) {
        this.member = member;
        this.favorite = favorite;
        this.favoriteMemoContent = favoriteMemoContent;
    }

    public static FavoriteMemo toFavoriteMemo(FavoriteMemoDto favoriteMemoDto) {
        FavoriteDto favoriteDto = favoriteMemoDto.getFavoriteDto();
        Favorite favorite = Favorite.toFavorite(favoriteDto);

        MemberDto memberDto = favoriteDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

        return FavoriteMemo.builder()
                .favorite(favorite)
                .member(member)
                .favoriteMemoContent(favoriteMemoContent)
                .build();
    }

    public void update(FavoriteMemoDto favoriteMemoDto) {

        MemberDto memberDto = favoriteMemoDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        Long favoriteMemoId = favoriteMemoDto.getFavoriteMemoId();
        String favoriteMemoContent = favoriteMemoDto.getFavoriteMemoContent();

        this.member = member;
        this.favoriteMemoId = favoriteMemoId;
        this.favoriteMemoContent = favoriteMemoContent;
    }
}
