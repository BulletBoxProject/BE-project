package com.hanghae.bulletbox.todo.entity;

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
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FavoriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = true)
    private Long categoryId;

    @Column(nullable = true)
    private String categoryColor;

    @Column(nullable = true)
    private String favoriteContent;

    @OneToMany
    @JoinColumn(name = "FAVORITE_ID")
    private List<FavoriteMemo> favoriteMemoList = new ArrayList<>();

    public Favorite(Member member, Long categoryId,
                    String categoryColor, String favoriteContent) {
        this.member = member;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.favoriteContent = favoriteContent;
    }
}
