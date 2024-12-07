package in.kitcoek.crud.controller;

import java.io.IOException;

import in.kitcoek.crud.services.StudentServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="StudentController")
public class StudentController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private StudentServices services = new StudentServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		
		try {
            switch (action) {
                case "/new":
                	services.showNewForm(req, resp);
                    break;
                case "/insert":
//                	System.out.println("in insert");
                	services.insertStudent(req, resp);
                    break;
                case "/delete":
                	services.deleteStudent(req, resp);
                    break;
                case "/edit":
                	services.showEditForm(req, resp);
                    break;
                case "/update":
//                	System.out.println("in update");
                	services.updateStudent(req, resp);
                    break;
                case "/list":
                	services.listStudent(req, resp);
                    break;
                default:
                	services.listStudent(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
