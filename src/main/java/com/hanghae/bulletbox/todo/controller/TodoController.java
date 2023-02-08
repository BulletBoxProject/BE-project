package com.hanghae.bulletbox.todo.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.ResponseSearchTodoDto;
import com.hanghae.bulletbox.todo.dto.SearchPageDto;
import com.hanghae.bulletbox.todo.service.SearchTodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Tag(name = "Todo", description = "할 일 API")
public class TodoController {

    private final SearchTodoService searchTodoService;

    @Operation(tags = {"Todo"}, summary = "메인 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "할 일 검색을 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping("/search")
    public Response<ResponseSearchTodoDto> searchTodo(@RequestParam String todoContent,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        SearchPageDto searchPageDto = SearchPageDto.toSearchTodoDto(memberDto, todoContent);

        ResponseSearchTodoDto responseSearchTodoDto = searchTodoService.searchTodo(searchPageDto);

        return Response.success(200, "할 일 검색을 성공했습니다", responseSearchTodoDto);
    }
}
