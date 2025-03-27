package com.library.exception;

/**
 * Thrown when book data is invalid.
 */
public class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}