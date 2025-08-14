package org.example.calendardevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendardevelop.comment.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class CalendarEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "calendarEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<CommentEntity> comments = new ArrayList<>();
    private String title;
    private String content;

    // 생성자
    public CalendarEntity(String title, String content, UserEntity userEntity) {
        this.title = title;
        this.content = content;
        this.userEntity = userEntity;
    }

    public void updateCalendar(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
