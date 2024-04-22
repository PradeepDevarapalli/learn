package com.learnspring.employee.exception;

import com.learnspring.employee.exception.kafka.EventErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleEmpException(EmployeeException exe) {
        EmployeeErrorResponse empexe = new EmployeeErrorResponse();

        empexe.setStatusCode(HttpStatus.NOT_FOUND.value());
        empexe.setMessage(exe.getMessage());
        empexe.setTime(System.currentTimeMillis());

        //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hi");
        return new ResponseEntity<>(empexe, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleEmpException(Exception exe) {
        EventErrorResponse empexe = new EventErrorResponse();

        empexe.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        empexe.setMessage(exe.getMessage());
        empexe.setTime(System.currentTimeMillis());

        return new ResponseEntity<>(empexe, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exe) {
        EmployeeErrorResponse empexe = new EmployeeErrorResponse();

        empexe.setStatusCode(HttpStatus.BAD_REQUEST.value());
        empexe.setMessage(exe.getMessage());
        empexe.setTime(System.currentTimeMillis());

        return new ResponseEntity<>(empexe, HttpStatus.BAD_REQUEST);
    }
}
