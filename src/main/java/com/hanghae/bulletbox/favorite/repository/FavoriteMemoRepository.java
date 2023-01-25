package com.hanghae.bulletbox.favorite.repository;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.favorite.entity.FavoriteMemo;
import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteMemoRepository extends JpaRepository<FavoriteMemo, Long> {

    List<FavoriteMemo> findAllByMemberAndFavorite(Member member, Favorite favorite);

    void deleteAllByMemberAndFavorite(Member member, Favorite favorite);
}
