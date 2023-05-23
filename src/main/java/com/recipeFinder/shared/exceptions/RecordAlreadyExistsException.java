package com.recipeFinder.shared.exceptions;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}