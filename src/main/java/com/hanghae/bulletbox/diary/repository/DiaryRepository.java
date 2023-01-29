package com.hanghae.bulletbox.diary.repository;

import com.hanghae.bulletbox.diary.entity.Diary;
import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByMemberAndYearAndMonth(Member member, Long year, Long month);

    Optional<Diary> findByMemberAndYearAndMonthAndDay(Member member, Long year, Long month, Long day);

}
