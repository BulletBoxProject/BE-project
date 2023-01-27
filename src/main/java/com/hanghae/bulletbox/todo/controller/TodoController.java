package com.hanghae.bulletbox.todo.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.ResponseSearchTodoDto;
import com.hanghae.bulletbox.todo.dto.SearchPageDto;
import com.hanghae.bulletbox.todo.service.SearchTodoService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final SearchTodoService searchTodoService;

    @GetMapping("/search")
    public Response<ResponseSearchTodoDto> searchTodo(@RequestParam String todoContent,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        SearchPageDto searchPageDto = SearchPageDto.toSearchTodoDto(memberDto, todoContent);

        ResponseSearchTodoDto responseSearchTodoDto = searchTodoService.searchTodo(searchPageDto);

        return Response.success(200, "할 일 검색을 성공했습니다", responseSearchTodoDto);
    }
}
