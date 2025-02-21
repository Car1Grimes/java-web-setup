package com.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.dao.UserDAO;
import com.todo.dto.UserDTO;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		
		int userId = userDAO.login(userDTO);
		userDTO.setUserId(userId);
		if (userId >= 1) {
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userDTO);
			response.sendRedirect("tasks");
		} else {
			request.setAttribute("errorMessage", "Incorrect username or password or account not existed.");
			request.getRequestDispatcher("login/login.jsp").forward(request, response);
		}
	}

}
