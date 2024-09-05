package com.backend.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.demo.dto.EmployeeRequestDTO;
import com.backend.demo.dto.EmployeeResponseDTO;
import com.backend.demo.model.Employee;
import com.backend.demo.model.Person;
import com.backend.demo.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EmployeeMapper {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeMapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee toEntity(EmployeeRequestDTO employeeRequestDTO, Person person) {

        return Employee.builder()
                .role(employeeRequestDTO.getRole())
                .manager(employeeRequestDTO.getManager())
                .person(person)
                .salary(employeeRequestDTO.getSalary())
                .startDate(employeeRequestDTO.getStartDate())
                .build();
    }

    public Employee toUpdatedEntity(EmployeeRequestDTO employeeRequestDTO, Person person) {
        Employee employee = employeeRepository.findById(person.getEmployee().getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee does not exist."));

        employee.setRole(employeeRequestDTO.getRole());
        employee.setManager(employeeRequestDTO.getManager());
        employee.setPerson(person);
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setStartDate(employeeRequestDTO.getStartDate());

        return employee;
    }

    public EmployeeResponseDTO toDTO(Employee employee) {

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .salary(employee.getSalary())
                .email(employee.getPerson().getEmail())
                .startDate(employee.getStartDate())
                .role(employee.getRole())
                .fistName(employee.getPerson().getFirstName())
                .lastName(employee.getPerson().getLastName())
                .phone(employee.getPerson().getPhone())
                .manager(employee.getManager())
                .build();
    }
}
