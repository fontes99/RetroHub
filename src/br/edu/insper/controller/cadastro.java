package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.insper.model.*;

/**
 * Servlet implementation class cadastro
 */
@WebServlet("/cadastro")
public class cadastro extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		PrintWriter out = response.getWriter();
		
		if (!request.getParameter("password").equals(request.getParameter("passwordConf"))) {
			out.println("<style>b {color:red;}</style>");
			out.println("<br/><b>Senha diferente nos campos!</b><br/>");
			
		} else {
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			if (dao.checkIfUserExists(user)) {
				out.println("<style>b {color:red;}</style>");
				out.println("<br/><b>Este usuário já existe!</b><br/>");
			} else {
				dao.addUser(user);
				response.sendRedirect("login");
			}
		}
	}

}
