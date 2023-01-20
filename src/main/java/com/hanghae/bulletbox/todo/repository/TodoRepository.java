package com.hanghae.bulletbox.todo.repository;

import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByMemberAndTodoYearAndTodoMonth(Member member, Long todoYear, Long todoMonth);

    List<Todo> findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(Member member, Long todoYear, Long todoMonth, Long todoDay);

    List<Todo> findAllByMemberAndCategoryIdAndTodoYearAndTodoMonthAndTodoDay(Member member, Long categoryId, Long todoYear, Long todoMonth, Long todoDay);

    Optional<Todo> findByTodoIdAndMember(Long todoId, Member member);

}