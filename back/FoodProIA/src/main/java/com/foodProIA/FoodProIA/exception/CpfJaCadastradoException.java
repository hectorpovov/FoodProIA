package com.foodProIA.FoodProIA.exception;

public class CpfJaCadastradoException extends RuntimeException{


    public CpfJaCadastradoException(){
        super("Já existe um usuário com esse CPF");
    }
}
