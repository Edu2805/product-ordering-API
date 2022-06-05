package com.springbootexpert.vendas.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErros {

    private List<String> errors;

    public ApiErros(List<String> errors) {
        this.errors = errors;
    }

    public ApiErros(String messageError){
        this.errors = Arrays.asList(messageError);
    }
}
