package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.diary.dto.DailyTodoDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowTodoCreatePageDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DailyTodoService {

    private final CategoryService categoryService;

    // 데일리 로그 할 일 추가 페이지 조회
    @Transactional
    public ResponseShowTodoCreatePageDto showTodoCreatePage(DailyTodoDto dailyTodoDto) {
        Member member = dailyTodoDto.getMember();
        Long year = dailyTodoDto.getYear();
        Long month = dailyTodoDto.getMonth();
        Long day = dailyTodoDto.getDay();

        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(member);

        return ResponseShowTodoCreatePageDto.toResponseShowTodoCreatePageDto(categoryDtoList, year, month, day);
    }
}
