package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.ResponseSearchTodoDto;
import com.hanghae.bulletbox.todo.dto.SearchPageDto;
import com.hanghae.bulletbox.todo.dto.SearchTodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchTodoService {

    private final TodoService todoService;

    private final TodoMemoService todoMemoService;

    public ResponseSearchTodoDto searchTodo(SearchPageDto searchPageDto) {

        MemberDto memberDto = searchPageDto.getMemberDto();

        // member 기준 할 일을 모두 조회
        List<SearchTodoDto> searchTodoDtoList = todoService.findAllByMember(memberDto);

        // 응답을 위한 List
        List<SearchTodoDto> responseDtoList = new ArrayList<>();

        for (SearchTodoDto searchTodoDto : searchTodoDtoList) {
            // 검색어 todoContent 를 포함하고 있는 todoContent(member 기준으로 조회)일 경우,
            if (searchTodoDto.getTodoContent().contains(searchPageDto.getTodoContent())) {
                List<TodoMemoDto> todoMemoDtoList = todoMemoService.findAllMemoByTodo(searchTodoDto);

                // member 기준으로 조회한 TodoDto 에 해당 Memo 삽입해준다.
                searchTodoDto.setTodoMemos(todoMemoDtoList);

                // 응답 List 에 데이터 담기
                responseDtoList.add(searchTodoDto);
            }
        }

        return ResponseSearchTodoDto.toResponseSearchTodoDto(responseDtoList);
    }
}
