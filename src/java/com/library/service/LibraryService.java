package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import com.library.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class containing all library operations.
 */
public class LibraryService {
    private final List<Book> books = new ArrayList<>();

    /**
     * Adds a new book to the library.
     */
    public void addBook(String id, String title, String author, String genre, String availabilityStatus) 
            throws InvalidBookException {
        ValidationUtil.validateBookData(id, title, author, availabilityStatus);
        
        if (getBookById(id) != null) {
            throw new InvalidBookException("Book with ID " + id + " already exists");
        }
        
        books.add(new Book(id, title, author, genre, availabilityStatus));
    }

    /**
     * Retrieves all books in the library.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Searches for a book by ID.
     */
    public Book getBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for books by title (case-insensitive partial match).
     */
    public List<Book> searchBooksByTitle(String title) {
        String searchTerm = title.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Updates book details.
     */
    public void updateBook(String id, String newTitle, String newAuthor, String newGenre, String newStatus) 
            throws BookNotFoundException, InvalidBookException {
        Book bookToUpdate = getBookById(id);
        if (bookToUpdate == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        
        ValidationUtil.validateBookData(id, newTitle, newAuthor, newStatus);
        
        bookToUpdate.setTitle(newTitle);
        bookToUpdate.setAuthor(newAuthor);
        bookToUpdate.setGenre(newGenre);
        bookToUpdate.setAvailabilityStatus(newStatus);
    }

    /**
     * Deletes a book from the library.
     */
    public void deleteBook(String id) throws BookNotFoundException {
        Book bookToRemove = getBookById(id);
        if (bookToRemove == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        books.remove(bookToRemove);
    }
}