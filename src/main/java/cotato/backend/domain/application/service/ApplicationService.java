package cotato.backend.domain.application.service;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import cotato.backend.domain.application.dto.ApplicationCreateRequest;
import cotato.backend.domain.application.dto.ApplicationDetailResponse;
import cotato.backend.domain.application.dto.ApplicationFilterRequest;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    public void createApplication(ApplicationCreateRequest req) {
        // 1. 번호로 기존 지원자 확인. 없으면 새로 생성
        Applicant applicant = applicantRepository.findByPhoneNumber(req.getPhoneNumber())
                .orElseGet(() -> Applicant.builder()
                        .phoneNumber(req.getPhoneNumber())
                        .name(req.getName())
                        .age(req.getAge())
                        .build());

        // 동일 번호 재지원 시 최신 정보로 갱신
        applicant.updateInfo(req.getName(), req.getAge());
        applicantRepository.save(applicant);

        // 2. 서류 저장
        Application application = Application.builder()
                .applicant(applicant)
                .period(req.getPeriod())
                .part(req.getPart())
                .ability(req.getAbility())
                .passion(req.getPassion())
                .applicationTime(req.getApplicationTime())
                .build();

        applicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public Page<Application> filterApplications(ApplicationFilterRequest req) {
        Pageable pageable;
        int page = req.getPage() > 0 ? req.getPage() - 1 : 0; // Spring Data JPA는 0페이지부터 시작

        switch (req.getFilterBy()) {
            case "likes":
                pageable = PageRequest.of(page, req.getPageSize(), Sort.by(Sort.Direction.DESC, "likeCount"));
                break;
            case "gisu":
                pageable = PageRequest.of(page, req.getPageSize(), Sort.by(Sort.Direction.DESC, "period"));
                break;
            case "gisu+likes":
                pageable = PageRequest.of(page, req.getPageSize(),
                        Sort.by(Sort.Direction.DESC, "period").and(Sort.by(Sort.Direction.DESC, "likeCount")));
                break;
            default:
                pageable = PageRequest.of(page, req.getPageSize());
        }

        return applicationRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public ApplicationDetailResponse getApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("지원 서류를 찾을 수 없습니다."));
        return new ApplicationDetailResponse(application);
    }
}