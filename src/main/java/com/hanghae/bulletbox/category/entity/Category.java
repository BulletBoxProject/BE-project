package com.hanghae.bulletbox.category.entity;

import com.hanghae.bulletbox.common.entity.TimeStamped;
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
public class Category extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = true)
    private String categoryName;

    @Column(nullable = true)
    private String categoryColor;

    @Builder(access = AccessLevel.PRIVATE)
    private Category(Member member, String categoryName, String categoryColor) {
        this.member = member;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public static Category toCategory(Member member, String categoryName, String categoryColor) {
        return Category.builder()
                .member(member)
                .categoryName(categoryName)
                .categoryColor(categoryColor)
                .build();
    }

    public void update(String categoryName, String categoryColor) {
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }
}
