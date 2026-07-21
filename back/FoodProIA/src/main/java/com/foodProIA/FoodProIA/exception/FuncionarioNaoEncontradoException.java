package com.foodProIA.FoodProIA.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException{
    public FuncionarioNaoEncontradoException(){
        super("Não foi possível encontrar esse funcionario");
    }
}
