package com.hanghae.bulletbox.category.repository;

import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByMember(Member member);

    Optional<Category> findAllByMemberAndCategoryName(Member member, String categoryName);

    Optional<Category> findByCategoryIdAndMember(Long categoryId, Member member);

}
