package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.todo.dto.*;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.TodoMemo;
import com.hanghae.bulletbox.todo.entity.Todo;

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

    private final CategoryService categoryService;

    private final TodoService todoService;

    private final TodoMemoService todoMemoService;

    // 데일리 로그 페이지 조회
    @Transactional(readOnly = true)
    public ResponseDailyDto showDailyPage(MemberDto memberDto) {

        // 해당 멤버의 카테고리들 조회
        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        // 해당 멤버의 오늘자 데일리 로그 조회
        Long todoYear = (long) LocalDate.now().getYear();
        Long todoMonth = (long) LocalDate.now().getMonthValue();
        Long todoDay = (long) LocalDate.now().getDayOfMonth();
        List<TodoDto> todoDtoList = todoService.findAllDtoByMemberAndYearAndMonthAndDay(memberDto, todoYear, todoMonth, todoDay);

        // 각 할 일들의 메모 조회해서 dailyDto에 담기
        List<DailyDto> dailyDtoList = new ArrayList<>();

        for(TodoDto todoDto: todoDtoList){
            List<TodoMemoDto> todoMemoDtoList = todoMemoService.findAllMemoByTodo(todoDto);
            DailyDto dailyDto = DailyDto.toDailyDto(todoDto, todoMemoDtoList);

            dailyDtoList.add(dailyDto);
        }

        return ResponseDailyDto.toResponseDailyDto(categoryDtoList, dailyDtoList);
    }

    public ResponseShowDailyDto showDailyPageChangeDay(TodoDto todoDto) {
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        List<DailyDto> dailyDtoList = new ArrayList<>();
        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(member, todoYear, todoMonth, todoDay);

        for (Todo todo : todoList) {
            List<TodoMemo> todoMemoList = todoMemoRepository.findAllByMemberAndTodo(member, todo);

            dailyDtoList.add(DailyDto.toDailyDto(todo, todoMemoList));
        }

        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);

        for (Category category : categoryList) {

            categoryDtoList.add(CategoryDto.toCategoryDto(category.getCategoryId(), category.getCategoryName(), category.getCategoryColor()));
        }

        return ResponseShowDailyDto.toResponseShowDailyDto(categoryDtoList, dailyDtoList);
    }

    public ResponseCategoryDto showDailyByCategory(TodoDto todoDto){
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        List<DailyDto> dailyDtoList = new ArrayList<>();
        Long categoryId = todoDto.getCategoryId();
        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();
        List<Todo> todoList = todoRepository.findAllByMemberAndCategoryIdAndTodoYearAndTodoMonthAndTodoDay(member, categoryId, todoYear, todoMonth, todoDay);

        for (Todo todo : todoList) {
            List<TodoMemo> todoMemoList = todoMemoRepository.findAllByMemberAndTodo(member, todo);

            dailyDtoList.add(DailyDto.toDailyDto(todo, todoMemoList));
        }

        return ResponseCategoryDto.toResponseCategoryDto(dailyDtoList);
    }

}