package org.example.calendardevelop.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.CalendarRequest;
import org.example.calendardevelop.dto.CalendarResponse;
import org.example.calendardevelop.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // JSON 형식으로 데이터를 반환
@RequiredArgsConstructor // final 선언된 필드의 생성자 자동 생성
@RequestMapping("/calendars")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<CalendarResponse> createCalendar(
            @PathVariable Long userId,
            @RequestBody CalendarRequest calendarRequest) {

        return ResponseEntity.ok(calendarService.createCalendar(calendarRequest, userId));
    }

    @GetMapping("/users/{userId}") // 몇번째 사용자의 데이터를 전부 받을 것인지
    public ResponseEntity<List<CalendarResponse>> getAllCalendarsByUserId(@PathVariable Long userId) {
        List<CalendarResponse> responseList = calendarService.getAllCalendar(userId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponse> getCalendarById(@PathVariable Long id) {
        CalendarResponse response = calendarService.getByIdCalender(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponse> updateCalendar(
            @PathVariable Long id,
            @RequestBody CalendarRequest calendarRequest) {

        CalendarResponse response = calendarService.updateCalender(id, calendarRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalender(id);
        return ResponseEntity.noContent().build();
    }
}
