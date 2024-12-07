package com.gmail.samirshende420.LibrarayManagementSystemKitcoek.DB;

import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Book;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.IssueBook;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Librarian;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Student;

import java.util.ArrayList;
import java.util.List;

public class LibraryDB {

    public static List<Book> bookList = new ArrayList<>();

    public static List<Student> studentList = new ArrayList<>();

    public static List<Librarian> librarianList = new ArrayList<>();

    public static List<IssueBook> issuedBooks = new ArrayList<>();
}
