package com.hanghae.bulletbox.favorite.entity;

import com.hanghae.bulletbox.member.entity.Member;

import lombok.AccessLevel;
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
public class FavoriteMemo {
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

    public FavoriteMemo(Member member, Favorite favorite, String favoriteMemoContent) {
        this.member = member;
        this.favorite = favorite;
        this.favoriteMemoContent = favoriteMemoContent;
    }
}
