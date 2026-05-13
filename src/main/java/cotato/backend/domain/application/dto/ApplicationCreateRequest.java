package cotato.backend.domain.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ApplicationCreateRequest {
    @Size(min = 2, max = 10, message = "이름은 2~10자여야 합니다.")
    private String name;

    @Min(value = 1, message = "기수는 1 이상이어야 합니다.")
    private Integer period;

    @Min(22) @Max(30)
    private Integer age;

    private String part;

    @Min(0) @Max(10)
    private Integer ability;

    @Min(0) @Max(10)
    private Integer passion;

    @Pattern(regexp = "^010\\d{8}$", message = "010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applicationTime;
}