package com.gmail.samirshende420.LibrarayManagementSystemKitcoek.controllers;

import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.DB.LibraryDB;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return LibraryDB.bookList;
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable String bookId) throws Exception {
        for (Book book : LibraryDB.bookList) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        throw new Exception("Book Not Found");
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) throws Exception {
        if (book.getBookId().isBlank() || book.getBookName().isBlank() || book.getAuthor().isBlank()) {
            throw new Exception("All fields (bookId, bookName, author) are required.");
        }
        LibraryDB.bookList.add(book);
        return book;
    }

    @PutMapping("/update/{bookId}")
    public Book updateBookDetails(@PathVariable String bookId, @RequestBody Book updatedBook) throws Exception {
        for (Book book : LibraryDB.bookList) {
            if (book.getBookId().equals(bookId)) {
                book.setBookName(updatedBook.getBookName());
                book.setAuthor(updatedBook.getAuthor());
                return book;
            }
        }
        throw new Exception("Book Not Found");
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId) throws Exception {
        Book deleteBook = null;
        for (Book book : LibraryDB.bookList) {
            if (book.getBookId().equals(bookId)) {
                deleteBook = book;
            }
        }
        if (deleteBook == null) {
            throw new Exception("Book Not Found");
        }
        LibraryDB.bookList.remove(deleteBook);
        return "Book Deleted Successfully";
    }
}
