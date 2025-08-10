package org.example.calendardevelop.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.CalendarRequest;
import org.example.calendardevelop.dto.CalendarResponse;
import org.example.calendardevelop.service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarContoller {
    private final CalendarService calendarService;

    //Create
    @PostMapping("/calendars")
    public CalendarResponse createCalendar(@RequestBody CalendarRequest calendarRequest) { // 클라이언트가 BODY에 REQUEST DTO를 채울 내용을 입력하고 RESPONSE DTO를 받는다.
        return calendarService.createCalendar(calendarRequest); // createCalendar 메서드의 리턴 타입과 같다.
    }

    @GetMapping("/calendars")
    public List<CalendarResponse> getCalendars(@RequestParam(required = false) String name) {
        return calendarService.readAllCalendar(name);
    }

    @GetMapping("/calendars/{calenderId}")
    public CalendarResponse getCalendar(@PathVariable Long calenderId) {
        return calendarService.readByIdCalendar(calenderId);
    }
    @PutMapping("/calendars/{calenderId}")
    public CalendarResponse updateCalendar(@PathVariable Long calenderId, @RequestBody CalendarRequest calendarRequest) {
        return calendarService.updateCalendar(calenderId, calendarRequest);
    }
    @DeleteMapping("/calendars/{calenderId}")
    public String deleteCalendar(@PathVariable Long calenderId) {
        return "삭제되었습니다.";
    }
}
