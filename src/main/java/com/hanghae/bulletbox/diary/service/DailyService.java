package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.diary.dto.DailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseDailyDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;
import com.hanghae.bulletbox.todo.entity.Memo;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.MemoRepository;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final MemberRepository memberRepository;

    private final CategoryRepository categoryRepository;

    private final TodoRepository todoRepository;

    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public ResponseDailyDto showDailyPage(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_MEMBER_MSG.getMsg())
        );

        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);

        for (Category category : categoryList) {

            categoryDtoList.add(CategoryDto.toCategoryDto(category.getCategoryId(), category.getCategoryName(), null));
        }

        List<DailyDto> dailyDtoList = new ArrayList<>();
        Long todoYear = (long) LocalDate.now().getYear();
        Long todoMonth = (long) LocalDate.now().getMonthValue();
        Long todoDay = (long) LocalDate.now().getDayOfMonth();
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(member, todoYear, todoMonth, todoDay);

        List<Memo> memoList = memoRepository.findAllByMember(member);
        for (Todo todo : todoList) {
            for (Memo memo : memoList) {
                if (memo.getTodo().equals(todo)) {
                    dailyDtoList.add(DailyDto.toDailyDto(todo, memoList));
                }
            }
        }

        return ResponseDailyDto.toResponseDailyDto(categoryDtoList, dailyDtoList, true);
    }

    @Transactional(readOnly = true)
    public ResponseCategoryDto showDailyByCategory(Long memberId,Long categoryId, Long todoYear, Long todoMonth, Long todoDay){

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_MEMBER_MSG.getMsg())
        );

        List<DailyDto> dailyDtoList = new ArrayList<>();
        todoYear = (long) LocalDate.now().getYear();
        todoMonth = (long) LocalDate.now().getMonthValue();
        todoDay = (long) LocalDate.now().getDayOfMonth();
        List<Todo> todoList = todoRepository.findAllByCategoryIdAndTodoYearAndTodoMonthAndTodoDay(categoryId, todoYear, todoMonth, todoDay);

        List<Memo> memoList = memoRepository.findAllByMember(member);
        for (Todo todo : todoList) {
            for (Memo memo : memoList) {
                if (memo.getTodo().equals(todo)) {
                    dailyDtoList.add(DailyDto.toDailyDto(todo, memoList));
                }
            }
        }

        return ResponseCategoryDto.toResponseCategoryDto(dailyDtoList);
    }

}