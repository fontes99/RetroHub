package br.edu.insper.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;
import br.edu.insper.model.Subject;

/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		String newSub = request.getParameter("newSubject");
		int idUser = (int) request.getSession().getAttribute("idUser");
		
		if (!newSub.isEmpty()) {
			Subject subject = new Subject();
			subject.setSubject(newSub);
			subject.setURL(newSub);
			dao.addSubject(subject);
			request.getSession().setAttribute("idUser", idUser);
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request,response);
		}
	}

}
