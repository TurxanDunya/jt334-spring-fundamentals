package com.texnoera.cardapi.error.exceptions;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String message) {
        super(message);
    }

}
