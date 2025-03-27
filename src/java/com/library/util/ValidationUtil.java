package com.library.util;

import com.library.exception.InvalidBookException;

/**
 * Utility class for validating book data.
 */
public class ValidationUtil {

    /**
     * Validates book data before adding or updating.
     */
    public static void validateBookData(String id, String title, String author, String status) 
            throws InvalidBookException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidBookException("Book ID cannot be empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidBookException("Title cannot be empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new InvalidBookException("Author cannot be empty");
        }
        if (!"Available".equalsIgnoreCase(status) && !"Checked Out".equalsIgnoreCase(status)) {
            throw new InvalidBookException("Status must be either 'Available' or 'Checked Out'");
        }
    }
}