package org.example.schedule.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.user.entity.BaseTimeEntity;
import org.example.schedule.user.entity.UserEntity;

@Getter
@Entity
@NoArgsConstructor
public class CalendarEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private String title;
    private String content;

    public CalendarEntity(UserEntity userEntity, String title, String content) {
        this.userEntity = userEntity;
        this.title = title;
        this.content = content;
    }

    public void calendarUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
