package com.learnspring.employee.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryDAO {

    @Before("execution(public * findAll())")
    public void beforeAdvice() {
        System.out.println("Before running method: ");
    }

}
