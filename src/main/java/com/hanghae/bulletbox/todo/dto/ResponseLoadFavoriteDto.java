package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.todo.TodoBullet;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "루틴 불러오기 응답 Dto")
public class ResponseLoadFavoriteDto {

    @Schema(description = "할 일 ID", type = "Long", example = "1")
    private Long todoId;

    @Schema(description = "카테고리 ID", type = "Long", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 색상", type = "String", example = "#000000")
    private String categoryColor;

    @Schema(description = "Bullet 이름", type = "String", example = "Todo")
    private String todoBulletName;

    @Schema(description = "Bullet 이미지", type = "String", example = "img_url...")
    private String todoBulletImgUrl;

    @Schema(description = "할 일 내용", type = "String", example = "할 일 내용")
    private String todoContent;

    @Schema(description = "할 일의 메모 변수", type = "List")
    private List<TodoMemoDto> todoMemos;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseLoadFavoriteDto(Long todoId, Long categoryId, String categoryColor, String todoBulletName, String todoBulletImgUrl, String todoContent, List<TodoMemoDto> todoMemos) {
        this.todoId = todoId;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBulletName = todoBulletName;
        this.todoBulletImgUrl = todoBulletImgUrl;
        this.todoContent = todoContent;
        this.todoMemos = todoMemos;
    }

    public static ResponseLoadFavoriteDto toResponseLoadFavoriteDto(TodoDto savedTodoDto, List<TodoMemoDto> todoMemoDtoList) {
        Long todoId = savedTodoDto.getTodoId();
        Long categoryId = savedTodoDto.getCategoryId();
        String categoryColor = savedTodoDto.getCategoryColor();
        TodoBullet todoBullet = savedTodoDto.getTodoBullet();
        String todoBulletName = todoBullet.getName();
        String todoBulletImgUrl = todoBullet.getImgUrl();
        String todoContent = savedTodoDto.getTodoContent();

        return ResponseLoadFavoriteDto.builder()
                .todoId(todoId)
                .categoryId(categoryId)
                .categoryColor(categoryColor)
                .todoBulletName(todoBulletName)
                .todoBulletImgUrl(todoBulletImgUrl)
                .todoContent(todoContent)
                .todoMemos(todoMemoDtoList)
                .build();
    }
}
