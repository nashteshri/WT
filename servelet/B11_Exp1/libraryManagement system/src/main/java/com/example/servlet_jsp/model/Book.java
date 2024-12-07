package com.example.servlet_jsp.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String assNo;

    // Constructor
    public Book() {
    }

    public Book(int id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.assNo = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return assNo;
    }

    public void setIsbn(String isbn) {
        this.assNo = isbn;
    }
}