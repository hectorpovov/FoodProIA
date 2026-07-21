package com.foodProIA.FoodProIA.exception;

public class CnpjJaCadastradoException extends RuntimeException{
    public CnpjJaCadastradoException(){
        super("Já existe uma empresa cadastrada com esse CNPJ!");
    }
}
