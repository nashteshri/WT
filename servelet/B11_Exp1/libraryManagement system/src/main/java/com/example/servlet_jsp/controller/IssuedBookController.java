package com.example.servlet_jsp.controller;

import com.example.servlet_jsp.model.Book;
import com.example.servlet_jsp.model.IssuedBook;
import com.example.servlet_jsp.model.Student;
import com.example.servlet_jsp.repo.BookRepo;
import com.example.servlet_jsp.repo.IssuedBookRepo;
import com.example.servlet_jsp.repo.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IssuedBookController {
    private IssuedBookRepo issuedBookRepo = new IssuedBookRepo();
    private BookRepo bookRepo = new BookRepo();
    private StudentRepo studentRepo = new StudentRepo();

    public void issueBook(int bookId, int studentId) throws SQLException {
        Student student = studentRepo.getStudentById(studentId);
        if (student.getIssuedBooks() >= 3) {
            throw new IllegalStateException("Student has already issued 3 books");
        }

        IssuedBook issuedBook = new IssuedBook(0, bookId, studentId);
        issuedBookRepo.issueBook(issuedBook);
        studentRepo.updateIssuedBooks(studentId, student.getIssuedBooks() + 1);
    }

    public List<IssuedBookWithDetails> getIssuedBooks() {
        try {
            List<IssuedBook> issuedBooks = issuedBookRepo.getIssuedBooks();
            List<IssuedBookWithDetails> result = new ArrayList<>();
            for (IssuedBook issuedBook : issuedBooks) {
                Book book = bookRepo.getBookById(issuedBook.getBookId());
                Student student = studentRepo.getStudentById(issuedBook.getStudentId());
                result.add(new IssuedBookWithDetails(issuedBook, book, student));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getIssuedBooks: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list instead of throwing an exception
        }
    }

    public static class IssuedBookWithDetails {
        private IssuedBook issuedBook;
        private Book book;
        private Student student;

        public IssuedBookWithDetails(IssuedBook issuedBook, Book book, Student student) {
            this.issuedBook = issuedBook;
            this.book = book;
            this.student = student;
        }

        public IssuedBook getIssuedBook() {
            return issuedBook;
        }

        public Book getBook() {
            return book;
        }

        public Student getStudent() {
            return student;
        }
    }
}
