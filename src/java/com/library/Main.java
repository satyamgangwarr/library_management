package com.library;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class with console interface.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryService libraryService = new LibraryService();

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> viewAllBooks();
                    case 3 -> searchBook();
                    case 4 -> updateBook();
                    case 5 -> deleteBook();
                    case 6 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InvalidBookException | BookNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        System.out.println("Exiting Library Management System. Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add a Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book Details");
        System.out.println("5. Delete a Book");
        System.out.println("6. Exit");
    }

    private static void addBook() throws InvalidBookException {
        System.out.println("\nAdd a New Book");
        String id = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Title: ");
        String author = getStringInput("Enter Author: ");
        String genre = getStringInput("Enter Genre: ");
        String status = getStringInput("Enter Availability Status (Available/Checked Out): ");
        
        libraryService.addBook(id, title, author, genre, status);
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        System.out.println("\nAll Books in Library");
        List<Book> books = libraryService.getAllBooks();
        
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBook() {
        System.out.println("\nSearch Book");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        int searchChoice = getIntInput("Enter search option: ");
        
        switch (searchChoice) {
            case 1 -> {
                String id = getStringInput("Enter Book ID: ");
                Book book = libraryService.getBookById(id);
                if (book != null) {
                    System.out.println("Book found: " + book);
                } else {
                    System.out.println("No book found with ID: " + id);
                }
            }
            case 2 -> {
                String title = getStringInput("Enter Title (or part of title): ");
                List<Book> books = libraryService.searchBooksByTitle(title);
                if (books.isEmpty()) {
                    System.out.println("No books found matching: " + title);
                } else {
                    System.out.println("Found " + books.size() + " book(s):");
                    books.forEach(System.out::println);
                }
            }
            default -> System.out.println("Invalid search option.");
        }
    }

    private static void updateBook() throws BookNotFoundException, InvalidBookException {
        System.out.println("\nUpdate Book Details");
        String id = getStringInput("Enter Book ID to update: ");
        
        Book currentBook = libraryService.getBookById(id);
        if (currentBook == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        
        System.out.println("Current details: " + currentBook);
        System.out.println("Enter new details (leave blank to keep current value):");
        
        String newTitle = getStringInput("New Title [" + currentBook.getTitle() + "]: ");
        String newAuthor = getStringInput("New Author [" + currentBook.getAuthor() + "]: ");
        String newGenre = getStringInput("New Genre [" + currentBook.getGenre() + "]: ");
        String newStatus = getStringInput("New Status [" + currentBook.getAvailabilityStatus() + "]: ");
        
        // Use current values if new ones are blank
        libraryService.updateBook(
            id,
            newTitle.isEmpty() ? currentBook.getTitle() : newTitle,
            newAuthor.isEmpty() ? currentBook.getAuthor() : newAuthor,
            newGenre.isEmpty() ? currentBook.getGenre() : newGenre,
            newStatus.isEmpty() ? currentBook.getAvailabilityStatus() : newStatus
        );
        
        System.out.println("Book updated successfully!");
    }

    private static void deleteBook() throws BookNotFoundException {
        System.out.println("\nDelete a Book");
        String id = getStringInput("Enter Book ID to delete: ");
        
        libraryService.deleteBook(id);
        System.out.println("Book deleted successfully!");
    }

    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}