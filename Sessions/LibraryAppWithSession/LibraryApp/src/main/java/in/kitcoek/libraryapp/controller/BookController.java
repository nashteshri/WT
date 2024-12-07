package in.kitcoek.libraryapp.controller;

import in.kitcoek.libraryapp.dao.BookDao;
import in.kitcoek.libraryapp.dao.RequestsDao;
import in.kitcoek.libraryapp.model.Book;
import in.kitcoek.libraryapp.model.Requests;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    BookDao bookDao;
    @Autowired
    RequestsDao requestsDao;

    @GetMapping("/bookList/{id}")
    public String bookList(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("id", id);
        return "bookList";
    }
    @GetMapping("/addBookForm/{id}")
    public String addBookForm(Model model, HttpSession session, @PathVariable String id){
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("id", id);
        return "bookForm";
    }

    @GetMapping("/requestBook/{prn}")
    public String searchBook(Model model, HttpSession session, @PathVariable int prn){
        if(!isLoggedIn(session, String.valueOf(prn))){
            return "redirect:/public/login";
        }
        List<Requests> requests = requestsDao.findAllByPrn(prn);
        List<Integer> isbn = new ArrayList<>();

        for (Requests request: requests) {
            isbn.add(request.getIsbn());
        }
        List<Book> books = bookDao.findAll();

        for(int i=books.size()-1; i>=0; i--){
            if(isbn.contains(books.get(i).getIsbn())){
                books.remove(i);
            }
        }
        model.addAttribute("books",books);
        model.addAttribute("prn",prn);
        model.addAttribute("id", prn);
        return "requestBook";
    }

    @PostMapping("/addBook/{id}")
    public String insertBook(@ModelAttribute Book book,Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        if(bookDao.findById(book.getIsbn()).orElse(null) != null) {
            model.addAttribute("id", id);
            return "bookForm";
        }
        bookDao.save(book);
        return "redirect:/book/bookList/"+id;
    }

    @GetMapping("/update/{isbn}/{id}")
    public String updateBook(@PathVariable("isbn") Integer isbn, Model model, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        model.addAttribute("book",bookDao.getById(isbn));
        model.addAttribute("id", id);
        return "updateBookForm";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Book book, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        Book oldBook = bookDao.findById(book.getIsbn()).orElse(null);
        assert oldBook != null;
        oldBook.setAuthor(book.getAuthor());
        oldBook.setTitle(book.getTitle());
        oldBook.setPublishYear(book.getPublishYear());
        bookDao.save(oldBook);
        return "redirect:/book/bookList/"+id;
    }
    @GetMapping("/delete_error")
    @ResponseBody
    public String deleteError(){
        return "Book is issued to a student";
    }

    @GetMapping("/delete/{isbn}/{id}")
    public String deleteByIsbn(@PathVariable("isbn") int isbn, HttpSession session, @PathVariable String id) {
        if(!isLoggedIn(session, id)){
            return "redirect:/public/login";
        }
        try{
            bookDao.deleteById(isbn);
            return "redirect:/book/bookList/"+id;
        }catch (Exception e){
            return "redirect:/book/delete_error";
        }
    }
    public boolean isLoggedIn(HttpSession session, String id){
        return session.getAttribute(id) != null && session.getAttribute(id).equals("SESSION");
    }
}
