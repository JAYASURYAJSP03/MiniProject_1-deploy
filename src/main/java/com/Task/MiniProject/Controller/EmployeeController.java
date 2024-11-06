package com.Task.MiniProject.Controller;

import com.Task.MiniProject.Dto.Employee;
import com.Task.MiniProject.Exception.EmailAlreadyExistException;
import com.Task.MiniProject.Exception.IdAlreadyExistException;
import com.Task.MiniProject.Service.EmoployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmoployeeService emoployeeService;
    //    private EmployeeRepository employeeRepository;
    @GetMapping("/homePage")
    public String  getAllEmployeeDetails(Model model){
        model.addAttribute("allEmpList",emoployeeService.getAllEmployeeDetails());
        return "index";
    }


//    @GetMapping("getEmployeeDetailsById/{id}")
//    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable Long id){
//        return new ResponseEntity<>(emoployeeService.getEmployeeDetailsById(id),HttpStatus.OK);
//    }

    @GetMapping("/addNew")
    public String  addNewEmployee(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "newEmployee";
    }
    @PostMapping("/save")
    public String addEmployeeDetails(@Valid @ModelAttribute("employee") Employee employeeDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newEmployee";
        }
        try {
            emoployeeService.addEmployeeDetails(employeeDto);
        } catch (EmailAlreadyExistException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "newEmployee";
        }
        catch (IdAlreadyExistException ex) {
            model.addAttribute("errorIdMessage", ex.getMessage());
            return "newEmployee";
        }
        return "redirect:/homePage";
    }




    @PostMapping("/updateEmployeeDetails/{id}")
    public String updateEmployeeDetails(@ModelAttribute("employee") @Valid Employee employee,
                                        BindingResult result,
                                        @PathVariable long id,
                                        Model model) {
        if (result.hasErrors()) {
            return "update"; // Return to update page if validation errors occur
        }

        try {
            emoployeeService.updateEmployeeDetails(employee, id);
        } catch (EmailAlreadyExistException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "update";
        }

        return "redirect:/homePage";
    }


    @GetMapping("/employeeDetails/{id}")
    public String  getEmployeeDetails(@PathVariable long id,Model model){
        Employee employee=emoployeeService.getEmployeeDetailsById(id);
        model.addAttribute("employee",employee);
        return "empDetails";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String  addNewEmployee(@PathVariable long id, Model model){
        Employee employee=emoployeeService.getEmployeeDetailsById(id);
        model.addAttribute("employee",employee);
        return "update";
    }


    @GetMapping("deleteEmployee/{id}")
    public String  deleteEmployeeDetails(@PathVariable Long  id){
        emoployeeService.deleteEmployeeDetails(id);
//        employeeRepository.deleteById(id);
        return "redirect:/homePage";
    }

}
