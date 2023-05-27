package com.flavorfinder.shared.exceptions;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}