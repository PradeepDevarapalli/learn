package com.learnspring.employee.services.interfaces;

import com.learnspring.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmp();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);

    List<Employee> findByName(String name);

}
