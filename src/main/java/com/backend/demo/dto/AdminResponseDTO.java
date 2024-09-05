package com.backend.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private Long id;
    private String username;
    private String manager;
    private String fistName;
    private String lastName;
    private String email;
    private String phone;
}
