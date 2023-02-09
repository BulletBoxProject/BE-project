package com.hanghae.bulletbox.main.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.main.dto.CalendarDto;
import com.hanghae.bulletbox.daily.dto.DailyDto;
import com.hanghae.bulletbox.main.dto.ResponseShowCalendarDto;
import com.hanghae.bulletbox.main.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.main.dto.ResponseShowMainPageDto;
import com.hanghae.bulletbox.member.service.MemberService;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.service.TodoMemoService;
import com.hanghae.bulletbox.todo.service.TodoService;

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

    private final CategoryService categoryService;

    private final TodoService todoService;

    private final TodoMemoService todoMemoService;

    private final MemberService memberService;

    // 메인 페이지 조회
    @Transactional
    public ResponseShowMainPageDto showMainPage(TodoDto todoDto) {

        MemberDto memberDto = todoDto.getMemberDto();

        // category 정보 가져오기
        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        // 할 일 정보 가져오기
        Long todoYear = (long) LocalDate.now().getYear();
        Long todoMonth = (long) LocalDate.now().getMonthValue();
        Long todoDay = (long) LocalDate.now().getDayOfMonth();

        List<TodoDto> todoDtoList = todoService.findAllDtoByMemberAndYearAndMonthAndDay(memberDto, todoYear, todoMonth, todoDay);

        // 각 할 일들의 메모 조회해서 dailyDto에 담기
        List<DailyDto> dailyDtoList = todoMemoService.makeDailyDtoListWithMemo(todoDtoList);

        // calendar 정보 가져오기
        // 메인 페이지 달력에서 일별 할 일의 개수를 세는 로직
        List<TodoDto> calendarTodoDtoList = todoService.findAllDtoByMemberAndYearAndMonth(memberDto, todoYear, todoMonth);

        List<CalendarDto> calendarDtoList = makeCalendarDtoList(calendarTodoDtoList);

        // 첫 로그인 여부 확인
        Boolean firstLogin = memberDto.getFirstLogin();

        if(firstLogin.equals(true)){
            memberService.updateFirstLogin(memberDto);

            return ResponseShowMainPageDto.toResponseShowMainPageDto(categoryDtoList, calendarDtoList, dailyDtoList, true);
        }

        return ResponseShowMainPageDto.toResponseShowMainPageDto(categoryDtoList, calendarDtoList ,dailyDtoList, false);
    }

    // 달력 조회 날짜 변경 (월 단위)
    @Transactional(readOnly = true)
    public ResponseShowCalendarDto showCalendar(TodoDto todoDto) {

        MemberDto memberDto = todoDto.getMemberDto();

        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();

        List<TodoDto> calendarTodoDtoList = todoService.findAllDtoByMemberAndYearAndMonth(memberDto, todoYear, todoMonth);

        List<CalendarDto> calendarDtoList = makeCalendarDtoList(calendarTodoDtoList);

        return ResponseShowCalendarDto.toResponseChangeCalendarDto(calendarDtoList);
    }

    // 메인 페이지 내 데일리 로그 조회
    @Transactional(readOnly = true)
    public ResponseShowDailyDto showDaily(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay) {

        List<TodoDto> todoDtoList = todoService.findAllDtoByMemberAndYearAndMonthAndDay(memberDto, todoYear, todoMonth, todoDay);

        // 각 할 일들의 메모 조회해서 dailyDto에 담기
        List<DailyDto> dailyDtoList = todoMemoService.makeDailyDtoListWithMemo(todoDtoList);

        return ResponseShowDailyDto.toResponseShowDailyDto(dailyDtoList);
    }

    // 이번 달 날짜별 할 일 갯수 세서 리스트로 반환
    private List<CalendarDto> makeCalendarDtoList(List<TodoDto> todoDtoList){

        Map<Long, Long> countTodoPerDayMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for (TodoDto todoDto : todoDtoList) {
            Long day = todoDto.getTodoDay();
            boolean isContainsKeyOfDay = countTodoPerDayMap.containsKey(day);

            // Map 의 key 값에 day 가 존재하는 지에 따라 구분
            if (isContainsKeyOfDay) {
                // Map key 값에 day 가 존재할 시, 해당 day 의 key 값의 value 를 불러와 +1
                countTodoPerDayMap.replace(day, countTodoPerDayMap.get(day), countTodoPerDayMap.get(day) + 1L);
            } else {
                // Map key 값에 day 가 없을 시, 새로운 K, V 추가
                countTodoPerDayMap.put(day, 1L);
            }
        }

        // Map k, v 뽑아서 dto 변환 후, 응답 dtoList 추가
        for (Map.Entry<Long, Long> val : countTodoPerDayMap.entrySet()) {
            Long day = val.getKey();
            Long count = val.getValue();

            calendarDtoList.add(CalendarDto.toCalendar(day, count));
        }

        return calendarDtoList;
    }
}
