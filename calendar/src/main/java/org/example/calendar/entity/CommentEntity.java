package org.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String name;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private CalendarEntity calendar;

    public CommentEntity(String content, String name, String password, CalendarEntity calendar) {
        this.content = content;
        this.name = name;
        this.password = password;
        this.calendar = calendar;
    }
}

