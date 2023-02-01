package com.hanghae.bulletbox.todo;

import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.BULLET_NOT_FOUND_MSG;

@Getter
public enum TodoBullet {
    TODO("할 일", "img_url"),
    COMPLETE("완료", "img_url"),
    DELAYED("미룬 일", "img_url"),
    EXPERIENCE("경험", "img_url"),
    MEMO("메모", "img_url"),
    TOBEDETERMINDED("미정", "img_url"),
    ROUTINE("루틴", "img_url"),
    EMPHASIS("강조", "img_url");

    private final String name;
    private final String imgUrl;

    TodoBullet(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static TodoBullet valueOfName(String name){
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(BULLET_NOT_FOUND_MSG.getMsg()));
    }
}
