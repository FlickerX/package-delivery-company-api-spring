package com.packagedeliverycompanyspringboot.errors;

public class TruckNotFound extends RuntimeException
{
    public TruckNotFound(Integer id){
        super("Unable to find Truck  "+ id);
    }
}
