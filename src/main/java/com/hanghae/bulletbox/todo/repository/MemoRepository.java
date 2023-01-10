package com.hanghae.bulletbox.todo.repository;

import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Memo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByMember(Member member);
}