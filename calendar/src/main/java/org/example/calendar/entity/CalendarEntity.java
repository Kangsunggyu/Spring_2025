package org.example.calendar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;

@Getter
@Entity
@NoArgsConstructor
public class CalendarEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String Content;
    private String name;
    private String password;

    // 생성자
    public CalendarEntity(String title, String Content, String name, String password) {
        this.title = title;
        this.Content = Content;
        this.name = name;
        this.password = password;
    }
    // service의 update 메서드를 위한 메서드
    public void updateCalendar(String title, String name) {
        this.title = title;
        this.name = name;
    }

}
