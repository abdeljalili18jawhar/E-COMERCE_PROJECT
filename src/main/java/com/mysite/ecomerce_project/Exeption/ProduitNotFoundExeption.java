package com.mysite.ecomerce_project.Exeption;

public class ProduitNotFoundExeption extends Exception{
    public ProduitNotFoundExeption(String  msg){
        super(msg);
    }
}
