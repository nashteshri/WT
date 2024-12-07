package com.gmail.samirshende420.LibrarayManagementSystemKitcoek.controllers;

import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.DB.LibraryDB;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Book;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.IssueBook;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Librarian;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @GetMapping("/")
    public List<Librarian> getAllLibrarians() {
        return LibraryDB.librarianList;
    }

    @PostMapping("/add")
    public Librarian addLibrarian(@RequestBody Librarian librarian) throws Exception {
        if (librarian.getLibrarianId().isBlank() || librarian.getName().isBlank()) {
            throw new Exception("Librarian ID and Name are required.");
        }
        LibraryDB.librarianList.add(librarian);
        return librarian;
    }

    @PostMapping("/issueBook/{librarianId}/{prnNo}/{bookId}")
    public String issueBookToStudent(@PathVariable String librarianId, @PathVariable Long prnNo, @PathVariable String bookId) throws Exception {
        boolean librarianExists = LibraryDB.librarianList.stream()
                .anyMatch(librarian -> librarian.getLibrarianId().equals(librarianId));
        if (!librarianExists) {
            throw new Exception("Librarian Not Found with ID: " + librarianId);
        }

        Student student = null;
        for (Student s : LibraryDB.studentList) {
            if (s.getPrnNo().equals(prnNo)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            throw new Exception("Student Not Found with PRN: " + prnNo);
        }

        Book book = null;
        for (Book b : LibraryDB.bookList) {
            if (b.getBookId().equals(bookId)) {
                book = b;
                break;
            }
        }
        if (book == null) {
            throw new Exception("Book Not Found with ID: " + bookId);
        }


        for (IssueBook issueBook : LibraryDB.issuedBooks) {
            if (issueBook.getBookId().equals(bookId)) {
                throw new Exception("Book with ID " + bookId + " is already issued.");
            }
        }


        IssueBook newIssue = new IssueBook();
        newIssue.setBookId(bookId);
        newIssue.setStudentPrn(prnNo);

        LibraryDB.issuedBooks.add(newIssue);

        return "Book with ID "+bookId+" issued to student with PRN "+prnNo +" by librarian "+ librarianId;
    }

    @GetMapping("/issuedBooks")
    public List<IssueBook> getAllIssuedBooks() {
        return LibraryDB.issuedBooks;
    }

    @PostMapping("/returnBook/{librarianId}/{bookId}/{prnNo}")
    public String returnBook(@PathVariable String librarianId, @PathVariable String bookId, @PathVariable Long prnNo) throws Exception {

        boolean librarianExists = LibraryDB.librarianList.stream()
                .anyMatch(librarian -> librarian.getLibrarianId().equals(librarianId));
        if (!librarianExists) {
            throw new Exception("Librarian Not Found with ID: " + librarianId);
        }


        IssueBook issueToRemove = null;
        for (IssueBook issueBook : LibraryDB.issuedBooks) {
            if (issueBook.getBookId().equals(bookId) && issueBook.getStudentPrn().equals(prnNo)) {
                issueToRemove = issueBook;
                break;
            }
        }

        if (issueToRemove == null) {
            throw new Exception("No issued book found for the given book ID and PRN.");
        }


        LibraryDB.issuedBooks.remove(issueToRemove);

        return "Book with ID " + bookId + " returned by student with PRN " + prnNo;
    }
}

