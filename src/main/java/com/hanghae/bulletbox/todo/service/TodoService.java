package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // 할 일 생성
    @Transactional
    public TodoDto saveTodo(TodoDto todoDto){
        Todo todo = Todo.toTodo(todoDto);

        todoRepository.save(todo);

        return TodoDto.toTodoDto(todo);
    }
}
