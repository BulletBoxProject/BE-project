package com.hanghae.bulletbox.todo;

import lombok.Getter;

@Getter
public enum TodoBullet {
    TODO("할 일", "img_url"),
    COMPLETE("완료", "img_url"),
    DELAYED("미룬 일", "img_url"),
    EXPERIENCE("경험", "img_url"),
    MEMO("메모", "img_url"),
    IMPORTANT("중요", "img_url"),
    FAVORITE("자주", "img_url");

    private final String name;
    private final String imgUrl;

    TodoBullet(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
