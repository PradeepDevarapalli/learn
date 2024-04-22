package com.learnspring.employee.kafka.kafkaevent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductCreatedEvent {
    private String productID;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String productID, String title, BigDecimal price, Integer quantity) {
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
}
