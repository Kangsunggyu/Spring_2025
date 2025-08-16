package org.example.schedule.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.calendar.dto.CalendarRequest;
import org.example.schedule.calendar.dto.CalendarResponse;
import org.example.schedule.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/users/{userId}/calendars") // 입력하면 calendar 생성
    public ResponseEntity<CalendarResponse> addCalendar(@PathVariable Long userId, @RequestBody CalendarRequest calendarRequest){
        return ResponseEntity.ok(calendarService.createCalendar(userId, calendarRequest));

    }

    @GetMapping("/users/{userId}/calendars") //userId의 모든 calendar 출력
    public ResponseEntity<List<CalendarResponse>> getCalendars(@PathVariable Long userId){
        return ResponseEntity.ok(calendarService.getAllByUserId(userId));
    }

    @PutMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarResponse> updateCalendar(@PathVariable Long calendarId, @RequestBody CalendarRequest calendarRequest){
        return ResponseEntity.ok(calendarService.updateCalendar(calendarId, calendarRequest));
    }

    @DeleteMapping("/users/{userId}/calendars/{calendarId}")
    public void deleteCalendar(
            @PathVariable Long userId,
            @PathVariable Long calendarId){
        calendarService.deleteCalendar(userId, calendarId);
    }
}
