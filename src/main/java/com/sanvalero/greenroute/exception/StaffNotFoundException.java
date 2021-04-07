package com.sanvalero.greenroute.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */
public class StaffNotFoundException extends RuntimeException {

    public StaffNotFoundException() {
        super();
    }

    public StaffNotFoundException(String message) {
        super(message);
    }

    public StaffNotFoundException(long id){
        super("Staff not found: " + id);
    }
}
