package org.example.schedule.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.calendar.entity.CalendarEntity;
import org.example.schedule.user.entity.BaseTimeEntity;
import org.example.schedule.user.entity.UserEntity;

@Getter
@Entity
@NoArgsConstructor
public class CommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private CalendarEntity calendarEntity;
    private String content;

    public CommentEntity( UserEntity userEntity, CalendarEntity calendarEntity, String content) {
        this.userEntity = userEntity;
        this.calendarEntity = calendarEntity;
        this.content = content;
    }
    public void update(CommentEntity commentEntity) {
        this.content = commentEntity.getContent();
    }
}
