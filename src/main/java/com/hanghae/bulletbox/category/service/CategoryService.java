package com.hanghae.bulletbox.category.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.favorite.entity.Favorite;
import com.hanghae.bulletbox.favorite.repository.FavoriteRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_CATEGORYNAME_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_CATEGORY_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NO_AUTHORIZATION_MSG;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final TodoRepository todoRepository;

    private final FavoriteRepository favoriteRepository;

    // 멤버로 해당 멤버의 전체 카테고리 조회
    @Transactional(readOnly = true)
    public List<CategoryDto> findAllCategory(MemberDto memberDto) {

        Member member = Member.toMember(memberDto);

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

    // 카테고리 중복 검사
    @Transactional(readOnly = true)
    public boolean isCategoryDuplicated(CategoryDto categoryDto) {

        MemberDto memberDto = categoryDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        String categoryName = categoryDto.getCategoryName();

        Optional<Category> categoryOptional = categoryRepository.findAllByMemberAndCategoryName(member, categoryName);

        return categoryOptional.isPresent();
    }

    // 카테고리 중복 검사 (수정)
    @Transactional(readOnly = true)
    public boolean isUpdateCategoryDuplicated(CategoryDto categoryDto) {

        MemberDto memberDto = categoryDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        String categoryName = categoryDto.getCategoryName();
        Long categoryId = categoryDto.getCategoryId();

        Category category = categoryRepository.findAllByMemberAndCategoryId(member, categoryId).orElseThrow(
                () -> new NoSuchElementException(NOT_FOUND_CATEGORY_MSG.getMsg())
        );

        // 카테고리의 색상만 변경할 경우, 기존의 카테고리 이름과 요청 온 카테고리 이름을 비교 -> 같으면 false 리턴
        if (categoryName.equals(category.getCategoryName())) {
            return false;
        }

        Optional<Category> categoryOptional = categoryRepository.findAllByMemberAndCategoryName(member, categoryName);

        return categoryOptional.isPresent();
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {

        boolean categoryDuplicated = isCategoryDuplicated(categoryDto);

        if (categoryDuplicated) {
            throw new IllegalArgumentException(DUPLICATE_CATEGORYNAME_MSG.getMsg());
        }

        Category category = Category.toCategory(categoryDto);

        categoryRepository.save(category);

        CategoryDto savedCategoryDto = CategoryDto.toCategoryDto(category);

        return savedCategoryDto;
    }

    // 카테고리 수정하기
    @Transactional
    public void update(CategoryDto categoryDto) {

        Long categoryId = categoryDto.getCategoryId();
        String categoryName = categoryDto.getCategoryName();
        String categoryColor = categoryDto.getCategoryColor();
        MemberDto memberDto = categoryDto.getMemberDto();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CATEGORY_MSG.getMsg()));

        // 같은 유저인지 확인
        checkMember(memberDto, category);

        category.update(categoryName, categoryColor);
    }

    // 카테고리 삭제
    @Transactional
    public Long deleteById(CategoryDto categoryDto) {

        Long categoryId = categoryDto.getCategoryId();
        MemberDto memberDto = categoryDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CATEGORY_MSG.getMsg()));

        // 같은 유저인지 확인
        checkMember(memberDto, category);

        categoryRepository.deleteById(categoryId);

        // 해당 카테고리를 사용하던 모든 Todo와 Routine 찾아서 null로 변경
        List<Todo> todoList = todoRepository.findAllByMemberAndCategoryId(member, categoryId);
        List<Favorite> favoriteList = favoriteRepository.findAllByMemberAndCategoryId(member, categoryId);

        for (Todo todo : todoList) {
            todo.updateCategory(null, null);
        }

        for (Favorite favorite : favoriteList) {
            favorite.updateCategory(null, null, null);
        }

        return categoryId;
    }

    // 같은 유저인지 확인
    private void checkMember(MemberDto memberDto, Category category) {

        Long requestMemberId = memberDto.getMemberId();

        Member member = category.getMember();
        Long memberId = member.getMemberId();

        if (!memberId.equals(requestMemberId)) {
            throw new IllegalArgumentException(NO_AUTHORIZATION_MSG.getMsg());
        }
    }
}
