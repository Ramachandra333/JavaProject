package com.backend.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmployeeRequestDTO {
    @NotBlank(message = "firstName can't be blank")
    private String firstName;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email address")
    @NotBlank(message = "email can't be blank")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    @NotBlank(message = "phone can't be blank")
    private String phone;

    @NotBlank(message = "Role can't be blank")
    private String role;

    @NotNull(message = "salary can't be null")
    private Long salary;

    @NotNull(message = "startDate can't be null")
    private LocalDateTime startDate;

    @NotBlank(message = "manager can't be blank")
    private String manager;
}
