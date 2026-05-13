package cotato.backend.domain.manager.service;

import cotato.backend.domain.manager.dto.ManagerResponse;
import cotato.backend.domain.manager.entity.Manager;
import cotato.backend.domain.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Transactional(readOnly = true)
    public ManagerResponse getManager(Long managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("운영진을 찾을 수 없습니다."));
        return new ManagerResponse(manager);
    }
}