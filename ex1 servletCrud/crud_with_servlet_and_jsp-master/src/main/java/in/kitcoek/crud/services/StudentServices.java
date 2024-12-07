package in.kitcoek.crud.services;

import java.io.IOException;
import java.util.List;

import in.kitcoek.crud.model.Student;
import in.kitcoek.crud.repo.StudentRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentServices {
	StudentRepo repo = new StudentRepo();
	
	public void listStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> list=  repo.selectAllStudents();
        req.setAttribute("Studentlist", list);
        for(Student s : list) {
        	System.out.println(s.getPrn());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(req, resp);
	}
	public void showNewForm(HttpServletRequest request, HttpServletResponse response)throws Exception{
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

	public void showEditForm(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int prn = Integer.parseInt(request.getParameter("prn"));
        Student existingStudent = repo.selectStudent(prn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);

    }

	public void insertStudent(HttpServletRequest request, HttpServletResponse response)throws Exception{
//		System.out.println("inserting...");
        int prn = Integer.parseInt(request.getParameter("prn"));
        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        Student student = new Student(prn, name, branch);
        repo.insertStudent(student);
        response.sendRedirect("list");
    }

	public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prn = Integer.parseInt(request.getParameter("prn"));
        String name = request.getParameter("name");
        String branch = request.getParameter("branch");

        Student student = new Student(prn, name, branch);
        repo.deleteStudent(prn);
        repo.insertStudent(student);
       
        response.sendRedirect("list");
    }

	public void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prn = Integer.parseInt(request.getParameter("prn"));
        repo.deleteStudent(prn);
        response.sendRedirect("list");
    }
}
