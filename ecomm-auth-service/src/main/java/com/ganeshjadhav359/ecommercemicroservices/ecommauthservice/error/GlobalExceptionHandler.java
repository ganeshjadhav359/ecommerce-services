package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiResponse apiResponse = new ApiResponse(ex.getLocalizedMessage(),errors,HttpStatus.BAD_REQUEST,new Date().toString());
               // new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiResponse, headers, apiResponse.getStatus(), request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        System.out.println(ex.getClass().getName());

        //
        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final ApiResponse apiResponse = new ApiResponse(ex.getMessage(),errors,HttpStatus.BAD_REQUEST,new Date().toString());
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request ";
        List<String> errors = new ArrayList<>(Collections.singletonList(error));
        final ApiResponse apiResponse = new ApiResponse(ex.getMessage(),errors,HttpStatus.BAD_REQUEST,new Date().toString());
        return new ResponseEntity<>(apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(final RuntimeException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        String error = "user does not exist with the provided username, please verify the username";
        List<String> errors = new ArrayList<>(Collections.singletonList(error));
        final ApiResponse apiResponse = new ApiResponse(ex.getMessage(),errors,HttpStatus.NOT_FOUND,new Date().toString());
        return handleExceptionInternal(
                ex, apiResponse, new HttpHeaders(), apiResponse.getStatus(), request);
    }
    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity<Object> handleRoleNotFound(final RuntimeException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        String error = "Role could not be found for the provided id";
        List<String> errors = new ArrayList<>(Collections.singletonList(error));
        final ApiResponse apiResponse = new ApiResponse(ex.getMessage(),errors,HttpStatus.NOT_FOUND,new Date().toString());
        return handleExceptionInternal(
                ex, apiResponse, new HttpHeaders(), apiResponse.getStatus(), request);
    }

    // 409
    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExist(final RuntimeException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        String error = "user already exists with the provided username, please try different username";
        List<String> errors = new ArrayList<>(Collections.singletonList(error));
        final ApiResponse apiResponse = new ApiResponse(ex.getMessage(),errors,HttpStatus.CONFLICT,new Date().toString());
        return handleExceptionInternal(
                ex, apiResponse, new HttpHeaders(), apiResponse.getStatus(), request);
    }
    
}
