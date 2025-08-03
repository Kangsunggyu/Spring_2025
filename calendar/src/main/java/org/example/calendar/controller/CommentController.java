package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CommentRequestDto;
import org.example.calendar.dto.CommentResponseDto;
import org.example.calendar.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }
}