package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.entity.TodoMemo;
import com.hanghae.bulletbox.todo.repository.TodoMemoRepository;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.TODO_NOT_FOUND_MSG;

@RequiredArgsConstructor
@Service
public class TodoMemoService {

    private final TodoMemoRepository todoMemoRepository;

    private final TodoRepository todoRepository;

    // Member가 빈 값인지 확인
    private void checkMemberIsNotEmpty(Member member){
        if(member == null){
            throw new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg());
        }
    }

    // todo가 해당 유저의 todo인지 검사
    @Transactional(readOnly = true)
    protected void checkMemberHasTodoId(Member member, Todo todo){
        Long todoId = todo.getTodoId();
        todoRepository.findByTodoIdAndMember(todoId, member)
                .orElseThrow(() -> new NoSuchElementException(TODO_NOT_FOUND_MSG.getMsg()));
    }


    // 할 일 메모 생성
    @Transactional
    public void saveTodoMemo(TodoMemoDto todoMemoDto){
        TodoMemo todoMemo = TodoMemo.toTodoMemo(todoMemoDto);
        Member member = todoMemo.getMember();
        Todo todo = todoMemo.getTodo();
        String todoMemoContent = todoMemo.getTodoMemoContent();

        if(todoMemoContent == null){
            return;
        }

        checkMemberIsNotEmpty(member);
        checkMemberHasTodoId(member, todo);

        todoMemoRepository.save(todoMemo);
    }

}
