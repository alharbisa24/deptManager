package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.models.Department;
import com.example.demo.services.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {
    
     private final DepartmentService departmentService;

     public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createDepartment(@Valid @RequestBody Department department, BindingResult result) {
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

        departmentService.createDepartment(department);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Employee created successfully",
            "data", department
    )); 

}

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDepartments(){
        List<Department> departments = departmentService.getAll();
       
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Departments returned successfully",
            "data", departments
    )); 
    }

@GetMapping("{id}")
public ResponseEntity<Map<String, Object>> getDepartmentById(@PathVariable("id") Long departmentId){
    Department department = departmentService.getDepartmentById(departmentId);

    return ResponseEntity.ok(Map.of(
        "status", "success",
        "message", "Department returned successfully",
        "data", department
)); 
}

    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        Department existingDepartment = departmentService.getDepartmentById(departmentId);
        existingDepartment.setName(department.getName());
        departmentService.updateDepartment(existingDepartment);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "department updated successfully",
            "data", department
    )); 
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "department deleted successfully",
            "data", departmentId
    )); 
    }

    @GetMapping("/pag")
    public Page<Department> getAllByPag(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        return departmentService.getAllByPagination(page, size);
    }

    @GetMapping("{id}/employees")
    public ResponseEntity<Map<String, Object>> getEmployeesByDepartmentId(@PathVariable("id") Long departmentId){
        Department department = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "department employees returned successfully",
            "data", department.getEmployees()
    )); 
    }
}
