package in.kitcoek.libraryapp.controller;


import in.kitcoek.libraryapp.dao.BookDao;
import in.kitcoek.libraryapp.dao.LibrarianDao;
import in.kitcoek.libraryapp.dao.RequestsDao;
import in.kitcoek.libraryapp.dao.StudentDao;
import in.kitcoek.libraryapp.model.Book;
import in.kitcoek.libraryapp.model.Librarian;
import in.kitcoek.libraryapp.model.Requests;
import in.kitcoek.libraryapp.model.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RequestsDao requestsDao;

    @GetMapping("/index/{id}")
    public String index(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("role","librarian");
        model.addAttribute("id",id);
        return "index";
    }

    @GetMapping("/issueForm/{id}")
    public String issueForm(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("requests",requestsDao.findAll());
        model.addAttribute("id",id);
        return "issueForm";
    }
    @GetMapping("/collectForm/{id}")
    public String collectForm(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("id",id);
        return "collectForm";
    }

    @GetMapping("/issue/{isbn}/{prn}/{requestsId}/{id}")
    public String issueBook(@PathVariable("isbn") int isbn, @PathVariable("prn") int prn,HttpSession session, @PathVariable int requestsId, @PathVariable int id) {
        if(!isLoggedIn(session, String.valueOf(id))){
            return "redirect:/public/login";
        }
        try{
            Student student =  studentDao.findById(prn).orElse(null);
            Book book = bookDao.findById(isbn).orElse(null);
            if(student == null || book == null || student.getIssuedBooks().size() > 3) {
                return "redirect:/librarian/issueForm/"+id;
            }
            List<Book> list = student.getIssuedBooks();
            list.add(book);
            student.setIssuedBooks(list);
            studentDao.save(student);
            requestsDao.deleteById(requestsId);

            return "redirect:/librarian/issueForm/"+id;
        }catch(Exception e){
            return "redirect:/librarian/issueForm/"+id;
        }
    }
    @GetMapping("/issuedBooks/{prn}/{id}")
    public String getIssuedBooks(@PathVariable("prn") int prn, Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        Student student = studentDao.findById(prn).orElse(null);
        assert student != null;
        model.addAttribute("books",student.getIssuedBooks());
        model.addAttribute("id",id);
        return "displayIssuedBooks";
    }
    @PostMapping("/collect/{id}")
    public String collectBook(@RequestParam("isbn") int isbn, @RequestParam("prn") int prn, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        Student student = studentDao.findById(prn).orElse(null);
        if(student == null) {
            return "redirect:/librarian/collectForm/"+id;
        }
        boolean found = false;
        List<Book> issuedBooksList = student.getIssuedBooks();
        for(Book book : student.getIssuedBooks()){
            if(book.getIsbn() == isbn){
                issuedBooksList.remove(book);
                found = true;
                break;
            }
        }
        if(found){
            student.setIssuedBooks(issuedBooksList);
            studentDao.save(student);
            return "redirect:/librarian/index/"+id;
        }
        return "redirect:/librarian/collectForm/"+id;
    }
    @GetMapping("/request/{prn}/{isbn}")
    public String request(@PathVariable int isbn, @PathVariable int prn, HttpSession session){
        if(!isLoggedIn(session, String.valueOf(prn))){
            return "redirect:/public/login";
        }
        Student student = studentDao.findById(prn).orElse(null);
        if(student != null && student.getIssuedBooks().size() < 3){
            Requests requests = new Requests();
            requests.setIsbn(isbn);
            requests.setPrn(prn);
            requestsDao.save(requests);
        }
        return "redirect:/book/requestBook/"+prn;
    }
    public boolean isLoggedIn(HttpSession session, String id){
        return session.getAttribute(id) != null && session.getAttribute(id).equals("SESSION");
    }
}


//    @GetMapping("/getLibrarian")
//    public Librarian getLibrarian() {
//        if(librarianDao.findAll().isEmpty()) {
//            return null;
//        }
//        return librarianDao.findAll().get(0);
//    }
//    @PostMapping
//    public boolean addLibrarian(@RequestBody Librarian librarian) {
//        if(librarianDao.findAll().size() == 1){
//            return false;
//        }
//        librarianDao.save(librarian);
//        return true;
//    }
//    @PostMapping("/update/{id}")
//    public boolean updateLibrarian(@RequestBody Librarian librarian, @PathVariable int id) {
//        if(librarianDao.findById(id).orElse(null) == null){
//            return false;
//        }
//        librarianDao.deleteById(id);
//        librarianDao.save(librarian);
//        return true;
//    }

//    @DeleteMapping("/delete/{id}")
//    public boolean deleteLibrarian(@PathVariable int id) {
//        if(librarianDao.findById(id).orElse(null) == null){
//            return false;
//        }
//        librarianDao.deleteAll();
//        return true;
//    }