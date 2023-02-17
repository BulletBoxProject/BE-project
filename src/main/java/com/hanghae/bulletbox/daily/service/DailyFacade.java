package com.hanghae.bulletbox.daily.service;

import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.daily.dto.*;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import org.springframework.transaction.annotation.Transactional;

public interface DailyFacade {

    // 데일리 로그 할 일 추가 페이지 조회
    @Transactional(readOnly = true)
    ResponseShowTodoCreatePageDto showTodoCreatePage(DailyTodoDto dailyTodoDto);

    // 데일리 로그 할 일 추가
    @Transactional
    void createTodo(DailyTodoDto dailyTodoDto);

    // 할 일 삭제하기
    @Transactional
    void deleteTodo(MemberDto memberDto, Long todoId);

    // 할 일 수정 페이지 조회하기
    @Transactional(readOnly = true)
    ResponseTodoUpdatePageDto showTodoUpdatePage(Long todoId, MemberDto memberDto);

    // 할 일 수정하기
    @Transactional
    void updateTodo(DailyTodoDto dailyTodoDto);

    // 루틴 불러와서 할 일로 저장하기
    @Transactional
    ResponseLoadFavoriteDto loadFavorite(Long favoriteId, DailyTodoDto dailyTodoDto);

    // 데일리 로그 페이지 조회
    @Transactional(readOnly = true)
    ResponseDailyDto showDailyPage(MemberDto memberDto);

    // 데일리 로그 조회 날짜 변경
    @Transactional(readOnly = true)
    ResponseDailyDto showDailyPageChangeDay(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay);

    // 데일리 로그 카테고리별 조회
    @Transactional(readOnly = true)
    ResponseCategoryDto showDailyByCategory(TodoDto todoDto);
}
