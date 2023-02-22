package com.hanghae.bulletbox.daily.service;

import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.daily.dto.DailyTodoDto;
import com.hanghae.bulletbox.daily.dto.ResponseDailyDto;
import com.hanghae.bulletbox.daily.dto.ResponseLoadFavoriteDto;
import com.hanghae.bulletbox.daily.dto.ResponseShowTodoCreatePageDto;
import com.hanghae.bulletbox.daily.dto.ResponseTodoUpdatePageDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import org.springframework.transaction.annotation.Transactional;

public interface DailyFacade {

    @Transactional(readOnly = true)
    ResponseDailyDto showDailyPage(MemberDto memberDto);

    @Transactional(readOnly = true)
    ResponseDailyDto showDailyPageChangeDay(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay);

    @Transactional(readOnly = true)
    ResponseCategoryDto showDailyByCategory(TodoDto todoDto);

    @Transactional(readOnly = true)
    ResponseShowTodoCreatePageDto showTodoCreatePage(DailyTodoDto dailyTodoDto);

    @Transactional
    void createTodo(DailyTodoDto dailyTodoDto);

    @Transactional
    void deleteTodo(MemberDto memberDto, Long todoId);

    @Transactional(readOnly = true)
    ResponseTodoUpdatePageDto showTodoUpdatePage(Long todoId, MemberDto memberDto);

    @Transactional
    void updateTodo(DailyTodoDto dailyTodoDto);

    @Transactional
    ResponseLoadFavoriteDto loadFavorite(Long favoriteId, DailyTodoDto dailyTodoDto);
}
