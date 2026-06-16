package com.restaurant;

// Excepción personalizada para indicar que un producto no está en stock.
public class SenseEstocException extends RuntimeException {

    // Constructor que recibe un mensaje de error y lo pasa a la clase base RuntimeException.
    public SenseEstocException(String message) {
        super(message);
    }
}