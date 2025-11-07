package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateEmployeeDTO;
import com.example.demo.dto.UpdateEmployeeDTO;
import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class employeeRestController {
    

     private final EmployeeService employeeService;
     private final DepartmentService departmentService;
     


    public employeeRestController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees(Model model){

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Employees returned successfully",
            "data", this.employeeService.getAll()
    )); 

    }

@GetMapping("{id}")
public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable("id") Long employeeId){
    Employee employee = employeeService.getEmployeeById(employeeId);

    return ResponseEntity.ok(Map.of(
        "status", "success",
        "message", "Employee returned successfully",
        "data", employee
)); 
}


@PostMapping
public ResponseEntity<Map<String, Object>> createEmployee(@Valid @RequestBody CreateEmployeeDTO dto, BindingResult result) {
    if (result.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "status", "error",
                        "message", "Validation failed",
                        "errors", errors
                ));
    }

    employeeService.saveEmployee(dto);
    
    return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Employee created successfully",
            "data", dto
    ));
}


    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(Long employeeId, @Valid UpdateEmployeeDTO dto, BindingResult result){
        
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", "error",
                            "message", "Validation failed",
                            "errors", errors
                    ));
        }
        
        Department department = departmentService.getDepartmentById(dto.getDepartment_id());

        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        existingEmployee.setName(dto.getName());
        existingEmployee.setPosition(dto.getPosition());
        existingEmployee.setSalary(dto.getSalary());
        existingEmployee.setDepartment(department);
        employeeService.updateEmployee(dto);


        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Employee updated successfully",
            "data", existingEmployee
    )); 

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Employee deleted successfully",
            "data", employeeId
    )); 
    }


    @GetMapping("/pag")
    public Page<Employee> getAllByPag(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        return employeeService.getAllByPagination(page, size);
    }


}
