package com.chainsys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.customExceptions.TestifyExceptions;
import com.chainsys.services.AddTopicService;

import oracle.jdbc.driver.Message;

/**
 * Servlet implementation class AddTopics
 */
@WebServlet("/Addtopics")
public class AddTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTopics() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topicName = request.getParameter("topic");
		AddTopicService addTopicService = new AddTopicService();
		try {
			String message = addTopicService.addNewTopic(topicName);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("addtopic.jsp");
			rd.forward(request, response);
		} catch (TestifyExceptions e) {
			request.setAttribute("message", "failed");
			RequestDispatcher rd = request.getRequestDispatcher("addtopic.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}
}
