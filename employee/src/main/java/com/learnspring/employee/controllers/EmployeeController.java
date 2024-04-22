package com.learnspring.employee.controllers;

import com.learnspring.employee.entity.Employee;
import com.learnspring.employee.exception.EmployeeException;
import com.learnspring.employee.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learnspring")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmp();
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployeeByID(@PathVariable int id) {
        Employee e = employeeService.findById(id);
        if(e == null) {
            throw new EmployeeException("Employee not found: " +id);
        }
        return e;
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee emp) {
         return employeeService.save(emp);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee emp = employeeService.findById(id);
        if(emp == null) {
            throw new EmployeeException("Employee not found: " +id);
        }
         employeeService.deleteById(id);
    }
}
