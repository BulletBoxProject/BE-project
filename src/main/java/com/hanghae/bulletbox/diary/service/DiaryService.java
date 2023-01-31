package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.diary.dto.DiaryDto;
import com.hanghae.bulletbox.diary.entity.Diary;
import com.hanghae.bulletbox.diary.repository.DiaryRepository;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DIARY_NOT_FOUND_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_DIARY_MSG;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    // 연,월,멤버로 diary 찾아서 dto로 반환
    @Transactional(readOnly = true)
    public List<DiaryDto> findAllDtoByYearAndMonthAndMember(Long year, Long month, MemberDto memberDto) {
        Member member = Member.toMember(memberDto);

        List<Diary> diaryList = diaryRepository.findAllByMemberAndYearAndMonth(member, year, month);
        List<DiaryDto> diaryDtoList = new ArrayList<>();


        for(Diary diary: diaryList){
            DiaryDto diaryDto = DiaryDto.toDiaryDto(diary);
            diaryDtoList.add(diaryDto);
        }

        return diaryDtoList;
    }

    // 연,월,일,멤버로 diary 찾아서 반환하기
    @Transactional(readOnly = true)
    public DiaryDto findByYearAndMonthAndDayAndMember(Long year, Long month, Long day, MemberDto memberDto) {
        Member member = Member.toMember(memberDto);

        Diary diary = diaryRepository.findByMemberAndYearAndMonthAndDay(member, year, month, day).get();

        DiaryDto diaryDto = DiaryDto.toDiaryDto(diary);

        return diaryDto;
    }

    // 일기장 저장
    @Transactional
    public DiaryDto saveDiary(DiaryDto diaryDto) {
        Long year = diaryDto.getYear();
        Long month = diaryDto.getMonth();
        Long day = diaryDto.getDay();
        MemberDto memberDto = diaryDto.getMemberDto();
        Member member = Member.toMember(memberDto);

        // 같은 날짜의 일기가 이미 있는지 검사
        Optional<Diary> diaryOptional = diaryRepository.findByMemberAndYearAndMonthAndDay(member, year, month, day);

        if(diaryOptional.isPresent()){
            throw new NoSuchElementException(DUPLICATE_DIARY_MSG.getMsg());
        }

        Diary diary = Diary.toDiary(diaryDto);

        diaryRepository.save(diary);

        DiaryDto savedDiaryDto = DiaryDto.toDiaryDto(diary);

        return savedDiaryDto;
    }

    // 일기장 수정
    @Transactional
    public DiaryDto updateDiary(DiaryDto diaryDto) {
        Long diaryId = diaryDto.getDiaryId();

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NoSuchElementException(DIARY_NOT_FOUND_MSG.getMsg()));

        diary.updateAll(diaryDto);

        return diaryDto;
    }
}
