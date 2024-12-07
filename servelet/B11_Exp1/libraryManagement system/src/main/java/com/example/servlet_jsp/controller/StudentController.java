package com.example.servlet_jsp.controller;

import com.example.servlet_jsp.model.Student;
import com.example.servlet_jsp.repo.StudentRepo;

import java.sql.SQLException;
import java.util.List;

public class StudentController {
    private StudentRepo studentRepo = new StudentRepo();

    public void addStudent(String name, String email) throws SQLException {
        Student student = new Student(0, name, email, 0);
        studentRepo.addStudent(student);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepo.getAllStudents();
    }

    public void updateIssuedBooks(int studentId, int issuedBooks) throws SQLException {
        studentRepo.updateIssuedBooks(studentId, issuedBooks);
    }
}
