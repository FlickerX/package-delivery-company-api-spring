package com.packagedeliverycompanyspringboot.errors;

public class CargoNotFound extends RuntimeException{
    public CargoNotFound(Integer id){
        super("Unable to find Cargo  "+ id);
    }
}
