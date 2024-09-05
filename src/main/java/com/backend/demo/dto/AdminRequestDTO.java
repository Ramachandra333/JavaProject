package com.backend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDTO {
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

    @NotBlank(message = "username can't be blank.")
    private String username;

    @NotBlank(message = "password can't be blank.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8-20 characters long, include at least one digit, one uppercase letter, one lowercase letter, and one special character.")
    private String password;
}
