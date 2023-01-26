package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.todo.dto.TodoMemoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUpdateTodoDto {

    private Long todoId;

    private String todoContent;

    private String todoBulletName;

    private String time;

    private Long categoryId;

    private String categoryColor;

    private Long year;

    private Long month;

    private Long day;

    private List<TodoMemoDto> memos;

}
