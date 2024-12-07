package com.example.servlet_jsp.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private int issuedBooks;

    // Constructors
    public Student() {
    }

    public Student(int id, String name, String email, int issuedBooks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.issuedBooks = issuedBooks;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(int issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

}
