package com.thoughtworks.springbootemployee.handler;

import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({IllegalParameterException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String handleIllegalParameterException(Exception exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({OperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleOperationException(Exception exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConstraintViolationException(ConstraintViolationException exception) {
        return "Insert data exception,please check your post data!";
    }

}
