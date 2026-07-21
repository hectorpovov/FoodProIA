package com.foodProIA.FoodProIA.exception;

public class RazaoSocialJaCadastradaException extends RuntimeException{
    public RazaoSocialJaCadastradaException(){
        super("Já existe uma empresa cadastrada com essa razão social!");
    }
}
