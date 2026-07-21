package com.foodProIA.FoodProIA.exception;

public class EmailEmpresaJaCadastradoException extends RuntimeException{

    public EmailEmpresaJaCadastradoException(){
        super("Já existe uma empresa cadastrada com esse email!");
    }

}
