package cotato.backend.domain.application_like.service;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.repository.ApplicationRepository;
import cotato.backend.domain.application_like.entity.ApplicationLike;
import cotato.backend.domain.application_like.repository.ApplicationLikeRepository;
import cotato.backend.domain.manager.entity.Manager;
import cotato.backend.domain.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationLikeService {

    private final ApplicationLikeRepository applicationLikeRepository;
    private final ApplicationRepository applicationRepository;
    private final ManagerRepository managerRepository;

    @Transactional
    public void addLike(Long applicationId, Long managerId) {
        // 이미 누른 좋아요인지 검증 (중복 방지)
        if (applicationLikeRepository.existsByManagerIdAndApplicationId(managerId, applicationId)) {
            throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("서류를 찾을 수 없습니다."));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("운영진을 찾을 수 없습니다."));

        ApplicationLike like = ApplicationLike.builder()
                .application(application)
                .manager(manager)
                .build();

        applicationLikeRepository.save(like);

        // 서류의 캐싱된 좋아요 수 1 증가
        application.addLike();
    }
}