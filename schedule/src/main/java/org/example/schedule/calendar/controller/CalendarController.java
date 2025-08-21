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

    @PostMapping("/calendars") // 입력하면 calendar 생성
    public ResponseEntity<CalendarResponse> addCalendar(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @RequestBody CalendarRequest calendarRequest
            ) {
        return ResponseEntity.ok(calendarService.createCalendar(userId, calendarRequest));
    }

    /*@GetMapping("/calendars") //userId의 모든 calendar 출력
    public ResponseEntity<List<CalendarResponse>> getCalendars(
            @SessionAttribute(name = "LOGIN_USER") Long userId) {
        return ResponseEntity.ok(calendarService.getAllByUserId(userId));
    }

     */
    @GetMapping("/calendars")
    public ResponseEntity<List<CalendarResponse>> getCalendars(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @RequestParam(defaultValue = "0") int page, // 페이지 번호
            @RequestParam(defaultValue = "5") int size) { // 페이지 사이즈
        List<CalendarResponse> calendars = calendarService.getAllByUserId(userId, page, size);
        return ResponseEntity.ok(calendars);
    }

    @PutMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarResponse> updateCalendar(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long calendarId,
            @RequestBody CalendarRequest calendarRequest
            ) {
        return ResponseEntity.ok(calendarService.updateCalendar(userId, calendarId, calendarRequest));
    }

    @DeleteMapping("calendars/{calendarId}")
    public void deleteCalendar(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long calendarId
            ) {
        calendarService.deleteCalendar(userId, calendarId);
    }
}


/*
@PostMapping("/users/{userId}/calendars") // 입력하면 calendar 생성
    public ResponseEntity<CalendarResponse> addCalendar(@PathVariable Long userId, @RequestBody CalendarRequest calendarRequest){
        return ResponseEntity.ok(calendarService.createCalendar(userId, calendarRequest));

    }

    @GetMapping("/users/{userId}/calendars") //userId의 모든 calendar 출력
    public ResponseEntity<List<CalendarResponse>> getCalendars(@PathVariable Long userId){
        return ResponseEntity.ok(calendarService.getAllByUserId(userId));
    }

    @PutMapping("/users/{userId}/calendars/{calendarId}")
    public ResponseEntity<CalendarResponse> updateCalendar(@PathVariable Long userId, @PathVariable Long calendarId, @RequestBody CalendarRequest calendarRequest){
        return ResponseEntity.ok(calendarService.updateCalendar(userId, calendarId, calendarRequest));
    }

    @DeleteMapping("/users/{userId}/calendars/{calendarId}")
    public void deleteCalendar(
            @PathVariable Long userId,
            @PathVariable Long calendarId){
        calendarService.deleteCalendar(userId, calendarId);
    }
*/