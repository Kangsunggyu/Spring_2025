package org.example.member.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member_Entity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String email;

    // 생성자
    public Member_Entity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
