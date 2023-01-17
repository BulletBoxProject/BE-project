package com.hanghae.bulletbox.category.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseShowCategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_CATEGORYNAME_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_CATEGORY_MSG;

@Service
@RequiredArgsConstructor
public class CategoryPageService {

    private final CategoryRepository categoryRepository;

    // 카테고리 목록 조회
    @Transactional(readOnly = true)
    public ResponseShowCategoryDto showCategory(CategoryDto categoryDto) {

        // 사용자의 카테고리 가져오기
        Member member = categoryDto.getMember();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);

        for (Category category : categoryList) {

            Long categoryId = category.getCategoryId();
            String categoryName = category.getCategoryName();
            String categoryColor = category.getCategoryColor();

            // Entity -> Dto 변환
            categoryDtoList.add(CategoryDto.toCategoryDto(categoryId, categoryName, categoryColor));
        }

        // Dto -> 응답 Dto 변환 후, 리턴
        return ResponseShowCategoryDto.toResponseShowCategoryDto(categoryDtoList);
    }

    // 카테고리 생성
    @Transactional(readOnly = false)
    public void createCategory(CategoryDto categoryDto) {

        // 카테고리 중복 검사
        Member member = categoryDto.getMember();
        String categoryName = categoryDto.getCategoryName();

        categoryRepository.findAllByMemberAndCategoryName(member, categoryName).ifPresent(
                m -> {
                    throw new IllegalArgumentException(DUPLICATE_CATEGORYNAME_MSG.getMsg());
                }
        );

        // DTO -> Entity 변환
        String categoryColor = categoryDto.getCategoryColor();
        Category category = Category.toCategory(member, categoryName, categoryColor);

        categoryRepository.save(category);
    }

    @Transactional(readOnly = false)
    public void updateCategory(CategoryDto categoryDto) {

        // 카테고리 유효성 검사
        Member member = categoryDto.getMember();
        Long categoryId = categoryDto.getCategoryId();

        Category category = categoryRepository.findAllByMemberAndCategoryId(member, categoryId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_CATEGORY_MSG.getMsg())
        );

        // 카테고리 수정
        String categoryName = categoryDto.getCategoryName();
        String categoryColor = categoryDto.getCategoryColor();

        category.update(categoryName, categoryColor);
    }
}
