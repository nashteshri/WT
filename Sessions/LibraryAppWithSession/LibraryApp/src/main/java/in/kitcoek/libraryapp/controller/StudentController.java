package in.kitcoek.libraryapp.controller;

import in.kitcoek.libraryapp.dao.StudentDao;
import in.kitcoek.libraryapp.model.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @GetMapping("/addStudentForm/{id}")
    public String addStudentForm(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("id",id);
        return "studentForm";
    }

    @PostMapping("/addStudent/{id}")
    public String insertStudent(@ModelAttribute Student s, Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session,id)){
            return "redirect:/public/login";
        }
        if(studentDao.findById(s.getPrn()).orElse(null) != null) {
            model.addAttribute("id",id);
            return "studentForm";
        }
        studentDao.save(s);
        return "redirect:/student/studentList/"+id;
    }

    @GetMapping("/studentList/{id}")
    public String getAllStudents(Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("students",studentDao.findAll());
        model.addAttribute("id",id);
        return "studentList";
    }

    @GetMapping("/update/{prn}/{id}")
    public String updateStudent(@PathVariable("prn") Integer prn,Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("student",studentDao.getById(prn));
        model.addAttribute("id",id);
        return "updateStudentForm";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute Student student, Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        Student existingStudent = studentDao.findById(student.getPrn()).orElse(null);
        existingStudent.setName(student.getName());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setPassword(student.getPassword());
        studentDao.save(existingStudent);
        return "redirect:/student/studentList/"+id;
    }

    @GetMapping("/delete/{prn}/{id}")
    public String deleteByPrn(@PathVariable("prn") int prn,HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        studentDao.deleteById(prn);
        return "redirect:/student/studentList/"+id;
    }
    public boolean isLoggedIn(HttpSession session, String id){
        return session.getAttribute(id) != null && session.getAttribute(id).equals("SESSION");
    }
}
