package com.chainsys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.dao.TopicsDAO;
import com.chainsys.model.Topics;
import com.chainsys.validation.Validator;

/**
 * Servlet implementation class AddTopic
 */
public class AddTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTopic() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Topics topic = new Topics();
		String topicName = request.getParameter("topic");
		Validator validate = new Validator();
		Boolean isFound;
		try {
			isFound = validate.checkForTopic(topicName);
			if (isFound) {
				topic.setName(topicName);
				
					int statusId = TopicsDAO.addNewTopic(topic);
					String message = null;
					if (statusId > 0) {
						message = "topic added successfully";
					} else {
						message = "failed to add topic";
					}
					System.out.println(message);
					RequestDispatcher rd = request.getRequestDispatcher("addTopic.jsp");
					request.setAttribute("message", message);
					rd.forward(request, response);
			}
		}
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			

}
	
}

