package com.hanghae.bulletbox.favorite.repository;

import com.hanghae.bulletbox.favorite.entity.FavoriteMemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMemoRepository extends JpaRepository<FavoriteMemo, Long> {
}
