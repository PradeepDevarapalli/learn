package com.learnspring.employee.repository;

import com.learnspring.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select  e from Employee  e where e.firstName=:name")
    public List<Employee> findByEntityName(String name);
}
