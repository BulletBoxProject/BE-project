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
}
