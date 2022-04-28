package com.dronesystem.dronex.controllers;

import com.dronesystem.dronex.exceptions.BadRequestException;
import com.dronesystem.dronex.exceptions.CustomException;
import com.dronesystem.dronex.responses.CustomResponse;
import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintValidation(ConstraintViolationException exception,
            WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail(request.getDescription(false)).withCode("502").withMessage(exception.getMessage())
                .withTimestamp(new Date()).withStatus(HttpStatus.BAD_REQUEST).withError("Validation Error").build();
        return new ResponseEntity<>(response, response.getStatus());
    }
    
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException customException) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>().withDetail("")
                .withMessage(customException.getMessage()).withTimestamp(new Date())
                .withStatus(customException.getStatus()).withError("Custom Error").build();
        return new ResponseEntity<>(response, response.getStatus());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleCustomException(Exception exception, WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail(request.getDescription(false)).withCode("500")
                .withMessage(exception.getMessage()).withTimestamp(new Date())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR).withError(exception.getLocalizedMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> catchEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
    
    
   
    @ExceptionHandler(RestClientException.class)
    public final ResponseEntity<Object> handleRestClientExceptions(RestClientException ex, WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail(request.getDescription(false)).withMessage(ex.getMessage()).withTimestamp(new Date())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR).withError("REST CLIENT EXCEPTION").build();
        return buildResponseEntity(response);
    }
    
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail(request.getDescription(false)).withMessage(ex.getMessage()).withTimestamp(new Date())
                .withStatus(HttpStatus.BAD_REQUEST).withError("BAD REQUEST").build();
        return buildResponseEntity(response);
        
    }
    
    
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail(exception.getBindingResult().getAllErrors().toString())
                .withMessage(exception.getLocalizedMessage()).withTimestamp(new Date())
                .withStatus(HttpStatus.BAD_REQUEST).withError("Validation Error").build();
        return buildResponseEntity(response);
    }
    
    
    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomResponse<?> response = new CustomResponse.CustomResponseBuilder<>()
                .withDetail("Put in the correct request type").withMessage("Invalid request method")
                .withTimestamp(new Date()).withStatus(HttpStatus.METHOD_NOT_ALLOWED).withError("Invalid request method")
                .build();
        return buildResponseEntity(response);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(formatUnsupportedMediaTypeMessage(exception), headers, status);
    }
    
    private static String formatUnsupportedMediaTypeMessage(HttpMediaTypeNotSupportedException exception) {
        return String.format("Unsupported content type: %s. Supported content types: %s", exception.getContentType(),
                MediaType.toString(exception.getSupportedMediaTypes()));
    }
    
    
    private ResponseEntity<Object> buildResponseEntity(CustomResponse<?> response) {
        return new ResponseEntity<>(response, response.getStatus());
    }
}
