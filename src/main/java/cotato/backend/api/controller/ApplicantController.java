package cotato.backend.api.controller;

import cotato.backend.domain.applicant.dto.ApplicantResponse;
import cotato.backend.domain.applicant.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    // 지원자 단건 조회 API
    @GetMapping("/{applicantId}")
    public ResponseEntity<ApplicantResponse> getApplicant(@PathVariable Long applicantId) {
        return ResponseEntity.ok(applicantService.getApplicant(applicantId));
    }
    
}