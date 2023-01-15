package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.todo.entity.Memo;
import com.hanghae.bulletbox.todo.entity.Todo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Daily 정보를 가진 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyDto {

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

    @Schema(description = "할 일에 기록된 시간", type = "String", example = "14:00")
    private String time;

    @Schema(description = "할 일의 하위 메모")
    private List<Memo> memos;

    private DailyDto(Long todoId, Long categoryId, String categoryColor, String todoBulletName, String todoBulletImgUrl, String todoContent, String time, List<Memo> memos) {
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
