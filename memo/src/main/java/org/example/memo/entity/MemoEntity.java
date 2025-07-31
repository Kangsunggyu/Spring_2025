package org.example.memo.entity;

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
public class MemoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String content;
    private LocalDateTime createTime;

    public MemoEntity(String content) {
        this.createTime = LocalDateTime.now(); // 이 객체를 만든 시간
        this.content = content;
    }
    public void updateMemo(String content){
        this.content = content;
        this.createTime = LocalDateTime.now();
    }
}
