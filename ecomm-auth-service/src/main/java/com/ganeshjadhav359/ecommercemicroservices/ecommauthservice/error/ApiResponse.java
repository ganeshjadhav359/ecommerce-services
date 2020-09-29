package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiResponse {
    
    private String message;
    private List<String> errors;
    private HttpStatus status;
    private String timestamp;

    public ApiResponse(String message, List<String> errors, HttpStatus status, String timestamp) {
        this.message = message;
        this.errors = errors;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
