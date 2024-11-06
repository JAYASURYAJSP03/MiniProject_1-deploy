package com.Task.MiniProject.Repository;

import com.Task.MiniProject.Dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmployeeEmail(String email);

    Optional<Employee> findByEmployeeEmail(String employeeEmail);

}
