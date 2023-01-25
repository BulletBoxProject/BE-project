package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.*;


@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final CategoryRepository categoryRepository;

    // 자신의 카테고리 중 카테고리 id가 있는지 조회
    @Transactional(readOnly = true)
    protected void checkMemberHasCategoryId(Long categoryId, Member member){
        categoryRepository.findByCategoryIdAndMember(categoryId, member)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CATEGORY_MSG.getMsg()));
    }

    // Member가 빈 값인지 확인
    private void checkMemberIsNotNull(Member member){
        if(member == null){
            throw new NoSuchElementException(NOT_FOUND_MEMBER_MSG.getMsg());
        }
    }

    // Todo의 유효성 검사 (멤버 확인, 입력된 카테고리 번호가 내 카테고리 중에서 있는지 확인)
    private void checkTodoIsSafe(Todo todo){
        Member member = todo.getMember();
        Long categoryId = todo.getCategoryId();

        checkMemberIsNotNull(member);

        if(categoryId == null){
            return;
        }
        checkMemberHasCategoryId(categoryId, member);
    }


    // 할 일 생성
    @Transactional
    public TodoDto saveTodo(TodoDto todoDto){
        Todo todo = Todo.toTodo(todoDto);

        checkTodoIsSafe(todo);

        todoRepository.save(todo);

        return TodoDto.toTodoDto(todo);
    }

    // todoId로 할 일 찾기
    @Transactional(readOnly = true)
    public TodoDto findByTodoIdAndMember(Long todoId, MemberDto memberDto) {
        Member member = Member.toMember(memberDto);
        Todo todo = todoRepository.findByTodoIdAndMember(todoId, member)
                .orElseThrow(() -> new NoSuchElementException(TODO_NOT_FOUND_MSG.getMsg()));
        
        TodoDto todoDto = TodoDto.toTodoDto(todo);
        
        return todoDto;
    }

    // 할 일 삭제하기
    @Transactional
    public void deleteTodo(TodoDto todoDto) {
        Long todoId = todoDto.getTodoId();
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        checkMemberIsNotNull(member);

        if(todoId == null){
            throw new NoSuchElementException(TODO_NOT_FOUND_MSG.getMsg());
        }

        todoRepository.deleteById(todoId);
    }
}
