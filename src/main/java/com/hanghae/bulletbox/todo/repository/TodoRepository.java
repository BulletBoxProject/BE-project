package com.hanghae.bulletbox.todo.repository;

import com.hanghae.bulletbox.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
