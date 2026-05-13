package cotato.backend.domain.applicant.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 11, nullable = false, unique = true)
    private String phoneNumber;

    @Builder
    public Applicant(String name, Integer age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // 지원자 정보 수정 (요청 시 기존 지원자 정보 업데이트용)
    public void updateInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}