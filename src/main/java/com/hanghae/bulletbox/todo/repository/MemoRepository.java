package com.hanghae.bulletbox.todo.repository;

import com.hanghae.bulletbox.todo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
