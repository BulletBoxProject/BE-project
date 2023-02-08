package com.hanghae.bulletbox.daily.dto;

import com.hanghae.bulletbox.todo.dto.TodoMemoDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCreateTodoDto {

    @Schema(description = "할 일 내용", type = "String", example = "할 일 내용")
    private String todoContent;

    @Schema(description = "Bullet 이름", type = "String", example = "Todo")
    private String todoBulletName;

    @Schema(description = "할 일에 기록된 시간", type = "String", example = "14:00")
    private String time;

    @Schema(description = "카테고리 ID", type = "Long", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 색상", type = "String", example = "#000000")
    private String categoryColor;

    @Schema(description = "연도", example = "2023", type = "Long")
    private Long year;

    @Schema(description = "월", example = "12", type = "Long")
    private Long month;

    @Schema(description = "날짜", example = "12", type = "Long")
    private Long day;

    @Schema(description = "할 일의 메모 변수", type = "List")
    private List<TodoMemoDto> memos;
}
