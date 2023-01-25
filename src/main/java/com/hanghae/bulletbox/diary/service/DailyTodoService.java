package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.diary.dto.DailyTodoDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowTodoCreatePageDto;
import com.hanghae.bulletbox.diary.dto.ResponseTodoUpdatePageDto;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;
import com.hanghae.bulletbox.todo.service.TodoMemoService;
import com.hanghae.bulletbox.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;

@RequiredArgsConstructor
@Service
public class DailyTodoService {

    private final CategoryService categoryService;

    private final TodoService todoService;

    private final TodoMemoService todoMemoService;


    // 데일리 로그 할 일 추가 페이지 조회
    @Transactional(readOnly = true)
    public ResponseShowTodoCreatePageDto showTodoCreatePage(DailyTodoDto dailyTodoDto) {
        MemberDto memberDto = dailyTodoDto.getMemberDto();
        Long year = dailyTodoDto.getYear();
        Long month = dailyTodoDto.getMonth();
        Long day = dailyTodoDto.getDay();

        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        return ResponseShowTodoCreatePageDto.toResponseShowTodoCreatePageDto(categoryDtoList, year, month, day);
    }

    // 데일리 로그 할 일 추가
    @Transactional
    public void createTodo(DailyTodoDto dailyTodoDto) {

        // 할 일 저장 후 저장된 todo를 기반으로 todoDto 받기
        TodoDto todoDto = TodoDto.toTodoDto(dailyTodoDto);

        todoDto = todoService.saveTodo(todoDto);

        // 메모 저장
        List<TodoMemoDto> todoMemoDtoList = dailyTodoDto.getMemos();
        MemberDto memberDto = todoDto.getMemberDto();

        for (TodoMemoDto todoMemoDto : todoMemoDtoList) {
            todoMemoDto.setTodoDto(todoDto);
            todoMemoDto.setMemberDto(memberDto);

            todoMemoService.saveTodoMemo(todoMemoDto);
        }
    }

    // 할 일 삭제하기
    @Transactional
    public void deleteTodo(MemberDto memberDto, Long todoId) {

        TodoDto todoDto = todoService.findByTodoIdAndMember(todoId, memberDto);

        // 지우려는 할 일의 하위 메모 찾아서 삭제하기
        todoMemoService.deleteTodoMemosOfTodo(todoDto);

        // 할 일 삭제하기
        todoService.deleteTodo(todoDto);
    }

    // 할 일 수정 페이지 조회하기
    @Transactional(readOnly = true)
    public ResponseTodoUpdatePageDto showTodoUpdatePage(Long todoId, MemberDto memberDto) {
        // 카테고리 조회하기
        List<CategoryDto> categoryDtoList = categoryService.findAllCategory(memberDto);

        // 할 일 조회해서 받기
        TodoDto todoDto = todoService.findByTodoIdAndMember(todoId,memberDto);

        // 메모 조회해서 받기
        List<TodoMemoDto> todoMemoDtoList = todoMemoService.findAllMemoByTodo(todoDto);

        // 할 일, 메모 합쳐서 반환하기
        DailyTodoDto dailyTodoDto = DailyTodoDto.toDailyTodoDto(todoDto, todoMemoDtoList);

        ResponseTodoUpdatePageDto responseTodoUpdatePageDto = ResponseTodoUpdatePageDto.toResponseTodoUpdatePageDto(categoryDtoList, dailyTodoDto);

        return responseTodoUpdatePageDto;
    }
}
