package com.example.servlet_jsp.model;

public class IssuedBook {
    private int id;
    private int bookId;
    private int studentId;

    // Constructor
    public IssuedBook() {
    }

    public IssuedBook(int id, int bookId, int studentId) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
