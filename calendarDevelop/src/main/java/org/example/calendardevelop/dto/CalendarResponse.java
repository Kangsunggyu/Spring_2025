package org.example.calendardevelop.dto;
import lombok.Getter;
import org.example.calendardevelop.comment.dto.CommentResponse;
import org.example.calendardevelop.comment.entity.CommentEntity;
import org.example.calendardevelop.entity.CalendarEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 서버가 클라이언트에게 보낼 데이터를 담는 객체
@Getter
public class CalendarResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final List<CommentResponse> comments;

    // 생성자
    public CalendarResponse(CalendarEntity calendarEntity) {
        this.id = calendarEntity.getId();
        this.title = calendarEntity.getTitle();
        this.content = calendarEntity.getContent();
        this.createdDate = calendarEntity.getCreatedDate();
        this.modifiedDate = calendarEntity.getModifiedDate();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for(CommentEntity commentEntity : calendarEntity.getComments()){
            commentResponses.add(new CommentResponse(commentEntity));
        }
        this.comments = commentResponses;
    }
}
