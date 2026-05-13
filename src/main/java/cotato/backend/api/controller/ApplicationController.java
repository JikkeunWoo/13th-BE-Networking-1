package cotato.backend.api.controller;

import cotato.backend.domain.application.dto.ApplicationCreateRequest;
import cotato.backend.domain.application.dto.ApplicationDetailResponse;
import cotato.backend.domain.application.dto.ApplicationFilterRequest;
import cotato.backend.domain.application.service.ApplicationService;
import cotato.backend.domain.application_like.service.ApplicationLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationLikeService applicationLikeService;

    // 1. 서류 등록 API
    @PostMapping
    public ResponseEntity<String> createApplication(@RequestBody @Valid ApplicationCreateRequest request) {
        applicationService.createApplication(request);
        return ResponseEntity.ok("서류가 성공적으로 등록되었습니다.");
    }

    // 2. 서류 필터링 조회 API (과제 명세서의 JSON Body 예시를 따르기 위해 POST 사용)
    @PostMapping("/search")
    public ResponseEntity<?> searchApplications(@RequestBody ApplicationFilterRequest request) {
        return ResponseEntity.ok(applicationService.filterApplications(request));
    }

    // 3. 서류 좋아요 API
    @PostMapping("/{applicationId}/likes")
    public ResponseEntity<String> likeApplication(@PathVariable Long applicationId, @RequestParam Long managerId) {
        // 실제로는 Security Context에서 로그인된 운영진 ID를 가져오지만, 요구사항에 맞춰 파라미터로 받음
        applicationLikeService.addLike(applicationId, managerId);
        return ResponseEntity.ok("좋아요가 반영되었습니다.");
    }

    // 4. 서류 세부 조회 API
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDetailResponse> getApplication(@PathVariable Long applicationId) {
        return ResponseEntity.ok(applicationService.getApplication(applicationId));
    }
}