package org.example.calendardevelop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.CalendarRequest;
import org.example.calendardevelop.dto.CalendarResponse;
import org.example.calendardevelop.entity.CalendarEntity;
import org.example.calendardevelop.entity.UserEntity;
import org.example.calendardevelop.repository.CalendarRepository;
import org.example.calendardevelop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional
    public CalendarResponse createCalendar(CalendarRequest calendarRequest, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId +" id를 찾을 수 없습니다."));
        CalendarEntity calendarEntity = new CalendarEntity(calendarRequest.getTitle(), calendarRequest.getContent(), userEntity);
        CalendarEntity savedCalendarEntity = calendarRepository.save(calendarEntity);
        return new CalendarResponse(savedCalendarEntity);
    }

    @Transactional // ReadAll 메서드
    public List<CalendarResponse> getAllCalendar(Long userId) {
        List<CalendarEntity> list = calendarRepository.findByUserEntity_Id(userId);
        List<CalendarResponse> calendarResponseList = new ArrayList<>();
        for (CalendarEntity calendarEntity : list) {
            calendarResponseList.add(new CalendarResponse(calendarEntity));
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
        calendarEntity.updateCalendar(calendarRequest.getTitle(), calendarRequest.getContent());
        return new CalendarResponse(calendarEntity);
    }

    @Transactional //delete 메서드
    public void deleteCalender(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        calendarRepository.delete(calendarEntity);
    }

}
