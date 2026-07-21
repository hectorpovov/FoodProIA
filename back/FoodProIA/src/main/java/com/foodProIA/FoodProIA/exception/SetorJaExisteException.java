package com.foodProIA.FoodProIA.exception;

public class SetorJaExisteException extends RuntimeException{

    public SetorJaExisteException(){
        super("Essa empresa ja tem um setor com esse nome!");
    }

}
