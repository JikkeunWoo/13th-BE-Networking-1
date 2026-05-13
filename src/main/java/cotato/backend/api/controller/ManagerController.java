package cotato.backend.api.controller;

import cotato.backend.domain.manager.dto.ManagerResponse;
import cotato.backend.domain.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    // 운영진 단건 조회 API
    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerResponse> getManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(managerService.getManager(managerId));
    }
}