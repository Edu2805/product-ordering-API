package com.springbootexpert.vendas.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerExceptionRuleBusiness(Exception exception){
        String message = exception.getMessage();
        return new ApiErros(message);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErros handlerPurchaseNotFoundExceptiom(PurchaseNotFoundException ex){
        return new ApiErros((ex.getMessage()));
    }
}
