package com.sanvalero.greenroute.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */
public class MaintenanceNotFoundException extends RuntimeException {

    public MaintenanceNotFoundException() {
        super();
    }

    public MaintenanceNotFoundException(String message){
        super(message);
    }

    public MaintenanceNotFoundException(long id){
        super("Maintenance not found: " + id);
    }
}
