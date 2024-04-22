package com.learnspring.employee.exception.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventErrorResponse {

    private int statusCode;
    private String message;
    private long time;
}
