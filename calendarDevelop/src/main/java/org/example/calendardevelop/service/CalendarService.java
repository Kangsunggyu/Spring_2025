package org.example.calendardevelop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.CalendarRequest;
import org.example.calendardevelop.dto.CalendarResponse;
import org.example.calendardevelop.entity.Calendar;
import org.example.calendardevelop.repository.CalendarRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    // Create 메서드
    @Transactional
    public CalendarResponse createCalendar(CalendarRequest calendarRequest) {
        Calendar calendar = new Calendar(calendarRequest.getUserName(), calendarRequest.getTitle(), calendarRequest.getContent()); // request dto를 통해 캘린더 엔티티를 생성
        Calendar savedCalendarEntity = calendarRepository.save(calendar); // 엔티티를 데이터베이스에 저장
        return new CalendarResponse( // 저장된 엔티티를 기반으로 response dto를 생성해 반환, 이를 클라이언트에게 보여줌
                savedCalendarEntity.getId(),
                savedCalendarEntity.getTitle(),
                savedCalendarEntity.getContent(),
                savedCalendarEntity.getUserName(),
                savedCalendarEntity.getCreationDate(),
                savedCalendarEntity.getModificationDate()
        );
    }

    //ReadAll 메서드
    @Transactional
    public List<CalendarResponse> readAllCalendar(String name) { // userName을 입력하도록 한다.
        List<Calendar> list;
        if(name == null || name.isEmpty()){
            list = calendarRepository.findAll(Sort.by(Sort.Direction.DESC, "modificationTime")); // 이름을 입력하지 않았다면, 전체를 최근 수정일을 기준으로 정랼한다.
        } else{
            list = calendarRepository.findByUserNameOrderByModificationDate(name);
        }
        List<CalendarResponse> calendarResponseList = new ArrayList<>(); // 클라이언트에게 전달할 리스트, 이를 클라이언트에게 보여줌
        for(Calendar calendar : list){
            CalendarResponse calendarResponse = new CalendarResponse(
                    calendar.getId(),
                    calendar.getTitle(),
                    calendar.getContent(),
                    calendar.getUserName(),
                    calendar.getCreationDate(),
                    calendar.getModificationDate()
            );
            calendarResponseList.add(calendarResponse);
        }
        return calendarResponseList; // 클라이언트에게 전달
    }

    //readById 메서드
    @Transactional
    public CalendarResponse readByIdCalendar(Long id) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        return new CalendarResponse(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getUserName(),
                calendar.getCreationDate(),
                calendar.getModificationDate()
        );
    }

    //update 메서드
    @Transactional
    public CalendarResponse updateCalendar(Long id, CalendarRequest calendarRequest) {// 사용자에게 변경할 데이터를 입력받기 위한 request dto
        Calendar calendar = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        calendar.updateCalendar(calendarRequest.getUserName(), calendarRequest.getTitle(), calendarRequest.getContent());
        return new CalendarResponse( // 사용자에게 업데이트 된 정보를 보여줌
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getUserName(),
                calendar.getCreationDate(),
                calendar.getModificationDate()
        );
    }

    //delete 메서드
    @Transactional
    public void deleteCalendar(Long id) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" id를 찾을 수 없습니다." ));
        calendarRepository.delete(calendar); // 삭제되는 내용을 굳이 보여줄 필요 없음
    }
}
