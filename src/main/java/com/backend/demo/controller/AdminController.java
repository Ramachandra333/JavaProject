package com.backend.demo.controller;

import com.backend.demo.dto.AdminLoginRequestDTO;
import com.backend.demo.dto.AdminLoginResponseDTO;
import com.backend.demo.dto.AdminRequestDTO;
import com.backend.demo.mapper.AdminMapper;
import com.backend.demo.model.Admin;
import com.backend.demo.service.AdminService;
import com.backend.demo.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/auth")
public class AdminController {
    private final AdminService adminService;

    private final JwtService jwtService;

    private final AdminMapper adminMapper;

    @Autowired
    public AdminController(
            AdminService adminService,
            JwtService jwtService,
            AdminMapper adminMapper) {

        this.adminService = adminService;
        this.jwtService = jwtService;
        this.adminMapper = adminMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody AdminRequestDTO adminRequestDTO) {
        Admin admin = adminService.signUp(adminRequestDTO);

        return new ResponseEntity<>(adminMapper.toDTO(admin), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody AdminLoginRequestDTO adminLoginRequestDTO) {
        Admin admin = adminService.signIn(adminLoginRequestDTO);
        AdminLoginResponseDTO adminLoginResponseDTO = AdminLoginResponseDTO.builder()
                .token(jwtService.generateToken(admin))
                .expirationTime(jwtService.getExpirationTime())
                .build();
        return new ResponseEntity<>(adminLoginResponseDTO, HttpStatus.ACCEPTED);
    }
}
