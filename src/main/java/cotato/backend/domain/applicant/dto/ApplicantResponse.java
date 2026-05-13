package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant;
import lombok.Getter;

@Getter
public class ApplicantResponse {
    private String name;
    private Integer age;
    private String phoneNumber;

    public ApplicantResponse(Applicant applicant) {
        this.name = applicant.getName();
        this.age = applicant.getAge();
        this.phoneNumber = applicant.getPhoneNumber();
    }
}