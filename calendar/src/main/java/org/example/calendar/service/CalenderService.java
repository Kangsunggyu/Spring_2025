package org.example.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.entity.CalendarEntity;
import org.example.calendar.repository.CalendarRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private CalendarRepository calendarRepository;

    // Create 메서드
    @Transactional
    public CalendarResponseDto createCalender(CalendarRequestDto calendarRequestDto) {
        CalendarEntity calendarEntity = new CalendarEntity(calendarRequestDto.getTitle(), calendarRequestDto.getContent(), calendarRequestDto.getName(), calendarRequestDto.getPassword());
        CalendarEntity savedCalendarEntity = calendarRepository.save(calendarEntity);
        return new CalendarResponseDto(savedCalendarEntity);
    }
    // ReadAll 메서드
    @Transactional
    public List<CalendarResponseDto> readAllCalender(String name){ // 사용자명을 받는다.
        List<CalendarEntity> list;
        if(name == null || name.isEmpty()){
            list = calendarRepository.findAll(Sort.by(Sort.Direction.DESC, "modificationTime"));
        } else{
            list = calendarRepository.findByNameOrderByModificationTime(name);
        }
        List<CalendarResponseDto> calendarResponseDtoList = new ArrayList<>();
        for (CalendarEntity calendarEntity : list) {
            CalendarResponseDto calendarResponseDto = new CalendarResponseDto(calendarEntity);
            calendarResponseDtoList.add(calendarResponseDto);
        }
        return calendarResponseDtoList;
    }
    //readById 메서드
    @Transactional
    public CalendarResponseDto readByIdCalender(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        return new CalendarResponseDto(calendarEntity);
    }


}
