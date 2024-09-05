package com.backend.demo.controller;

import com.backend.demo.dto.EmployeeRequestDTO;
import com.backend.demo.dto.EmployeeResponseDTO;
import com.backend.demo.mapper.EmployeeMapper;
import com.backend.demo.model.Employee;
import com.backend.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(
            EmployeeService employeeService,
            EmployeeMapper employeeMapper) {

        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public ResponseEntity<?> allEmployees() {

        List<Employee> employees = employeeService.retrieveAll();
        List<EmployeeResponseDTO> employeeResponseDTOs = employees.stream()
                .map(employeeMapper::toDTO)
                .toList();

        return new ResponseEntity<>(employeeResponseDTOs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = employeeService.create(employeeRequestDTO);
        return new ResponseEntity<>(employeeMapper.toDTO(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = employeeService.update(id, employeeRequestDTO);
        return new ResponseEntity<>(employeeMapper.toDTO(employee), HttpStatus.OK);
    }
}
