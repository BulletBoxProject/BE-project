package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.favorite.dto.FavoriteMemoDto;
import com.hanghae.bulletbox.todo.TodoBullet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseLoadFavoriteDto {

    private Long todoId;

    private Long categoryId;

    private String categoryColor;

    private String todoBulletName;

    private String todoBulletImgUrl;

    private String todoContent;

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
