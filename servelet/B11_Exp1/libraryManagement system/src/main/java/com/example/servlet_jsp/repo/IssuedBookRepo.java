package com.example.servlet_jsp.repo;

import com.example.servlet_jsp.model.IssuedBook;
import com.example.servlet_jsp.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssuedBookRepo {
    public void issueBook(IssuedBook issuedBook) throws SQLException {
        String sql = "INSERT INTO issuedBooks (bookId, studentId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issuedBook.getBookId());
            pstmt.setInt(2, issuedBook.getStudentId());
            pstmt.executeUpdate();
        }
    }

    public List<IssuedBook> getIssuedBooks() throws SQLException {
        List<IssuedBook> issuedBooks = new ArrayList<>();
        String sql = "SELECT * FROM issuedBooks";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                issuedBooks.add(new IssuedBook(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }
        }
        return issuedBooks;
    }
}

