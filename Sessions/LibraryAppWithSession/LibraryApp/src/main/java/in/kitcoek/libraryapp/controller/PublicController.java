package in.kitcoek.libraryapp.controller;

import in.kitcoek.libraryapp.dao.LibrarianDao;
import in.kitcoek.libraryapp.dao.StudentDao;
import in.kitcoek.libraryapp.model.Librarian;
import in.kitcoek.libraryapp.model.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private LibrarianDao librarianDao;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String validate(@RequestParam int id, @RequestParam String password, HttpSession session, Model model){
        Librarian librarian = librarianDao.findById(id).orElse(null);
        Student student = studentDao.findById(id).orElse(null);
        if(librarian != null){
            if(librarian.getPassword().equals(password)){
                session.setAttribute(String.valueOf(id), "SESSION");
                return "redirect:/librarian/index/"+id;
            }
        }
        else if (student != null) {
            if(student.getPassword().equals(password)){
                session.setAttribute(String.valueOf(id), "SESSION");
                model.addAttribute("role","student");
                model.addAttribute("prn",student.getPrn());
                model.addAttribute("id",student.getPrn());
                return "index";
            }
        }
        return "redirect:/public/login";
    }
    @GetMapping("/logout/{id}")
    public String logout(HttpSession session, @PathVariable String id){
        session.removeAttribute(id);
        return "redirect:/public/login";
    }
}
