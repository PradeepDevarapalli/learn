package com.learnspring.employee.exception;

public class EmployeeErrorResponse {
    private int statusCode;
    private String message;
    private long time;

    public EmployeeErrorResponse() {
    }

    public EmployeeErrorResponse(int statusCode, String message, long time) {
        this.statusCode = statusCode;
        this.message = message;
        this.time = time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
