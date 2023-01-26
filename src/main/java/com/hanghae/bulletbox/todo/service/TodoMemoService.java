package com.hanghae.bulletbox.todo.service;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoDto;
import com.hanghae.bulletbox.todo.dto.TodoMemoDto;
import com.hanghae.bulletbox.todo.entity.Todo;
import com.hanghae.bulletbox.todo.entity.TodoMemo;
import com.hanghae.bulletbox.todo.repository.TodoMemoRepository;
import com.hanghae.bulletbox.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.*;

@RequiredArgsConstructor
@Service
public class TodoMemoService {

    private final TodoMemoRepository todoMemoRepository;

    private final TodoRepository todoRepository;

    // Member가 빈 값인지 확인
    private void checkMemberIsNotNull(Member member){
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

    // memoId로 메모 찾기
    @Transactional(readOnly = true)
    protected TodoMemo findTodoMemoById(Long todoMemoId){
        TodoMemo todoMemo = todoMemoRepository.findById(todoMemoId)
                .orElseThrow(() -> new NoSuchElementException(TODO_MEMO_NOT_FOUND_MSG.getMsg()));

        return todoMemo;
    }

    // todo로 메모 찾기
    @Transactional(readOnly = true)
    public List<TodoMemoDto> findAllMemoByTodo(TodoDto todoDto){
        Todo todo = Todo.toTodo(todoDto);
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        List<TodoMemo> todoMemoList = todoMemoRepository.findAllByMemberAndTodo(member, todo);

        List<TodoMemoDto> todoMemoDtoList = new ArrayList<>();

        for(TodoMemo todoMemo : todoMemoList){
            TodoMemoDto todoMemoDto = TodoMemoDto.toTodoMemoDto(todoMemo);
            todoMemoDtoList.add(todoMemoDto);
        }

        return todoMemoDtoList;
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

        checkMemberIsNotNull(member);
        checkMemberHasTodoId(member, todo);

        todoMemoRepository.save(todoMemo);
    }

    // 할 일의 하위 메모들 삭제
    @Transactional
    public void deleteTodoMemosOfTodo(TodoDto todoDto) {
        MemberDto memberDto = todoDto.getMemberDto();
        Member member = Member.toMember(memberDto);
        Todo todo = Todo.toTodo(todoDto);

        checkMemberIsNotNull(member);
        checkMemberHasTodoId(member, todo);

        todoMemoRepository.deleteAllByTodoAndMember(todo, member);
    }

    // 메모 id로 메모 삭제
    @Transactional
    public void deleteTodoMemoById(Long todoMemoId) {
        todoMemoRepository.deleteById(todoMemoId);
    }

    // todoMemo 수정
    @Transactional
    public void updateTodoMemo(TodoMemoDto todoMemoDto) {
        Long todoMemoId = todoMemoDto.getTodoMemoId();
        String todoMemoContent = todoMemoDto.getTodoMemoContent();

        TodoMemo todoMemo = findTodoMemoById(todoMemoId);

        // 수정된 내용이 없으면 update를 쿼리를 날리지 않도록 함
        if(todoMemo.getTodoMemoContent().equals(todoMemoDto.getTodoMemoContent())){
            return;
        }

        todoMemo.updateContent(todoMemoDto);
    }
}
