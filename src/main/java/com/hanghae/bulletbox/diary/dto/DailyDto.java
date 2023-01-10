package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.todo.entity.Memo;
import com.hanghae.bulletbox.todo.entity.Todo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyDto {

    private Long todoId;
    private Long categoryId;
    private String categoryColor;
    private String todoBulletName;
    private String todoBulletImgUrl;
    private String todoContent;
    private String time;
    private List<Memo> memos;

    public DailyDto(Long todoId, Long categoryId, String categoryColor, String todoBulletName, String todoBulletImgUrl, String todoContent, String time, List<Memo> memos) {
        this.todoId = todoId;
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
        this.todoBulletName = todoBulletName;
        this.todoBulletImgUrl = todoBulletImgUrl;
        this.todoContent = todoContent;
        this.time = time;
        this.memos = memos;
    }

    public static DailyDto toDailyDto(Todo todo, List<Memo> memos) {
        return new DailyDto(
                todo.getTodoId(),
                todo.getCategoryId(),
                todo.getCategoryColor(),
                todo.getTodoBullet().getName(),
                todo.getTodoBullet().getImgUrl(),
                todo.getTodoContent(),
                todo.getTime(),
                memos
        );
    }
}
