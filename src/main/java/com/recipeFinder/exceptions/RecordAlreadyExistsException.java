package com.recipeFinder.exceptions;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}