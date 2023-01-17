package com.hanghae.bulletbox.category.controller;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseShowCategoryDto;
import com.hanghae.bulletbox.category.service.CategoryService;
import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "Category", description = "카테고리 API")
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(tags = {"Category"}, summary = "카테고리 목록 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 목록 조회를 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping
    public Response showCategory(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        CategoryDto categoryDto = CategoryDto.toCategoryDto(memberId);
        ResponseShowCategoryDto responseShowCategoryDto = categoryService.showCategory(categoryDto);

        return Response.success(200, "카테고리 목록 조회를 성공했습니다.", responseShowCategoryDto);
    }
}
