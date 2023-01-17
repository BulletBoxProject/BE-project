package com.hanghae.bulletbox.category.controller;

import com.hanghae.bulletbox.category.dto.CategoryDto;
import com.hanghae.bulletbox.category.dto.RequestCreateCategoryDto;
import com.hanghae.bulletbox.category.dto.RequestUpdateCategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseDeleteCategoryDto;
import com.hanghae.bulletbox.category.dto.ResponseShowCategoryDto;
import com.hanghae.bulletbox.category.service.CategoryPageService;
import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "Category", description = "카테고리 API")
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@RestController
public class CategoryController {

    private final CategoryPageService categoryService;

    @Operation(tags = {"Category"}, summary = "카테고리 목록 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 목록 조회를 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping
    public Response showCategory(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        CategoryDto categoryDto = CategoryDto.toCategoryDto(member);
        ResponseShowCategoryDto responseShowCategoryDto = categoryService.showCategory(categoryDto);

        return Response.success(200, "카테고리 목록 조회를 성공했습니다.", responseShowCategoryDto);
    }

    @Operation(tags = {"Category"}, summary = "카테고리 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 생성을 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 카테고리입니다.")
    })
    @PostMapping()
    public Response createCategory(@RequestBody RequestCreateCategoryDto requestCreateCategoryDto,
                                   @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        String categoryName = requestCreateCategoryDto.getCategoryName();
        String categoryColor = requestCreateCategoryDto.getCategoryColor();

        CategoryDto categoryDto = CategoryDto.toCategoryDto(categoryName, categoryColor, member);
        categoryService.createCategory(categoryDto);

        return Response.success(200, "카테고리 생성을 성공했습니다.", null);
    }

    @Operation(tags = {"Category"}, summary = "카테고리 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 수정을 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "이미 존재하지 않는 카테고리입니다.")
    })
    @PutMapping("/{categoryId}")
    public Response updateCategory(@PathVariable Long categoryId,
                                   @RequestBody RequestUpdateCategoryDto requestUpdateCategoryDto,
                                   @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        String categoryName = requestUpdateCategoryDto.getCategoryName();
        String categoryColor = requestUpdateCategoryDto.getCategoryColor();

        CategoryDto categoryDto = CategoryDto.toCategoryDto(member, categoryId, categoryName, categoryColor);
        categoryService.updateCategory(categoryDto);

        return Response.success(200, "카테고리 수정을 성공했습니다.", null);
    }

    @Operation(tags = {"Category"}, summary = "카테고리 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 삭제를 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 카테고리입니다.")
    })
    @DeleteMapping("/{categoryId}")
    public Response deleteCategory(@PathVariable Long categoryId,
                                   @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();

        CategoryDto categoryDto = CategoryDto.toCategoryDto(member, categoryId);
        ResponseDeleteCategoryDto responseDeleteCategoryDto = categoryService.deleteCategory(categoryDto);

        return Response.success(200, "카테고리 삭제를 성공했습니다.", responseDeleteCategoryDto);
    }
}
