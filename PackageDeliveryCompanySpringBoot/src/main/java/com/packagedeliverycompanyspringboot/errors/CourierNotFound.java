package com.packagedeliverycompanyspringboot.errors;

public class CourierNotFound extends RuntimeException{
    public CourierNotFound(Integer id){
        super("Unable to find Courier  "+ id);
    }
}
