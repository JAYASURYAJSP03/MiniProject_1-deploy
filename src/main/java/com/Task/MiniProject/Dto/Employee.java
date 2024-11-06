package com.Task.MiniProject.Dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee_details")
public class Employee {
    @Id
    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    private String employeeFirstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String employeeLastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String employeeEmail;

    @NotBlank(message = "Location is required")
    private String employeeLocation;

    public Employee(String employeeFirstName, String employeeLastName, String employeeLocation, String employeeEmail) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeLocation = employeeLocation;
        this.employeeEmail = employeeEmail;
    }
}
