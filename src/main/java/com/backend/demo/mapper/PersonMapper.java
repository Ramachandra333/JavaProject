package com.backend.demo.mapper;

import com.backend.demo.dto.AdminRequestDTO;
import com.backend.demo.dto.EmployeeRequestDTO;
import com.backend.demo.model.Person;
import com.backend.demo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    private final PersonRepository personRepository;

    @Autowired
    public PersonMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person toEntity(EmployeeRequestDTO employeeRequestDTO) {

        return Person.builder()
                .firstName(employeeRequestDTO.getFirstName())
                .lastName(employeeRequestDTO.getLastName())
                .email(employeeRequestDTO.getEmail())
                .phone(employeeRequestDTO.getPhone())
                .build();
    }

    public Person toEntity(AdminRequestDTO adminRequestDTO) {

        return Person.builder()
                .firstName(adminRequestDTO.getFirstName())
                .lastName(adminRequestDTO.getLastName())
                .email(adminRequestDTO.getEmail())
                .phone(adminRequestDTO.getPhone())
                .build();
    }

    public Person toUpdatedEntity(Long employeeId, EmployeeRequestDTO employeeRequestDTO) {
        Person person = personRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Person does not exist."));

        person.setEmail(employeeRequestDTO.getEmail());
        person.setFirstName(employeeRequestDTO.getFirstName());
        person.setLastName(employeeRequestDTO.getLastName());
        person.setPhone(employeeRequestDTO.getPhone());

        return person;
    }
}
