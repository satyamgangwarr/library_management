package com.library.exception;

/**
 * Thrown when a requested book is not found in the library.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}