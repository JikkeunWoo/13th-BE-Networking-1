package cotato.backend.domain.application.dto;

import lombok.Getter;

@Getter
public class ApplicationFilterRequest {
    private String filterBy; // "likes", "gisu", "gisu+likes"
    private int page;
    private int pageSize;
}