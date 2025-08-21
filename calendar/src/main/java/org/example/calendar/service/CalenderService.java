package org.example.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.dto.CommentResponseDto;
import org.example.calendar.entity.CalendarEntity;
import org.example.calendar.entity.CommentEntity;
import org.example.calendar.repository.CalendarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalendarRepository calendarRepository;

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

    @Transactional
    public List<CalendarResponseDto> calendarAll(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CalendarEntity> commentPage = calendarRepository.findByCalendarId(postId, pageable);
        List<CalendarResponseDto> responseDtoList = new ArrayList<>();
        for (CalendarEntity comment : commentPage.getContent()) {
            CalendarResponseDto commentResponseDto = new CalendarResponseDto(comment);
            responseDtoList.add(commentResponseDto);
        }
        return responseDtoList;

    }

    //readById 메서드
    @Transactional
    public CalendarResponseDto readByIdCalender(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        return new CalendarResponseDto(calendarEntity);
    }

    //update 메서드
    @Transactional
    public CalendarResponseDto updateCalender(Long id, CalendarRequestDto calendarRequestDto) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        if (!calendarEntity.getPassword().equals(calendarRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        calendarEntity.updateCalendar(calendarRequestDto.getTitle(), calendarRequestDto.getName());
        return new CalendarResponseDto(calendarEntity);
    }

    //delete 메서드
    @Transactional
    public void deleteCalender(Long id, String password) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        if (!calendarEntity.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        calendarRepository.delete(calendarEntity);
    }
}
