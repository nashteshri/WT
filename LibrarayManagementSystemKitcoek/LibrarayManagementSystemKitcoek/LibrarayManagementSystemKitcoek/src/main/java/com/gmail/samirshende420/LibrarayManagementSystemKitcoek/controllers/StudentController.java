package com.gmail.samirshende420.LibrarayManagementSystemKitcoek.controllers;


import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.DB.LibraryDB;
import com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return LibraryDB.studentList;
    }

    // Get a student by PRN
    @GetMapping("/{prnNo}")
    public Student getStudentByPrn(@PathVariable Long prnNo) throws Exception {
        for (Student student : LibraryDB.studentList) {
            if (student.getPrnNo().equals(prnNo)) {
                return student;
            }
        }
        throw new Exception("Student Not Found with PRN: " + prnNo);
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) throws Exception {
        if (student.getPrnNo() == null || student.getName().isBlank() || student.getBranch().isBlank() || student.getYear().isBlank()) {
            throw new Exception("All fields (PRN, name, branch, year) are required.");
        }
        for (Student existingStudent : LibraryDB.studentList) {
            if (existingStudent.getPrnNo().equals(student.getPrnNo())) {
                throw new Exception("Student with PRN " + student.getPrnNo() + " already exists.");
            }
        }
        LibraryDB.studentList.add(student);
        return student;
    }

    @PutMapping("/update/{prnNo}")
    public Student updateStudent(@PathVariable Long prnNo, @RequestBody Student updatedStudent) throws Exception {
        for (Student student : LibraryDB.studentList) {
            if (student.getPrnNo().equals(prnNo)) {
                student.setName(updatedStudent.getName());
                student.setBranch(updatedStudent.getBranch());
                student.setYear(updatedStudent.getYear());
                return student;
            }
        }
        throw new Exception("Student Not Found with PRN: " + prnNo);
    }

    @DeleteMapping("/delete/{prnNo}")
    public String deleteStudent(@PathVariable Long prnNo) throws Exception {
        Student studentToDelete = null;
        for (Student student : LibraryDB.studentList) {
            if (student.getPrnNo().equals(prnNo)) {
                studentToDelete = student;
            }
        }
        if (studentToDelete == null) {
            throw new Exception("Student Not Found with PRN: " + prnNo);
        }
        LibraryDB.studentList.remove(studentToDelete);
        return "Student with PRN " + prnNo + " deleted successfully.";
    }
}
