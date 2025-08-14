package org.example.calendardevelop.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.comment.dto.CommentRequest;
import org.example.calendardevelop.comment.dto.CommentResponse;
import org.example.calendardevelop.comment.entity.CommentEntity;
import org.example.calendardevelop.comment.repository.CommentRepository;
import org.example.calendardevelop.entity.CalendarEntity;
import org.example.calendardevelop.entity.UserEntity;
import org.example.calendardevelop.repository.CalendarRepository;
import org.example.calendardevelop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional // 일정에 댓글을 입력한다고 생각하면 편한다. //calendar에 comment를 단다.
    public CommentResponse addComment(CommentRequest commentRequest, long userId, long calendarId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        CalendarEntity calendarEntity = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("캘린더가 존재하지 않습니다."));
         CommentEntity commentEntity = new CommentEntity(
                 commentRequest.getContent(),
                 userEntity,
                 calendarEntity
         );
         CommentEntity savedComment = commentRepository.save(commentEntity);
         return new CommentResponse(savedComment);
    }
    @Transactional // 특정 캘린더ID의 모든 댓글 조회
    public List<CommentResponse> getCommentsByCalendarId(Long calendarId) {
        // 특정 캘린더의 모든 댓글 조회
        CalendarEntity calendarEntity = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("캘린더가 존재하지 않습니다."));

        List<CommentEntity> comments = commentRepository.findByCalendarEntityId(calendarId);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (CommentEntity comment : comments) {
            commentResponses.add(new CommentResponse(comment));
        }
        return commentResponses;
    }

    @Transactional
    public void deleteComment(Long userId,Long commentId) {
        // 댓글 엔티티 조회
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        // 댓글 작성자와 현재 로그인한 사용자가 동일한지 확인한다.
        if (!comment.getUserEntity().getId().equals(userId)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }
}
