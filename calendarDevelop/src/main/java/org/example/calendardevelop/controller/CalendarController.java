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
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public CalendarResponse createCalendar(@RequestBody CalendarRequest calendarRequest) {
        return calendarService.createCalendar(calendarRequest);
    }

    @GetMapping
    public List<CalendarResponse> readAllCalendars(@RequestParam(required = false) String name) {
        return calendarService.getAllCalendar(name);
    }

    @GetMapping("/{calenderId}")
    public CalendarResponse readCalendar(@PathVariable Long calenderId) {
        return calendarService.getByIdCalender(calenderId);
    }

    @PutMapping("/{calenderId}")
    public CalendarResponse updateCalendar(@PathVariable Long calenderId, @RequestBody CalendarRequest calendarRequest) {
        return calendarService.updateCalender(calenderId, calendarRequest);
    }

    @DeleteMapping("/{calenderId}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long calenderId) {
        calendarService.deleteCalender(calenderId);
        return ResponseEntity.noContent().build();
    }
}
