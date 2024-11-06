package com.Task.MiniProject.Service;

import com.Task.MiniProject.Exception.EmailAlreadyExistException;
import com.Task.MiniProject.Exception.IdAlreadyExistException;
import com.Task.MiniProject.Repository.EmployeeRepository;
import com.Task.MiniProject.Dto.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmoployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeDetails() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeDetailsById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployeeDetails(Employee employee) {
        if(employeeRepository.existsById(employee.getEmployeeId())){
            throw new IdAlreadyExistException("Id already exists: "+employee.getEmployeeId()+"  try another!");
        }
        if (employeeRepository.existsByEmployeeEmail(employee.getEmployeeEmail())) {
            throw new EmailAlreadyExistException("Email already exists: " + employee.getEmployeeEmail()+"  try another!");
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeDetails(Employee employee, Long id) {
        // Check if email exists with another employee's ID
        Optional<Employee> existingEmployeeWithEmail = employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail());
        if (existingEmployeeWithEmail.isPresent() && !existingEmployeeWithEmail.get().getEmployeeId().equals(id)) {
            throw new EmailAlreadyExistException("Email already exists for another employee.");
        }

        // Proceed with update logic
        Employee existingEmployee = employeeRepository.findById(id).get();
        existingEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        existingEmployee.setEmployeeLocation(employee.getEmployeeLocation());
        existingEmployee.setEmployeeFirstName(employee.getEmployeeFirstName());
        existingEmployee.setEmployeeLastName(employee.getEmployeeLastName());

        return employeeRepository.save(existingEmployee);
    }


    public void deleteEmployeeDetails(Long id) {
         employeeRepository.deleteById(id);

    }
}
