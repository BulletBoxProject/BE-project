package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.todo.dto.TodoMemoDto;
import com.hanghae.bulletbox.todo.entity.TodoMemo;
import com.hanghae.bulletbox.todo.repository.TodoMemoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoMemoService {

    private final TodoMemoRepository todoMemoRepository;

    // 할 일 메모 생성
    @Transactional
    public void saveTodoMemo(TodoMemoDto todoMemoDto){

        TodoMemo todoMemo = TodoMemo.toTodoMemo(todoMemoDto);

        todoMemoRepository.save(todoMemo);
    }

}
