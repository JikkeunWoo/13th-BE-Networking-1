package cotato.backend.domain.manager.dto;

import cotato.backend.domain.manager.entity.Manager;
import lombok.Getter;

@Getter
public class ManagerResponse {
    private String name;
    private Integer age;
    private String phoneNumber;
    private String role;

    public ManagerResponse(Manager manager) {
        this.name = manager.getName();
        this.age = manager.getAge();
        this.phoneNumber = manager.getPhoneNumber();
        this.role = manager.getRole();
    }
}