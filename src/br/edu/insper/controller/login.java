package br.edu.insper.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;
import br.edu.insper.model.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pass = request.getParameter("password");
		String name = request.getParameter("username");
		
		if(!pass.isEmpty() || !name.isEmpty()) {
			DAO dao = new DAO();
			User user= new User();
			user.setName(name);
			user.setPassword(pass);
			
			boolean status = dao.login(user);
			
			if(status) {
				int id = dao.getUserId(user);
				request.getSession().setAttribute("idUser", id);
				response.sendRedirect("main");
			}
			
			else {
				request.setAttribute("loged", "username ou senha incorretos");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
		
		else {
			request.setAttribute("emptyCamps", "campos vazios");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

}
