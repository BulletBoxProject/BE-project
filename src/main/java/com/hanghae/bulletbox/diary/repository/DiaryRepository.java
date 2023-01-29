package com.hanghae.bulletbox.diary.repository;

import com.hanghae.bulletbox.diary.entity.Diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Long, Diary> {
}
