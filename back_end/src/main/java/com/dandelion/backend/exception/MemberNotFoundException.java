package com.dandelion.backend.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String id){
        super(id + " NotFoundException");
    }

}
