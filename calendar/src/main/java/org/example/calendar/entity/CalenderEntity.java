package org.example.calendar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CalenderEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String Content;
    private String name;
    private int password;

    // 생성자
    public CalenderEntity(String title, String Content, String name, int password) {
        this.title = title;
        this.Content = Content;
        this.name = name;
        this.password = password;
    }
}
