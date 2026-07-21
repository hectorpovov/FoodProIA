package com.foodProIA.FoodProIA.exception;

public class EmpresaNaoEncontradaException extends RuntimeException{
    public EmpresaNaoEncontradaException(){
        super("Empresa não encontrada!");
    }
}
