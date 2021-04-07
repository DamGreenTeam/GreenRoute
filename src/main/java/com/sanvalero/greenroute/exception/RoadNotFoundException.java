package com.sanvalero.greenroute.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */
public class RoadNotFoundException extends RuntimeException {

    public RoadNotFoundException() {
        super();
    }

    public RoadNotFoundException(String message) {
        super(message);
    }

    public RoadNotFoundException(long id){
        super("Road not found: " + id);
    }
}
