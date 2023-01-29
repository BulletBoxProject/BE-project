package com.hanghae.bulletbox.favorite.entity;

import com.hanghae.bulletbox.favorite.dto.FavoriteDto;
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
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = true)
    private Long categoryId;

    @Column(nullable = true)
    private String categoryColor;

    @Column(nullable = true)
    private String categoryName;

    @Column(nullable = true)
    private String favoriteContent;

    @Builder(access = AccessLevel.PRIVATE)
    public Favorite(Long favoriteId, Member member, Long categoryId, String categoryColor, String categoryName, String favoriteContent) {
        this.favoriteId = favoriteId;
        this.member = member;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.categoryName = categoryName;
        this.favoriteContent = favoriteContent;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public static Favorite toFavorite(FavoriteDto favoriteDto) {
        MemberDto memberDto = favoriteDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Long favoriteId = favoriteDto.getFavoriteId();
        String favoriteContent = favoriteDto.getFavoriteContent();
        Long categoryId = favoriteDto.getCategoryId();
        String categoryName = favoriteDto.getCategoryName();
        String categoryColor = favoriteDto.getCategoryColor();

        return Favorite.builder()
                .member(member)
                .favoriteId(favoriteId)
                .favoriteContent(favoriteContent)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }

    public void update(FavoriteDto favoriteDto) {
        MemberDto memberDto = favoriteDto.getMemberDto();

        member = Member.toMember(memberDto);
        favoriteId = favoriteDto.getFavoriteId();
        favoriteContent = favoriteDto.getFavoriteContent();
        categoryId = favoriteDto.getCategoryId();
        categoryName = favoriteDto.getCategoryName();
        categoryColor = favoriteDto.getCategoryColor();
    }
}
