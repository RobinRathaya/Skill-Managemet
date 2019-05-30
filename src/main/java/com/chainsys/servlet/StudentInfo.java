package com.chainsys.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.model.Student;
import com.chainsys.services.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("request");
		StudentService studentService = new StudentService();
		RequestDispatcher requestDispatcher = null;
		List<Student> studentDetailsList = null;
		String message = null;
		int studentId = 0;
		Student studentInfo = null;
		HttpSession httpSession=request.getSession(false);
		switch (action) {
		case "addstudent":
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phonenumber = request.getParameter("phonenumber");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Student student = new Student();
			if (!name.isEmpty() && name != null) {
				student.setName(name);
			}
			if (!email.isEmpty() && email != null) {
				student.setEmail(email);
			}
			if (!phonenumber.isEmpty() && phonenumber != null) {
				student.setPhonenumber(phonenumber);
			}
			if (!username.isEmpty() && username != null) {
				student.setUserName(username);
			}
			if (!password.isEmpty() && password != null) {
				student.setPassword(password);
			}
			student.setCreatedBy(1);
			student.setCreatedDate(LocalDateTime.now());
			try {
				boolean success = studentService.addStudent(student);
				if (success) {
					message = "Student info added successfully.";
				}
			} catch (Exception e) {
				message = e.getMessage();
			}
			request.setAttribute("MESSAGE", message);
			requestDispatcher = request.getRequestDispatcher("addstudent.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "viewbyname":
			name = request.getParameter("name");
			Student infoModel = new Student();
			if (!name.isEmpty() && name != null) {
				infoModel.setName(name);
			}
			try {
				studentInfo = studentService.getStudentInfoByName(infoModel);
				if (studentInfo == null) {
					message = "No results found";
				}
			} catch (Exception e) {
				message = "No results found";
			}
			request.setAttribute("message", message);
			request.setAttribute("STUDENTDETAILS", studentInfo);
			requestDispatcher = request.getRequestDispatcher("viewstudent.jsp");
			requestDispatcher.forward(request, response);

			break;
		case "viewall":
			try {
				studentDetailsList = studentService.getAllStudentInfo();
				if (studentDetailsList.isEmpty() || studentDetailsList == null) {
					message = "no results found";
				}
			} catch (Exception e) {
				message = "no results found";
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String studentList = gson.toJson(studentDetailsList);
			response.getWriter().write(studentList);
			/*
			 * request.setAttribute("message", message); request.setAttribute("STUDENTlIST",
			 * studentDetailsList); requestDispatcher =
			 * request.getRequestDispatcher("viewallstudent.jsp");
			 * requestDispatcher.forward(request, response);
			 */

			break;
		case "editstudentprofile":
//			studentId = Integer.valueOf(httpSession.getAttribute("STUDENTID").toString());
			studentId = Integer.valueOf(request.getParameter("id"));
			Student editStudent = new Student();
			Student editStudentProfile = null;
			editStudent.setId(studentId);
			try {
				editStudentProfile = studentService.getStudentInfoById(editStudent);
				if (editStudentProfile == null) {
					message = "No results found";
				}
			} catch (Exception e1) {
				message = "No results found";
			}
			gson = new GsonBuilder().setPrettyPrinting().create();
			studentList = gson.toJson(editStudentProfile);
			response.getWriter().write(studentList);
			/*
			 * request.setAttribute("message", message);
			 * request.setAttribute("STUDENTDETAILS", editStudentProfile); requestDispatcher
			 * = request.getRequestDispatcher("editprofile.jsp");
			 * requestDispatcher.forward(request, response);
			 */

			break;
		case "deletestudent":
//			studentId = Integer.valueOf(httpSession.getAttribute("STUDENTID").toString());
			studentId = Integer.valueOf(request.getParameter("id"));
			String deleteMessage = "";
			Student deleteStudent = new Student();
			deleteStudent.setId(studentId);
			try {
				boolean success = studentService.deleteStudent(deleteStudent);
				if (success) {
					deleteMessage = "delete successfully";
					/*
					 * studentDetailsList = studentService.getAllStudentInfo(); if
					 * (studentDetailsList.isEmpty()) { message = "no results found"; }
					 */
				} else {
					deleteMessage = "Unable to delete";
				}
			} catch (Exception e) {
				deleteMessage = e.getMessage();
			}
			response.getWriter().write(deleteMessage);
			/*
			 * request.setAttribute("message", message);
			 * request.setAttribute("deleteMessage", deleteMessage);
			 * request.setAttribute("STUDENTlIST", studentDetailsList); requestDispatcher =
			 * request.getRequestDispatcher("viewallstudent.jsp");
			 * requestDispatcher.forward(request, response);
			 */
			break;
		case "updateprofile":
//			studentId = Integer.valueOf(httpSession.getAttribute("STUDENTID").toString());
			studentId = Integer.valueOf(request.getParameter("id"));
			name = request.getParameter("name");
			email = request.getParameter("email");
			phonenumber = request.getParameter("phonenumber");
			username = request.getParameter("username");
			password = request.getParameter("password");
			String updateMessage = null;
			Student updateInfo = new Student();
			if (!name.isEmpty() && name != null) {
				updateInfo.setName(name);
			}
			if (!email.isEmpty() && email != null) {
				updateInfo.setEmail(email);
			}
			if (!phonenumber.isEmpty() && phonenumber != null) {
				updateInfo.setPhonenumber(phonenumber);
			}
			if (!username.isEmpty() && username != null) {
				updateInfo.setUserName(username);
			}
			if (!password.isEmpty() && password != null) {
				updateInfo.setPassword(password);
			}
			updateInfo.setId(studentId);
			updateInfo.setModifiedBy(1);
			updateInfo.setModifiedDate(LocalDateTime.now());
			try {
				boolean success = studentService.updateStudent(updateInfo);
				if (success) {
					updateMessage = "Student info updated successfully.";
					studentDetailsList = studentService.getAllStudentInfo();
					if (studentDetailsList.isEmpty() || studentDetailsList == null) {
						message = "no results found";
					}
				}
			} catch (Exception e) {
				updateMessage = e.getMessage();
			}
			/*
			 * System.out.println(studentDetailsList); request.setAttribute("updateMessage",
			 * updateMessage); request.setAttribute("MESSAGE", message);
			 * request.setAttribute("STUDENTlIST", studentDetailsList);
			 */
			requestDispatcher = request.getRequestDispatcher("statistics.jsp");
			requestDispatcher.forward(request, response);
			break;

		case "getStudentInfo":
			studentId = Integer.valueOf(httpSession.getAttribute("STUDENTID").toString());
			student = new Student();
			student.setId(studentId);
			try {
				studentInfo = studentService.getStudentInfoById(student);
				if (studentInfo == null) {
					message = "No results found";
				}
			} catch (Exception e1) {
				message = "No results found";
			}
			gson = new GsonBuilder().setPrettyPrinting().create();
			studentList = gson.toJson(studentInfo);
			response.getWriter().write(studentList);
			break;
		default:
			System.out.println("invalid input");
			break;
		}

	}

}
