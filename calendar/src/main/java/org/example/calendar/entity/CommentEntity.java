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

    @ManyToOne(fetch =  FetchType.LAZY) // CalendarEntity와의 Many To One 관계 설정, 그리고 Lazy 즉 실제 데이터는 가져오지 않고 더미로
    @JoinColumn(name = "calendar_id") // 외래 키 컬렴명 지정
    private CalendarEntity calendar;

    public CommentEntity(String content, String name, String password, CalendarEntity calendar) {
        this.content = content;
        this.name = name;
        this.password = password;
        this.calendar = calendar;
    }
}

