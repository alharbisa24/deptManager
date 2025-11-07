package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateEmployeeDTO;
import com.example.demo.dto.UpdateEmployeeDTO;
import com.example.demo.models.Employee;

@Service
public interface EmployeeService {

    public List<Employee> getAll();
    Employee saveEmployee(CreateEmployeeDTO dto);
    Employee updateEmployee(UpdateEmployeeDTO dto);
    Employee getEmployeeById(Long id);
    public Page<Employee> getAllByPagination(int page, int size);
    void deleteEmployeeById(Long id);
}
