package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "salary")
    private int salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    public Department department;


}
