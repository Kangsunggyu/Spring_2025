package org.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class CalendarEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String name;
    private String password;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<CommentEntity> comments; // 그냘 이걸 변수로 가지네

    // 생성자
    public CalendarEntity(String title, String content, String name, String password) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }
    // service의 update 메서드를 위한 메서드
    public void updateCalendar(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
