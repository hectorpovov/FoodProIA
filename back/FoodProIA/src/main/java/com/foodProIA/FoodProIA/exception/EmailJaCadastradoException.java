package com.foodProIA.FoodProIA.exception;

public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(){
        super("Já existe um usuário com esse email");
    }

}
