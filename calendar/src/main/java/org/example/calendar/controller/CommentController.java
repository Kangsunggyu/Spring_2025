package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CommentRequestDto;
import org.example.calendar.dto.CommentResponseDto;
import org.example.calendar.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }
    @GetMapping("/comments/{commentId}") // 댓글 조회
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long commentId,
            @RequestParam(defaultValue = "0") int page, // page 파라미터를 받아 현재 페이지 번호를 지정
            @RequestParam(defaultValue = "5") int size) { // 한 페이지에 보여줄 댓글의 수
        return ResponseEntity.ok(commentService.commentAll(commentId, page, size));
    }

}