package org.example.schedule.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.comment.dto.CommentRequest;
import org.example.schedule.comment.dto.CommentResponse;
import org.example.schedule.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/calendars/{calendarId}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long calendarId,
            @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.createComment(userId, calendarId, commentRequest));
    }

    @GetMapping("/calendars/{calendarId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long calendarId){
        return ResponseEntity.ok(commentService.getAllByCalendarId(calendarId));
    }

    @DeleteMapping("comments/{commentId}")
    public void deleteComment(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long commentId){
        commentService.deleteComment(userId, commentId);
    }
}

/*
@PostMapping("users/{userId}/calendars/{calendarId}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long userId,
            @PathVariable Long calendarId,
            @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.createComment(userId, calendarId, commentRequest));
    }

    @GetMapping("/calendars/{calendarId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long calendarId){
        return ResponseEntity.ok(commentService.getAllByCalendarId(calendarId));
    }

    @DeleteMapping("userId/{userId}/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long userId,
            @PathVariable Long commentId){
        commentService.deleteComment(userId, commentId);
    }
 */