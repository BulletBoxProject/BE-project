package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.entity.Category;
import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.diary.dto.CalendarDto;
import com.hanghae.bulletbox.diary.dto.DailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowCalendarDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowMainPageDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.entity.TodoMemo;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.TodoMemoRepository;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainService {

    private final CategoryRepository categoryRepository;

    private final TodoRepository todoRepository;

    private final TodoMemoRepository todoMemoRepository;

    @Transactional(readOnly = true)
    public ResponseShowMainPageDto showMainPage(TodoDto todoDto) {
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        // category 정보 가져오기
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAllByMember(member);
        for (Category category : categoryList) {

            Long categoryId = category.getCategoryId();
            String categoryName = category.getCategoryName();

            categoryDtoList.add(CategoryDto.toCategoryDto(categoryId, categoryName));
        }

        // daily 정보 가져오기
        List<DailyDto> dailyDtoList = new ArrayList<>();
        // 메인 페이지 조회 시, 현재 연도, 월의 달력을 보여주기 위해 현재 날짜로 연월 설정
        Long todoYear = (long) LocalDate.now().getYear();
        Long todoMonth = (long) LocalDate.now().getMonthValue();
        Long todoDay = (long) LocalDate.now().getDayOfMonth();
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(member, todoYear, todoMonth, todoDay);

        for (Todo todo : todoList) {
            List<TodoMemo> todoMemoList = todoMemoRepository.findAllByMemberAndTodo(member, todo);
            for (TodoMemo todoMemo : todoMemoList) {
                if (todoMemo.getTodo().equals(todo)) {
                    dailyDtoList.add(DailyDto.toDailyDto(todo, todoMemoList));
                    break;
                }
            }
        }

        // calendar 정보 가져오기
        // 메인 페이지 달력에서 일별 할 일의 개수를 세는 로직
        Map<Long, Long> countTodoPerDayMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            Long day = todo.getTodoDay();
            Long count;
            boolean isContainsKeyOfDay = countTodoPerDayMap.containsKey(day);

            // Map 의 key 값에 day 가 존재하는 지에 따라 구분
            if (!isContainsKeyOfDay) {
                // Map key 값에 day 가 없을 시, 새로운 K, V 추가
                countTodoPerDayMap.put(day, 1L);
            } else {
                // Map key 값에 day 가 존재할 시, 해당 day 의 key 값의 value 를 불러와 +1
                countTodoPerDayMap.put(day, countTodoPerDayMap.get(day) + 1L);
            }

            count = countTodoPerDayMap.get(day);
            calendarDtoList.add(CalendarDto.toCalendar(day, count));
        }

        return ResponseShowMainPageDto.toResponseShowMainPageDto(categoryDtoList, calendarDtoList ,dailyDtoList);
    }

    @Transactional(readOnly = true)
    public ResponseShowCalendarDto showCalendar(TodoDto todoDto) {

        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonth(member, todoYear, todoMonth);

        // 메인 페이지 달력에서 일별 할 일의 개수를 세는 로직
        Map<Long, Long> countTodoPerDayMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            Long day = todo.getTodoDay();
            Long count;
            boolean isContainsKeyOfDay = countTodoPerDayMap.containsKey(day);

            // Map 의 key 값에 day 가 존재하는 지에 따라 구분
            if (!isContainsKeyOfDay) {
                // Map key 값에 day 가 없을 시, 새로운 K, V 추가
                countTodoPerDayMap.put(day, 1L);
            } else {
                // Map key 값에 day 가 존재할 시, 해당 day 의 key 값의 value 를 불러와 +1
                countTodoPerDayMap.put(day, countTodoPerDayMap.get(day) + 1L);
            }

            count = countTodoPerDayMap.get(day);
            calendarDtoList.add(CalendarDto.toCalendar(day, count));
        }

        return ResponseShowCalendarDto.toResponseChangeCalendarDto(calendarDtoList);
    }

    // 메인 페이지 내 데일리 로그 조회
    @Transactional(readOnly = true)
    public ResponseShowDailyDto showDaily(TodoDto todoDto) {

        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();
        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(member, todoYear, todoMonth, todoDay);
        List<DailyDto> dailyDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            List<TodoMemo> todoMemoList = todoMemoRepository.findAllByMemberAndTodo(member, todo);
            for (TodoMemo todoMemo : todoMemoList) {
                if (todoMemo.getTodo().equals(todo)) {
                    dailyDtoList.add(DailyDto.toDailyDto(todo, todoMemoList));
                    break;
                }
            }
        }

        return ResponseShowDailyDto.toResponseShowDailyDto(dailyDtoList);
    }
}
