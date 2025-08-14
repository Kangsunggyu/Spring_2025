package org.example.calendardevelop.comment.controller;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.comment.dto.CommentRequest;
import org.example.calendardevelop.comment.dto.CommentResponse;
import org.example.calendardevelop.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/users/{userId}/calendars/{calendarId}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @RequestBody CommentRequest request,
            @PathVariable Long userId,
            @PathVariable Long calendarId){
        return ResponseEntity.ok(commentService.addComment(request, userId, calendarId));
    }
    // 특정 calendar의 댓글을 가져오기
    @GetMapping("/calendars/{calendarId}/comments")
    public ResponseEntity<List<CommentResponse>> getAllComments(@PathVariable Long calendarId){
        return ResponseEntity.ok(commentService.getCommentsByCalendarId(calendarId));
    }

    @DeleteMapping("/users/{userId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(
            @PathVariable Long userId,
            @PathVariable Long commentId){
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}