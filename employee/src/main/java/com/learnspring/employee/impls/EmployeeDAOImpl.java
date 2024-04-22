package com.learnspring.employee.impls;

import com.learnspring.employee.entity.Employee;
import com.learnspring.employee.entity.interfaces.EmployeeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> findAllEmployees = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = findAllEmployees.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee emp = entityManager.merge(employee);
        return emp;
    }


    public void example() {
        List<String> example = new ArrayList<>();
        List<Integer> intNumbers = new ArrayList<>();
        intNumbers.add(123);
        intNumbers.add(167);
        intNumbers.add(984);
        intNumbers.add(111);
        intNumbers.add(98);

       int result =  intNumbers.stream()
                        .sorted((a,b) -> Integer.compare(b,a))
                                .distinct()
                                        .skip(1)
                                                .findFirst()
                                                        .orElseThrow(()-> new IllegalArgumentException("Invalid data"));


        example.add("Mittu");
        example.add("Buddi");

        example.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        Employee emp = entityManager.find(Employee.class, id);
        entityManager.remove(emp);
    }

    public List<Employee> findByEntityName(@Param("name") String name) {
        TypedQuery<Employee> q = entityManager.createQuery("select  e from Employee  e where e.firstName=:name", Employee.class);
        q.setParameter("name", name);

        return q.getResultList();
    }
}
