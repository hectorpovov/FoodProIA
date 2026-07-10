package com.foodProIA.FoodProIA.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
