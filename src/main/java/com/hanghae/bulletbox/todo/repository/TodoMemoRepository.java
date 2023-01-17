package com.hanghae.bulletbox.todo.repository;

import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.TodoMemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoMemoRepository extends JpaRepository<TodoMemo, Long> {

    List<TodoMemo> findAllByMember(Member member);
}