package org.example.calendardevelop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.CalendarRequest;
import org.example.calendardevelop.dto.CalendarResponse;
import org.example.calendardevelop.entity.CalendarEntity;
import org.example.calendardevelop.repository.CalendarRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Transactional // Create 메서드
    public CalendarResponse createCalendar(CalendarRequest calendarRequest) {
        CalendarEntity calendarEntity = new CalendarEntity(calendarRequest.getUsername(), calendarRequest.getTitle(), calendarRequest.getContent());
        CalendarEntity savedCalendarEntity = calendarRepository.save(calendarEntity);
        return new CalendarResponse(savedCalendarEntity);
    }

    @Transactional // ReadAll 메서드
    public List<CalendarResponse> getAllCalendar(String name) { // 사용자명을 받는다.
        List<CalendarEntity> list;
        if (name == null || name.isEmpty()) {
            list = calendarRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedDate"));
        } else {
            list = calendarRepository.findByUserNameOrderByModifiedDate(name);
        }
        List<CalendarResponse> calendarResponseList = new ArrayList<>();
        for (CalendarEntity calendarEntity : list) {
            CalendarResponse calendarResponse = new CalendarResponse(calendarEntity);
            calendarResponseList.add(calendarResponse);
        }
        return calendarResponseList;
    }

    @Transactional //readById 메서드
    public CalendarResponse getByIdCalender(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        return new CalendarResponse(calendarEntity);
    }

    @Transactional //update 메서드
    public CalendarResponse updateCalender(Long id, CalendarRequest calendarRequest) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        calendarEntity.updateCalendar(calendarRequest.getUsername(), calendarRequest.getTitle(), calendarRequest.getContent());
        return new CalendarResponse(calendarEntity);
    }

    @Transactional //delete 메서드
    public void deleteCalender(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        calendarRepository.delete(calendarEntity);
    }

}
