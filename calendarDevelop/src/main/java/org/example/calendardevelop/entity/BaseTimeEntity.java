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
@MappedSuperclass // 해당 엔티티를 통해 직접 DB 테이블이 생성되지 않고, 이 클래스를 상속 받는 자식 엔티티(클래스)에게 해당 클래스에 정의된 필드가 자식 엔티티의 테이블 컬럼으로 추가된다는 것을 의미
@EntityListeners(AuditingEntityListener.class) // CreateData, LastModifiedDate 어노테이션을 사용하기 위한 어노테이션
public class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false) // 해당 필드를 수정하지 않곘다는 의미
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
