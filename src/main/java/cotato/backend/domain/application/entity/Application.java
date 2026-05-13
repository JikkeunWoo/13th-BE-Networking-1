package cotato.backend.domain.application.entity;

import cotato.backend.domain.applicant.entity.Applicant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false, updatable = false)
    private Applicant applicant;

    @Column(updatable = false, nullable = false)
    private Integer period; // 지원 기수

    @Column(updatable = false, length = 20)
    private String part; // 지원 파트

    @Column(updatable = false)
    private Integer ability; // 실력 (0~10)

    @Column(updatable = false)
    private Integer passion; // 열정 (0~10)

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(updatable = false)
    private LocalDateTime applicationTime; // 제출 시간

    // 요구사항 '좋아요 순 정렬'을 쉽게 구현하기 위한 필드
    private int likeCount = 0; 

    @Builder
    public Application(Applicant applicant, Integer period, String part, Integer ability, Integer passion, LocalDateTime applicationTime) {
        this.applicant = applicant;
        this.period = period;
        this.part = part;
        this.ability = ability;
        this.passion = passion;
        this.applicationTime = applicationTime;
    }

    public void addLike() {
        this.likeCount++;
    }
}