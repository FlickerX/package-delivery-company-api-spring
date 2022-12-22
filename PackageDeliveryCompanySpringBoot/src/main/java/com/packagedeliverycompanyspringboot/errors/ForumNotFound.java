package com.packagedeliverycompanyspringboot.errors;

public class ForumNotFound extends RuntimeException{
    public ForumNotFound(Integer id){
        super("Unable to find Forum  "+ id);
    }
}
