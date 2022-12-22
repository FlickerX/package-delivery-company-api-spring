package com.packagedeliverycompanyspringboot.errors;

public class ManagerNotFound extends RuntimeException{
    public ManagerNotFound(Integer id){
        super("Unable to find Manager "+ id);
    }
}
