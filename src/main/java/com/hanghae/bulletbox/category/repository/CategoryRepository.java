package com.hanghae.bulletbox.category.repository;

import com.hanghae.bulletbox.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
