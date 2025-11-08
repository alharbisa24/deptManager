package com.example.demo.dto;
import com.example.demo.models.Position;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateEmployeeDTO {
    
    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Min(value = 1000, message = "Salary must be at least 1000")
    private int salary;

    public Long department_id;

}
