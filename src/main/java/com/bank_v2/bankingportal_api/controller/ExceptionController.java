package com.bank_v2.bankingportal_api.controller;

import com.bank_v2.bankingportal_api.exception.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController implements ErrorController {
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument(Exception e) {
        e.printStackTrace();
        return "illegal-argument";
    }
    @ExceptionHandler(MethodNotAllowedException.class)
    protected String handleMethodNotAllowed(MethodNotAllowedException ex) {
        ex.printStackTrace();
        return "illegal-argument";
    }
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(Exception e) {
        e.printStackTrace();
        return "illegal-argument";
    }
}
