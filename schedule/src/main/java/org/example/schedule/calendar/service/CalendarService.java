package org.example.schedule.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.example.schedule.calendar.dto.CalendarRequest;
import org.example.schedule.calendar.dto.CalendarResponse;
import org.example.schedule.calendar.entity.CalendarEntity;
import org.example.schedule.calendar.repository.CalendarRepository;
import org.example.schedule.user.entity.UserEntity;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
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

    /*@Transactional // 유저 id를 입력하면 해당 유저의 calendar을 모두 출력
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
     */

    @Transactional
    public List<CalendarResponse> getAllByUserId(Long userId, int page, int size
    ) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        // 페이지당 5개의 항목을 가져오기 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);
        // 페이징된 캘린더 엔티티 목록 조회
        List<CalendarEntity> calendarEntities = calendarRepository.findByUserEntity_Id(userId, pageable);
        // CalendarEntity 목록을 CalendarResponse 목록으로 변환
        List<CalendarResponse> calendarResponses = new ArrayList<>();
        for (CalendarEntity calendarEntity : calendarEntities) {
            // CalendarResponse의 생성자가 모든 필드를 인자로 받도록 수정
            calendarResponses.add(new CalendarResponse(
                    calendarEntity.getId(),
                    calendarEntity.getUserEntity().getId(),
                    calendarEntity.getTitle(),
                    calendarEntity.getContent(),
                    calendarEntity.getCreatedDate(),
                    calendarEntity.getLastModifiedDate()
            ));
        }
        return calendarResponses;
    }



    @Transactional // calendar의 특정 id를 입력하면 해당 캘린더를 수정
    public CalendarResponse updateCalendar(Long userId, Long calendarId, CalendarRequest calendarRequest){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
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
    public void deleteCalendar(Long userId, Long calendarId){
        CalendarEntity calendarEntity = calendarRepository.findById(calendarId).orElseThrow(()-> new IllegalArgumentException("해당 캘린더 id는 존재하지 않습니다."));
        if(userId.equals(calendarEntity.getUserEntity().getId())) {
            calendarRepository.deleteById(calendarId);
        }else{
            throw new IllegalArgumentException("본인의 캘린더가 아닙니다.");
        }
    }
}
