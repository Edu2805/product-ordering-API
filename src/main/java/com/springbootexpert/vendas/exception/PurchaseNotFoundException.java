package com.springbootexpert.vendas.exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException() {
        super("Pedido não encontrado");
    }
}
