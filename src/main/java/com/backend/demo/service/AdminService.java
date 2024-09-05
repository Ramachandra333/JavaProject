package com.backend.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.backend.demo.dto.AdminLoginRequestDTO;
import com.backend.demo.dto.AdminRequestDTO;
import com.backend.demo.mapper.AdminMapper;
import com.backend.demo.mapper.PersonMapper;
import com.backend.demo.model.Admin;
import com.backend.demo.model.Person;
import com.backend.demo.repository.AdminRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    private final PersonService personService;

    private final AdminMapper adminMapper;

    private final PersonMapper personMapper;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AdminService(
            AdminRepository adminRepository,
            PersonMapper personMapper,
            AdminMapper adminMapper,
            PersonService personService,
            AuthenticationManager authenticationManager) {

        this.adminRepository = adminRepository;
        this.personMapper = personMapper;
        this.adminMapper = adminMapper;
        this.personService = personService;
        this.authenticationManager = authenticationManager;
    }

    public Admin signUp(AdminRequestDTO adminRequestDTO) {
        if (adminRepository.existsByUsername(adminRequestDTO.getUsername())) {
            throw new EntityExistsException("Admin with given username already exists.");
        }
        Person person = personService.create(personMapper.toEntity(adminRequestDTO));
        return adminRepository.save(adminMapper.toEntity(adminRequestDTO, person));
    }

    public Admin signIn(AdminLoginRequestDTO adminLoginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        adminLoginRequestDTO.getUsername(),
                        adminLoginRequestDTO.getPassword()
                )
        );

        return adminRepository.findByUsername(adminLoginRequestDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Admin does not exist with given username."));
    }
}
