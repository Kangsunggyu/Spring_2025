package org.example.calendardevelop.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendardevelop.entity.BaseTimeEntity;
import org.example.calendardevelop.entity.CalendarEntity;
import org.example.calendardevelop.entity.UserEntity;

@Getter
@Entity
@NoArgsConstructor
public class CommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendarEntity;

    public CommentEntity(String content, UserEntity userEntity, CalendarEntity calendarEntity) {
        this.content = content;
        this.userEntity = userEntity;
        this.calendarEntity = calendarEntity;
    }
}
