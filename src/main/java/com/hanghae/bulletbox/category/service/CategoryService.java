package com.hanghae.bulletbox.category.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;

import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 멤버로 해당 멤버의 전체 카테고리 조회
    @Transactional(readOnly = true)
    public List<CategoryDto> findAllCategory(Member member){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);

        for (Category category : categoryList) {
            Long categoryId = category.getCategoryId();
            String categoryName = category.getCategoryName();
            String categoryColor = category.getCategoryColor();

            // Entity -> Dto 변환
            categoryDtoList.add(CategoryDto.toCategoryDto(categoryId, categoryName, categoryColor));
        }
        return categoryDtoList;
    }

}
