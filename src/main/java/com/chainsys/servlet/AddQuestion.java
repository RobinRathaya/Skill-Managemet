package com.chainsys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.model.Questions;
import com.chainsys.model.Topics;
import com.chainsys.services.QuestionService;

/**
 * Servlet implementation class AddQuestion
 */
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddQuestion() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message="";
		if (!request.getParameter("topic").isEmpty() && !request.getParameter("question").isEmpty()
				&& !request.getParameter("option1").isEmpty() && !request.getParameter("option2").isEmpty()
				&& !request.getParameter("option3").isEmpty() && !request.getParameter("option4").isEmpty()) {
			Topics topic = new Topics();
			Questions question = new Questions();
			topic.setId(Integer.parseInt(request.getParameter("topic")));
			question.setQuestion(request.getParameter("question"));
			question.setTopic(topic);
			String[] options = new String[4];
			options[0] = request.getParameter("option1");
			options[1] = request.getParameter("option2");
			options[2] = request.getParameter("option3");
			options[3] = request.getParameter("option4");
			int optionId = Integer.parseInt(request.getParameter("answer"));
			QuestionService questionService = new QuestionService();
			message = questionService.addQuestion(question, options, optionId);
		} else {
			message="Invalid Input";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("addquestions.jsp");
		rd.forward(request, response);
	}

}
