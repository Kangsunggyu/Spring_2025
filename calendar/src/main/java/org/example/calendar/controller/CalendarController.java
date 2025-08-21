package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.dto.CommentResponseDto;
import org.example.calendar.service.CalenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalenderService calenderService;

    //create
    @PostMapping("/calendars")
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto calendarRequestDto) {
        return calenderService.createCalender(calendarRequestDto);
    }

    /*@GetMapping("/calendars")
    public List<CalendarResponseDto> readAllCalendars(@RequestParam(required = false) String name) {
        //@RequestParam은 get calendars name = 홍길동 요청이 오면, name = 홍길동에서 홍길동의 값읗 추출해 String name 변수에 넣어준다.
        //required = false 는 필수가 아님을 나타낸다.
        return calenderService.readAllCalender(name);
    }
     */

    @GetMapping("/calendars") // 댓글 조회
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long commentId,
            @RequestParam(defaultValue = "0") int page, // page 파라미터를 받아 현재 페이지 번호를 지정
            @RequestParam(defaultValue = "5") int size) { // 한 페이지에 보여줄 댓글의 수
        return ResponseEntity.ok(commentService.commentAll(commentId, page, size));
    }

    @GetMapping("/calendars/{calenderId}")
    public CalendarResponseDto readCalendar(@PathVariable Long calenderId) {
        return calenderService.readByIdCalender(calenderId);
    }
    @PutMapping("/calendars/{calenderId}")
    public CalendarResponseDto updateCalendar(@PathVariable Long calenderId, @RequestBody CalendarRequestDto calendarRequestDto) {
        return calenderService.updateCalender(calenderId, calendarRequestDto);
    }
    @DeleteMapping("/calendars/{calenderId}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long calenderId, @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");
        calenderService.deleteCalender(calenderId, password);
        return ResponseEntity.noContent().build();
    }
}
