package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.DailyDto;
import com.hanghae.bulletbox.todo.dto.ResponseDailyDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final CategoryService categoryService;

    private final TodoService todoService;

    private final TodoMemoService todoMemoService;

    // 데일리 로그 페이지 조회
    @Transactional(readOnly = true)
    public ResponseDailyDto showDailyPage(MemberDto memberDto) {
        Long todoYear = (long) LocalDate.now().getYear();
        Long todoMonth = (long) LocalDate.now().getMonthValue();
        Long todoDay = (long) LocalDate.now().getDayOfMonth();

        ResponseDailyDto responseDailyDto = showDailyPageChangeDay(memberDto, todoYear, todoMonth, todoDay);

        return responseDailyDto;
    }

    // 데일리 로그 조회 날짜 변경
    @Transactional(readOnly = true)
    public ResponseDailyDto showDailyPageChangeDay(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay) {
        // 해당 멤버의 카테고리들 조회
        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        // 해당 멤버의 오늘자 데일리 로그 조회
        List<TodoDto> todoDtoList = todoService.findAllDtoByMemberAndYearAndMonthAndDay(memberDto, todoYear, todoMonth, todoDay);

        // 각 할 일들의 메모 조회해서 dailyDto에 담기
        List<DailyDto> dailyDtoList = findAllMemoOfTodo(todoDtoList);

        ResponseDailyDto responseDailyDto = ResponseDailyDto.toResponseDailyDto(categoryDtoList, dailyDtoList);

        return responseDailyDto;
    }

    // 데일리 로그 카테고리별 조회
    @Transactional(readOnly = true)
    public ResponseCategoryDto showDailyByCategory(TodoDto todoDto){
        MemberDto memberDto = todoDto.getMemberDto();

        // 카테고리에 해당하는 Todo들 정보 찾기
        List<TodoDto> todoDtoList = todoService.findAllDtoByMemberAndCategoryIdAndYearAndMonthAndDay(todoDto);

        // 각 할 일들의 메모 조회해서 dailyDto에 담기
        List<DailyDto> dailyDtoList = findAllMemoOfTodo(todoDtoList);

        ResponseCategoryDto responseCategoryDto = ResponseCategoryDto.toResponseCategoryDto(dailyDtoList);

        return responseCategoryDto;
    }

    // 각 할 일들의 메모 조회해서 dailyDto에 담기
    private List<DailyDto> findAllMemoOfTodo(List<TodoDto> todoDtoList){
        List<DailyDto> dailyDtoList = new ArrayList<>();

        for(TodoDto todoDto: todoDtoList){
            List<TodoMemoDto> todoMemoDtoList = todoMemoService.findAllMemoByTodo(todoDto);
            DailyDto dailyDto = DailyDto.toDailyDto(todoDto, todoMemoDtoList);

            dailyDtoList.add(dailyDto);
        }

        return dailyDtoList;
    }
}