package com.hanghae.bulletbox.favorite.repository;

import com.hanghae.bulletbox.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
