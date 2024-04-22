package com.learnspring.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
public class Product {
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
