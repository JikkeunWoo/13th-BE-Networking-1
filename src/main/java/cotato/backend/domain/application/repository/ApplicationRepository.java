package cotato.backend.domain.application.repository;

import cotato.backend.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // 필터링은 Service 단에서 PageRequest 정렬 조건을 바꿔서 findAll()로 처리합니다.
}