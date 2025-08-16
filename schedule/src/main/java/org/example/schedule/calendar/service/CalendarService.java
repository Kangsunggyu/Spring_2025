package org.example.schedule.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.calendar.dto.CalendarRequest;
import org.example.schedule.calendar.dto.CalendarResponse;
import org.example.schedule.calendar.entity.CalendarEntity;
import org.example.schedule.calendar.repository.CalendarRepository;
import org.example.schedule.user.dto.UserResponse;
import org.example.schedule.user.entity.UserEntity;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional
    public CalendarResponse createCalendar(Long userId, CalendarRequest calendarRequest){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        CalendarEntity calendarEntity = new CalendarEntity(userEntity, calendarRequest.getTitle(), calendarRequest.getContent());
        CalendarEntity savedCalendarEntity = calendarRepository.save(calendarEntity);
        return new CalendarResponse(
                savedCalendarEntity.getId(),
                savedCalendarEntity.getUserEntity().getId(),
                savedCalendarEntity.getTitle(),
                savedCalendarEntity.getContent(),
                savedCalendarEntity.getCreatedDate(),
                savedCalendarEntity.getLastModifiedDate()
        );
    }

    @Transactional // 유저 id를 입력하면 해당 유저의 calendar을 모두 출력
    public List<CalendarResponse> getAllByUserId(Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        List<CalendarEntity> CalendarEntities = calendarRepository.findByUserEntity_Id(userId);
        List<CalendarResponse> calendarResponses = new ArrayList<>();
        for (CalendarEntity calendarEntity : CalendarEntities) {
            CalendarResponse calendarResponse = new CalendarResponse(
                    calendarEntity.getId(),
                    calendarEntity.getUserEntity().getId(),
                    calendarEntity.getTitle(),
                    calendarEntity.getContent(),
                    calendarEntity.getCreatedDate(),
                    calendarEntity.getLastModifiedDate()
            );
            calendarResponses.add(calendarResponse);
        }
        return calendarResponses;
    }

    @Transactional // calendar의 특정 id를 입력하면 해당 캘린더를 수정
    public CalendarResponse updateCalendar(Long calendarId, CalendarRequest calendarRequest){
        CalendarEntity calendarEntity = calendarRepository.findById(calendarId).orElseThrow(()-> new IllegalArgumentException("해당 캘린더 id는 존재하지 않습니다."));
        calendarEntity.calendarUpdate(calendarRequest.getTitle(), calendarRequest.getContent());
        return new CalendarResponse(
                calendarEntity.getId(),
                calendarEntity.getUserEntity().getId(),
                calendarEntity.getTitle(),
                calendarEntity.getContent(),
                calendarEntity.getCreatedDate(),
                calendarEntity.getLastModifiedDate()
        );
    }

    @Transactional // userId를 입력하면 해당 id와 같은지 확인하고 삭제
    public void deleteCalendar(Long UserId, Long calendarId){
        CalendarEntity calendarEntity = calendarRepository.findById(calendarId).orElseThrow(()-> new IllegalArgumentException("해당 캘린더 id는 존재하지 않습니다."));
        if(UserId.equals(calendarEntity.getUserEntity().getId())) {
            calendarRepository.deleteById(calendarId);
        }else{
            throw new IllegalArgumentException("본인의 캘린더가 아닙니다.");
        }
    }
}
