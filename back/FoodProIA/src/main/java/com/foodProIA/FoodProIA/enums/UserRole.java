package com.foodProIA.FoodProIA.enums;

public enum UserRole {
    ADMIN("admin"),
    GESTOR("gestor"),
    OPERADOR("operador");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
