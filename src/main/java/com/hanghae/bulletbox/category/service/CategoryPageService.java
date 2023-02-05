package com.hanghae.bulletbox.category.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseCreateCategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseDeleteCategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseShowCategoryDto;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_CATEGORYNAME_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_CATEGORY_MSG;

@Service
@RequiredArgsConstructor
public class CategoryPageService {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    @Transactional(readOnly = true)
    public ResponseShowCategoryDto showCategory(CategoryDto categoryDto) {

        // 사용자의 카테고리 가져오기
        MemberDto memberDto = categoryDto.getMemberDto();

        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        // Dto -> 응답 Dto 변환 후, 리턴
        return ResponseShowCategoryDto.toResponseShowCategoryDto(categoryDtoList);
    }

    // 카테고리 생성
    @Transactional
    public ResponseCreateCategoryDto createCategory(CategoryDto categoryDto) {

        // 카테고리 중복 검사
        boolean isCategoryDuplicated = categoryService.isCategoryDuplicated(categoryDto);

        if (isCategoryDuplicated) {
            throw new IllegalArgumentException(DUPLICATE_CATEGORYNAME_MSG.getMsg());
        }

        CategoryDto savedCategoryDto = categoryService.save(categoryDto);

        return ResponseCreateCategoryDto.toResponseCreateCategoryDto(savedCategoryDto);
    }

    // 카테고리 수정
    @Transactional
    public void updateCategory(CategoryDto categoryDto) {

        // 카테고리 유효성 검사
        boolean isCategoryDuplicated = categoryService.isCategoryDuplicated(categoryDto);

        if (isCategoryDuplicated) {
            throw new IllegalArgumentException(NOT_FOUND_CATEGORY_MSG.getMsg());
        }

        // 카테고리 수정
        categoryService.update(categoryDto);
    }

    @Transactional
    public ResponseDeleteCategoryDto deleteCategory(CategoryDto categoryDto) {

        // 카테고리 존재 여부 확인
        boolean isCategoryDuplicated = categoryService.isCategoryDuplicated(categoryDto);

        if (isCategoryDuplicated) {
            throw new IllegalArgumentException(NOT_FOUND_CATEGORY_MSG.getMsg());
        }

        // 카테고리 삭제
        Long categoryId = categoryService.deleteById(categoryDto);

        return ResponseDeleteCategoryDto.toResponseDeleteCategoryDto(categoryId);
    }
}
