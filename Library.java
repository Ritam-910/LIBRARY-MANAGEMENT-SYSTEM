

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a user to the library
    public void addUser(User user) {
        users.add(user);
    }

    // Issue a book to a user
    public void issueBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book != null && user != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book issued successfully to " + user.getName());
        } else {
            System.out.println("Book or user not found, or book is not available.");
        }
    }

    // Return a book
    public void returnBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or already available.");
        }
    }

    // Helper method to find a book by ID
    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    // Helper method to find a user by ID
    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // Display all books
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Display all users
    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}