package com.hanghae.bulletbox.daily.service;

import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.daily.dto.DailyTodoDto;
import com.hanghae.bulletbox.daily.dto.ResponseDailyDto;
import com.hanghae.bulletbox.daily.dto.ResponseLoadFavoriteDto;
import com.hanghae.bulletbox.daily.dto.ResponseShowTodoCreatePageDto;
import com.hanghae.bulletbox.daily.dto.ResponseTodoUpdatePageDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyFacadeImpl implements DailyFacade {

    private final DailyService dailyService;

    private final DailyTodoService dailyTodoService;

    @Override
    public ResponseDailyDto showDailyPage(MemberDto memberDto) {
        return dailyService.showDailyPage(memberDto);
    }

    @Override
    public ResponseDailyDto showDailyPageChangeDay(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay) {
        return dailyService.showDailyPageChangeDay(memberDto, todoYear, todoMonth, todoDay);
    }

    @Override
    public ResponseCategoryDto showDailyByCategory(TodoDto todoDto) {
        return dailyService.showDailyByCategory(todoDto);
    }

    @Override
    public ResponseShowTodoCreatePageDto showTodoCreatePage(DailyTodoDto dailyTodoDto) {
        return dailyTodoService.showTodoCreatePage(dailyTodoDto);
    }

    @Override
    public void createTodo(DailyTodoDto dailyTodoDto) {
        dailyTodoService.createTodo(dailyTodoDto);
    }

    @Override
    public void deleteTodo(MemberDto memberDto, Long todoId) {
        dailyTodoService.deleteTodo(memberDto, todoId);
    }

    @Override
    public ResponseTodoUpdatePageDto showTodoUpdatePage(Long todoId, MemberDto memberDto) {
        return dailyTodoService.showTodoUpdatePage(todoId, memberDto);
    }

    @Override
    public void updateTodo(DailyTodoDto dailyTodoDto) {
        dailyTodoService.updateTodo(dailyTodoDto);
    }

    @Override
    public ResponseLoadFavoriteDto loadFavorite(Long favoriteId, DailyTodoDto dailyTodoDto) {
        return dailyTodoService.loadFavorite(favoriteId, dailyTodoDto);
    }
}
