package org.example.calendar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 부모 클래스이며, 이 클래스의 필드는 자식 클래스에 매핑되어야 함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate // 엔티티가 처음 생성되었을 때, 현재 시간을 자동으로 주입하는 어노테이션
    @Column(updatable = false) // 한번 생성된 뒤 절대 수정되지 않도록 하는 어노테이션
    private LocalDateTime creationTime;

    @LastModifiedDate // 엔티티가 수정될 때마다 시간을 자동으로 갱신하는 어노테이션
    private LocalDateTime modificationTime;
}
