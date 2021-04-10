package com.sanvalero.greenroute.exception;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
public class SectionNotFoundException extends RuntimeException{

    public SectionNotFoundException() {
        super();
    }

    public SectionNotFoundException(String message) {
        super(message);
    }

    public SectionNotFoundException(long id){
        super("Section not found: " + id);
    }

}
