package cotato.backend.domain.application.dto;

import cotato.backend.domain.application.entity.Application;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ApplicationDetailResponse {
    private String applicantName;
    private Integer period;
    private Integer age;
    private String part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applicationTime;

    public ApplicationDetailResponse(Application application) {
        this.applicantName = application.getApplicant().getName();
        this.period = application.getPeriod();
        this.age = application.getApplicant().getAge();
        this.part = application.getPart();
        this.ability = application.getAbility();
        this.passion = application.getPassion();
        this.phoneNumber = application.getApplicant().getPhoneNumber();
        this.applicationTime = application.getApplicationTime();
    }
}