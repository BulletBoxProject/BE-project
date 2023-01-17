package com.hanghae.bulletbox.category.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseShowCategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    // 카테고리 목록 조회
    public ResponseShowCategoryDto showCategory(CategoryDto categoryDto) {

        // 사용자 유효성 검사
        Long memberId = categoryDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_MEMBER_MSG.getMsg())
        );

        // 사용자의 카테고리 가져오기
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
}
