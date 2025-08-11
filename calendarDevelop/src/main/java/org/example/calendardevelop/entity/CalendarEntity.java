package org.example.calendardevelop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CalendarEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String title;
    private String content;

    // 생성자
    public CalendarEntity(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void updateCalendar(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
