package cotato.backend.domain.applicant.service;

import cotato.backend.domain.applicant.dto.ApplicantResponse;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    @Transactional(readOnly = true)
    public ApplicantResponse getApplicant(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다."));
        return new ApplicantResponse(applicant);
    }
}