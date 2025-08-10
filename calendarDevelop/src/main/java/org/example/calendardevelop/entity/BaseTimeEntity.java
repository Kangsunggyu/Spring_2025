package org.example.calendardevelop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // BaseTimeEntity 자체로는 테이블이 안 만들어지고, 자식 엔티티에 createTime, modificationTime 변수가 자동으로 포함된다.
@EntityListeners(AuditingEntityListener.class) // 엔티티의 생성/수정 이벤트를 감지해서 필드를 자동으로 채워주는 감시 리스너,
public class BaseTimeEntity {
    @CreatedDate // 엔티티가 처음 생성되었을 때, 현재 시간을 자동으로 주입하는 어노테이션
    @Column(updatable = false) // 한번 생성된 뒤 절대 수정되지 않도록 하는 어노테이션
    private LocalDateTime creationTime;

    @LastModifiedDate // 엔티티가 수정될 때마다 시간을 자동으로 갱신하는 어노테이션
    private LocalDateTime modificationTime;
}
