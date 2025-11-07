package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Service
public class DepartmentServiceimpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Department> getAll() {
       return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        this.departmentRepository.save(department);
        return department;
    }

    @Override
    public Department updateDepartment(Department department) {
        Department existingDepartment = getDepartmentById(department.getId());
        existingDepartment.setName(department.getName());
        this.departmentRepository.save(existingDepartment);

        return existingDepartment;
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteDepartmentById(Long id) {
        this.departmentRepository.deleteById(id);

    }

    @Override
    public Page<Department> getAllByPagination(int page, int size) {
   Pageable pageable = PageRequest.of(page, size);
    return departmentRepository.findAll(pageable);
    }

   
    
}
