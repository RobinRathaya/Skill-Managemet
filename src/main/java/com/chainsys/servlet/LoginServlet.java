package com.chainsys.servlet;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.model.Student;
import com.chainsys.services.LoginService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("Email");
		String password = request.getParameter("Password");
		Student student = new Student();
		student.setUserName(userName);
		student.setPassword(password);
		LoginService authentication = new LoginService();
		int studentID = 0;
		RequestDispatcher rs;
		HttpSession httpSession = request.getSession(false);
		try {
			studentID = authentication.isLogin(student);

		} catch (AuthenticationException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
		if (studentID > 0) {
			httpSession.setAttribute("STUDENTID", studentID);
			rs = request.getRequestDispatcher("userdashboard.jsp");
			rs.forward(request, response);
		} else {
			rs = request.getRequestDispatcher("login.html");
			rs.forward(request, response);
		}

	}
}
