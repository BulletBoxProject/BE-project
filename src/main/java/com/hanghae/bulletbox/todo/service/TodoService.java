package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.category.repository.CategoryRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.SearchTodoDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_CATEGORY_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.NOT_FOUND_MEMBER_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.TODO_NOT_FOUND_MSG;


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

    // todo리스트를 todoDto 리스트로 만들어서 반환
    private List<TodoDto> mapTodoListToTodoDtoList(List<Todo> todoList){
        List<TodoDto> todoDtoList = new ArrayList<>();

        for(Todo todo : todoList) {

            TodoDto todoDto = TodoDto.toTodoDto(todo);

            todoDtoList.add(todoDto);
        }

        return todoDtoList;
    }

    // todoId로 할 일 찾기
    @Transactional(readOnly = true)
    protected Todo findByTodoIdAndMember(Long todoId, Member member){
        Todo todo = todoRepository.findByTodoIdAndMember(todoId, member)
                .orElseThrow(() -> new NoSuchElementException(TODO_NOT_FOUND_MSG.getMsg()));

        return todo;
    }

    // todoId로 할 일 찾아서 반환하기
    @Transactional(readOnly = true)
    public TodoDto findDtoByTodoIdAndMember(Long todoId, MemberDto memberDto) {
        Member member = Member.toMember(memberDto);
        Todo todo = findByTodoIdAndMember(todoId, member);
        
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

    // 할 일 업데이트하기
    @Transactional
    public void updateTodo(TodoDto todoDto) {
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        // 바꿀 내용을 더티 체킹으로 DB에 반영
        Long todoId = todoDto.getTodoId();

        Todo todo = findByTodoIdAndMember(todoId, member);

        Long categoryId = todoDto.getCategoryId();
        todo.setCategoryId(categoryId);

        checkTodoIsSafe(todo);

        todo.updateAll(todoDto);
    }

    // member 기준 모든 할 일 조회하기 (검색용)
    @Transactional(readOnly = true)
    public List<SearchTodoDto> findAllByMember(MemberDto memberDto) {

        Member member = Member.toMember(memberDto);

        // member 유효성 검사
        checkMemberIsNotNull(member);

        List<Todo> todoList = todoRepository.findAllByMember(member);
        List<SearchTodoDto> searchTodoDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            // Entity -> Dto 변환
            SearchTodoDto searchTodoDto = SearchTodoDto.toSearchTodoDto(todo);
            searchTodoDtoList.add(searchTodoDto);
        }

        return searchTodoDtoList;
    }

    // 멤버의 특정 날짜의 모든 할 일들 조회 후 DTO로 반환
    @Transactional(readOnly = true)
    public List<TodoDto> findAllDtoByMemberAndYearAndMonthAndDay(MemberDto memberDto, Long todoYear, Long todoMonth, Long todoDay) {

        Member member = Member.toMember(memberDto);

        checkMemberIsNotNull(member);

        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonthAndTodoDay(member, todoYear, todoMonth, todoDay);

        List<TodoDto> todoDtoList = mapTodoListToTodoDtoList(todoList);

        return todoDtoList;
    }

    // 특정 날짜의 데일리 로그 할 일 카테고리별로 조회하기
    @Transactional(readOnly = true)
    public List<TodoDto> findAllDtoByMemberAndCategoryIdAndYearAndMonthAndDay(TodoDto todoDto) {
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        Long todoYear = todoDto.getTodoYear();
        Long todoMonth = todoDto.getTodoMonth();
        Long todoDay = todoDto.getTodoDay();
        Long categoryId = todoDto.getCategoryId();

        checkMemberIsNotNull(member);

        List<Todo> todoList = todoRepository.findAllByMemberAndCategoryIdAndTodoYearAndTodoMonthAndTodoDay(member, categoryId, todoYear, todoMonth, todoDay);
        List<TodoDto> todoDtoList = mapTodoListToTodoDtoList(todoList);

        return todoDtoList;
    }

    // 해당 멤버의 이달의 할 일 찾아서 반환
    @Transactional(readOnly = true)
    public List<TodoDto> findAllDtoByMemberAndYearAndMonth(MemberDto memberDto, Long todoYear, Long todoMonth) {
        Member member = Member.toMember(memberDto);

        checkMemberIsNotNull(member);

        List<Todo> todoList = todoRepository.findAllByMemberAndTodoYearAndTodoMonth(member, todoYear, todoMonth);

        List<TodoDto> todoDtoList = mapTodoListToTodoDtoList(todoList);

        return todoDtoList;
    }
}
