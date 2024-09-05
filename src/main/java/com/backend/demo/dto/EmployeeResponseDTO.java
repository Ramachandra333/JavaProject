package com.backend.demo.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String role;
    private Long salary;
    private LocalDateTime startDate;
    private String manager;
    private String fistName;
    private String lastName;
    private String email;
    private String phone;
}
