package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.diary.dto.CalendarDto;
import com.hanghae.bulletbox.diary.dto.DailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseChangeCalendarDto;
import com.hanghae.bulletbox.diary.dto.ResponseMainDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import com.hanghae.bulletbox.todo.entity.Memo;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.MemoRepository;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;
    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public ResponseMainDto showMainPage(Long memberId) {

        // 사용자 유효성 검사
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        // 카테고리 entity 대신 dto 사용
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);

        // ResponseMainDto 로 변환할 데이터 정제
        for (Category category : categoryList) {
            // 카테고리 entity -> toCategoryDto 변환
            categoryDtoList.add(CategoryDto.toCategoryDto(category.getCategoryId(), category.getCategoryName(), null));
        }

        // daily
        List<DailyDto> dailyDtoList = new ArrayList<>();
        List<Todo> todoList = todoRepository.findAllByMember(member);
        List<Memo> memoList = memoRepository.findAllByMember(member);

        // 투두 entity -> toDailyDto 변환
        for (Todo todo : todoList) {
            for (Memo memo : memoList) {
                if (memo.getTodo().equals(todo)) {
                    dailyDtoList.add(DailyDto.toDailyDto(todo, memoList));
                }
            }
        }

        // calendar
        // 메인 페이지 달력에서 일별 할 일의 개수를 세는 로직
        Map<Long, Long> countTodoPerDayMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            Long day = todo.getTodoDay();
            Long count;

            // Map 의 key 값에 day 가 존재하는 지에 따라 구분
            if (countTodoPerDayMap.containsKey(day)) {
                // Map key 값에 day 가 존재할 시, 해당 day 의 key 값의 value 를 불러와 +1
                countTodoPerDayMap.put(day, countTodoPerDayMap.get(day) + 1L);
            } else {
                // Map key 값에 day 가 없을 시, 새로운 K, V 추가
                countTodoPerDayMap.put(day, 1L);
            }

            count = countTodoPerDayMap.get(day);
            calendarDtoList.add(CalendarDto.toCalendar(day, count));
        }

        return ResponseMainDto.toResponseMainDto(categoryDtoList, calendarDtoList ,dailyDtoList);
    }

    public ResponseChangeCalendarDto changeCalendar(Long todoYear, Long todoMonth, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_MEMBER_MSG.getMsg())
        );

        // 해당 연도, 월 가져와 todoList 담기
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonth(member, todoYear, todoMonth);

        // 메인 페이지 달력에서 일별 할 일의 개수를 세는 로직
        Map<Long, Long> countTodoPerDayMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            Long day = todo.getTodoDay();
            Long count;

            // Map 의 key 값에 day 가 존재하는 지에 따라 구분
            if (countTodoPerDayMap.containsKey(day)) {
                // Map key 값에 day 가 존재할 시, 해당 day 의 key 값의 value 를 불러와 +1
                countTodoPerDayMap.put(day, countTodoPerDayMap.get(day) + 1L);
            } else {
                // Map key 값에 day 가 없을 시, 새로운 K, V 추가
                countTodoPerDayMap.put(day, 1L);
            }

            count = countTodoPerDayMap.get(day);
            calendarDtoList.add(CalendarDto.toCalendar(day, count));
        }

        return ResponseChangeCalendarDto.toResponseChangeCalendarDto(calendarDtoList);
    }
}
