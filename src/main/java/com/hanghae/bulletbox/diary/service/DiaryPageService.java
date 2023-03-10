package com.hanghae.bulletbox.diary.service;

import com.hanghae.bulletbox.diary.dto.DiaryDto;
import com.hanghae.bulletbox.diary.dto.MonthlyEmotionDto;
import com.hanghae.bulletbox.diary.dto.ResponseDiaryCalendarPageDto;
import com.hanghae.bulletbox.diary.dto.ResponseDiaryPageDto;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryPageService {

    private final DiaryService diaryService;

    // 일기장 페이지 조회
    @Transactional(readOnly = true)
    public ResponseDiaryPageDto showDiaryPage(MemberDto memberDto) {

        Long year = (long) LocalDate.now().getYear();
        Long month = (long) LocalDate.now().getMonthValue();
        Long day = (long) LocalDate.now().getDayOfMonth();

        // 이번 달 일기들에서 감정 찾기, 중간에 오늘 일기 있으면 미리 빼두기
        List<DiaryDto> diaryDtoList = diaryService.findAllDtoByYearAndMonthAndMember(year, month, memberDto);
        List<MonthlyEmotionDto> emotions = new ArrayList<>();
        DiaryDto diaryOfToday = null;

        for(DiaryDto diaryDto : diaryDtoList){
            Long diaryDay = diaryDto.getDay();
            String emotion = diaryDto.getEmotion();

            // 오늘의 일기일 경우
            if (day.equals(diaryDay)) {
                diaryOfToday = diaryDto;
            }

            MonthlyEmotionDto monthlyEmotionDto = MonthlyEmotionDto.toMonthlyEmotionDto(diaryDay, emotion);
            emotions.add(monthlyEmotionDto);
        }

        // 이달의 감정 리스트, 오늘의 일기 합치기
        ResponseDiaryPageDto responseDiaryPageDto = ResponseDiaryPageDto.toResponseDiaryPageDto(emotions, diaryOfToday);

        return responseDiaryPageDto;
    }

    // 일기장 페이지 달력 조회 날짜 변경
    @Transactional(readOnly = true)
    public ResponseDiaryCalendarPageDto changeMonthOfDiaryPage(Long year, Long month, MemberDto memberDto) {

        List<MonthlyEmotionDto> emotions = findEmotionListOfMonth(year, month, memberDto);

        ResponseDiaryCalendarPageDto responseDiaryCalendarPageDto = ResponseDiaryCalendarPageDto.toResponseDiaryCalendarPageDto(emotions);

        return responseDiaryCalendarPageDto;
    }

    // 일기장 조회 날짜 변경
    @Transactional(readOnly = true)
    public DiaryDto showDiaryOfAnotherDay(Long year, Long month, Long day, MemberDto memberDto) {

        DiaryDto diaryDto = diaryService.findByYearAndMonthAndDayAndMember(year, month, day, memberDto);

        return diaryDto;
    }

    // 일기장 생성 및 수정
    @Transactional
    public ResponseDiaryPageDto updateDiary(DiaryDto diaryDto) {

        Long diaryId = diaryDto.getDiaryId();
        Long year = diaryDto.getYear();
        Long month = diaryDto.getMonth();
        MemberDto memberDto = diaryDto.getMemberDto();

        List<MonthlyEmotionDto> emotions = findEmotionListOfMonth(year, month, memberDto);

        if (diaryId == null) {
            DiaryDto savedDiaryDto = diaryService.saveDiary(diaryDto);
            ResponseDiaryPageDto responseDiaryPageDto = ResponseDiaryPageDto.toResponseDiaryPageDto(emotions, savedDiaryDto);

            return responseDiaryPageDto;
        }

        DiaryDto updatedDiaryDto = diaryService.updateDiary(diaryDto);
        ResponseDiaryPageDto responseDiaryPageDto = ResponseDiaryPageDto.toResponseDiaryPageDto(emotions, updatedDiaryDto);

        return responseDiaryPageDto;
    }

    // 이 달의 emotion 찾아서 리스트로 반환환
   @Transactional(readOnly = true)
    protected List<MonthlyEmotionDto> findEmotionListOfMonth(Long year, Long month, MemberDto memberDto) {

       List<DiaryDto> diaryDtoList = diaryService.findAllDtoByYearAndMonthAndMember(year, month, memberDto);
       List<MonthlyEmotionDto> emotions = new ArrayList<>();

       // 이 달의 emotion 모아서 리스트로 만들기
       for(DiaryDto diaryDtoOfMonth : diaryDtoList){
           Long diaryDay = diaryDtoOfMonth.getDay();
           String emotion = diaryDtoOfMonth.getEmotion();

           MonthlyEmotionDto monthlyEmotionDto = MonthlyEmotionDto.toMonthlyEmotionDto(diaryDay, emotion);
           emotions.add(monthlyEmotionDto);
       }

       return emotions;
   }
}
