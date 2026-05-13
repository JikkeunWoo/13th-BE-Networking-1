package cotato.backend.domain.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    private Integer age;

    @Column(length = 11, nullable = false)
    private String phoneNumber;

    @Column(length = 20)
    private String role; // 파트장, 기획팀장 등
}