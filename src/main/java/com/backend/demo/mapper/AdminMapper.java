package com.backend.demo.mapper;

import com.backend.demo.dto.AdminLoginResponseDTO;
import com.backend.demo.dto.AdminRequestDTO;
import com.backend.demo.dto.AdminResponseDTO;
import com.backend.demo.model.Admin;
import com.backend.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Admin toEntity(AdminRequestDTO adminRequestDTO, Person person) {

        return Admin.builder()
                .person(person)
                .password(passwordEncoder.encode(adminRequestDTO.getPassword()))
                .username(adminRequestDTO.getUsername())
                .build();
    }

    public AdminResponseDTO toDTO(Admin admin) {

        return AdminResponseDTO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .fistName(admin.getPerson().getFirstName())
                .lastName(admin.getPerson().getLastName())
                .email(admin.getPerson().getEmail())
                .phone(admin.getPerson().getPhone())
                .build();
    }
}
