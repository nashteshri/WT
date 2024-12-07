package com.example.servlet_jsp.controller;

import com.example.servlet_jsp.model.Book;
import com.example.servlet_jsp.repo.BookRepo;

import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookRepo bookRepo = new BookRepo();

    public void addBook(String title, String author, String isbn) throws SQLException {
        Book book = new Book(0, title, author, isbn);
        bookRepo.addBook(book);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookRepo.getAllBooks();
    }
}
