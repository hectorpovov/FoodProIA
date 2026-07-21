package com.foodProIA.FoodProIA.exception;

public class SetorNaoEncontradoException extends RuntimeException{
    public SetorNaoEncontradoException(){
        super("Nao foi possivel encontrar esse setor");
    }
}
