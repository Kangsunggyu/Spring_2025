package org.example.schedule.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.calendar.entity.CalendarEntity;
import org.example.schedule.calendar.repository.CalendarRepository;
import org.example.schedule.comment.dto.CommentRequest;
import org.example.schedule.comment.dto.CommentResponse;
import org.example.schedule.comment.entity.CommentEntity;
import org.example.schedule.comment.repository.CommentRepository;
import org.example.schedule.user.entity.UserEntity;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public CommentResponse createComment(Long userId, Long scheduleId, CommentRequest commentRequest) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        CalendarEntity calendarEntity = calendarRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 캘린더 id는 존재하지 않습니다."));
        CommentEntity  commentEntity = new CommentEntity(userEntity, calendarEntity, commentRequest.getComment());
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);
        return new CommentResponse(
                savedCommentEntity.getId(),
                savedCommentEntity.getUserEntity().getId(),
                savedCommentEntity.getCalendarEntity().getId(),
                savedCommentEntity.getContent(),
                savedCommentEntity.getCreatedDate(),
                savedCommentEntity.getLastModifiedDate()
        );
    }
    @Transactional // 켈린더 id를 입력하면 해당 id로 comment를 출력한다.
    public List<CommentResponse> getAllByCalendarId(Long scheduleId) {
        CalendarEntity calendarEntity = calendarRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 캘린더 id는 존재하지 않습니다."));
        List<CommentEntity> commentEntities = commentRepository.findByCalendarEntity_Id(scheduleId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentResponse commentResponse = new CommentResponse(
                    commentEntity.getId(),
                    commentEntity.getUserEntity().getId(),
                    commentEntity.getCalendarEntity().getId(),
                    commentEntity.getContent(),
                    commentEntity.getCreatedDate(),
                    commentEntity.getLastModifiedDate()
            );
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글 id는 존재하지 않습니다."));
        if(userId.equals(commentEntity.getUserEntity().getId())) {
            commentRepository.deleteById(commentId);
        }else{
            throw new IllegalArgumentException("본인의 댓글이 아닙니다.");
        }
    }
}
