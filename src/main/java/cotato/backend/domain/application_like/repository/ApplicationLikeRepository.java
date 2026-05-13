package cotato.backend.domain.application_like.repository;

import cotato.backend.domain.application_like.entity.ApplicationLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, Long> {
    boolean existsByManagerIdAndApplicationId(Long managerId, Long applicationId);
}