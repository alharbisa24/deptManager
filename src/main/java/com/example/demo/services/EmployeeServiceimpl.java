package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateEmployeeDTO;
import com.example.demo.dto.UpdateEmployeeDTO;
import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.employeeRepository;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    private employeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(CreateEmployeeDTO dto) {

             Department department = departmentRepository.findById(dto.getDepartment_id())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartment_id()));
               
                Employee employee = new Employee();
                employee.setName(dto.getName());
                employee.setPosition(dto.getPosition());
                employee.setSalary(dto.getSalary());
                employee.setDepartment(department);
        
                return employeeRepository.save(employee);

    }

    @Override
    public Employee updateEmployee(UpdateEmployeeDTO dto) {

             Department department = departmentRepository.findById(dto.getDepartment_id())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartment_id()));
               
                Employee employee = new Employee();
                employee.setName(dto.getName());
                employee.setPosition(dto.getPosition());
                employee.setSalary(dto.getSalary());
                employee.setDepartment(department);
        
                return employeeRepository.save(employee);

    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }
    
    @Override
    public Page<Employee> getAllByPagination(int page, int size) {
   Pageable pageable = PageRequest.of(page, size);
    return employeeRepository.findAll(pageable);
    }

   
    
}
