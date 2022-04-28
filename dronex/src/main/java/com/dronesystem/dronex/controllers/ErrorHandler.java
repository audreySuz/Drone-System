package com.dronesystem.dronex.controllers;

import com.dronesystem.dronex.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sabi Employee
 */
@ControllerAdvice
public class ErrorHandler {
     Logger log = LoggerFactory.getLogger(ErrorHandler.class);
   
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e){
            ErrorResponse errorResponse =new ErrorResponse();
            errorResponse.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    }
   
