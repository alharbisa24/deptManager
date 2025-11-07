package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;

@Service
public interface DepartmentService {

    public List<Department> getAll();
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    Department getDepartmentById(Long id);
    void deleteDepartmentById(Long id);
    public Page<Department> getAllByPagination(int page, int size);

}
