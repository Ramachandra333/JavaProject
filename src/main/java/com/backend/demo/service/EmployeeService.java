package com.backend.demo.service;

import com.backend.demo.dto.EmployeeRequestDTO;
import com.backend.demo.mapper.EmployeeMapper;
import com.backend.demo.mapper.PersonMapper;
import com.backend.demo.model.Employee;
import com.backend.demo.model.Person;
import com.backend.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final PersonService personService;

    private final PersonMapper personMapper;

    private final EmployeeMapper employeeMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            PersonService personService,
            PersonMapper personMapper,
            EmployeeMapper employeeMapper) {

        this.employeeRepository = employeeRepository;
        this.personService = personService;
        this.personMapper = personMapper;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> retrieveAll() {
        return employeeRepository.findAll();
    }

    public Employee create(EmployeeRequestDTO employeeRequestDTO) {
        Person person = personService.create(personMapper.toEntity(employeeRequestDTO));
        return employeeRepository.save(employeeMapper.toEntity(employeeRequestDTO, person));
    }

    public Employee update(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee does not exist."));
        Person person = personService.update(personMapper.toUpdatedEntity(id, employeeRequestDTO));
        return employeeRepository.save(employeeMapper.toUpdatedEntity(employeeRequestDTO, person));
    }
}
