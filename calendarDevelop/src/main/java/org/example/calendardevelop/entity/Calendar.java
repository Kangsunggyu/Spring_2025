package org.example.calendardevelop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    // 생성자
    public Calendar(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    // 업데이트
    public void updateCalendar(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
