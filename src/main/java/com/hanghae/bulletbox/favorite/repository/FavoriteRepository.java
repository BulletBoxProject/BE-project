package com.hanghae.bulletbox.favorite.repository;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Object> findByFavoriteIdAndMember(Long favoriteId, Member member);
}
