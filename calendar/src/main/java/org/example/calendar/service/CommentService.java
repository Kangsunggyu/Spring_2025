package org.example.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.dto.CommentRequestDto;
import org.example.calendar.dto.CommentResponseDto;
import org.example.calendar.entity.CalendarEntity;
import org.example.calendar.entity.CommentEntity;
import org.example.calendar.repository.CalendarRepository;
import org.example.calendar.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        CalendarEntity calendar = calendarRepository.findById(commentRequestDto.getCalendarId()).orElseThrow(() -> new IllegalArgumentException("해당 캘린더를 찾을 수 없습니다."));
        if (calendar.getComments().size() >= 10) {
            throw new IllegalStateException("하나의 일정에는 댓글을 10개까지만 작성할 수 있습니다.");
        }
        CommentEntity comment = new CommentEntity(
                commentRequestDto.getContent(),
                commentRequestDto.getName(),
                commentRequestDto.getPassword(),
                calendar
        );
        CommentEntity savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }


    @Transactional
    public List<CommentResponseDto> commentAll(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommentEntity> commentPage = commentRepository.findByCalendar_Id(postId, pageable);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        for (CommentEntity comment : commentPage.getContent()) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            responseDtoList.add(commentResponseDto);
        }
        return responseDtoList;

    }
}