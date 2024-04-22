package com.learnspring.employee.services.impls;

import com.learnspring.employee.entity.Employee;
import com.learnspring.employee.exception.EmployeeException;
import com.learnspring.employee.repository.EmployeeRepository;
import com.learnspring.employee.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmp() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee e;
        if(emp.isPresent()) {
            e = emp.get();
        }
        else {
            CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> {
                return "hellow";
            });
            futureResult.thenApply(greeting -> greeting+" World")
                    .thenAccept(System.out::println);


            throw new EmployeeException("Employee not present: " +id);
        }
        return e;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByEntityName(name);
    }

}
