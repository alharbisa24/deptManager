package com.example.demo.dto;
import com.example.demo.models.Position;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmployeeDTO {
    
    @NotBlank(message = "Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Position is required")
    private Position position;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be at least 1000")
    private int salary;

    @NotNull(message = "Department is required")
    public Long department_id;

}
