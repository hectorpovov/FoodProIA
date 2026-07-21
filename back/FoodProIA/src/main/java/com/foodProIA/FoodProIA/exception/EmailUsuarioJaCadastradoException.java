package com.foodProIA.FoodProIA.exception;

public class EmailUsuarioJaCadastradoException extends RuntimeException{

    public EmailUsuarioJaCadastradoException(){
        super("Já existe um usuário com esse email");
    }

}
