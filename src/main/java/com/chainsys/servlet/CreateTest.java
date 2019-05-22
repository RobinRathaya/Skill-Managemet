package com.chainsys.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.model.Quiz;
import com.chainsys.model.Topics;
import com.chainsys.services.CreateQuizService;

/**
 * Servlet implementation class CreateTest
 */
public class CreateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTest() {
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
		String topic =request.getParameter("topic");
		String quizName =request.getParameter("quizname");
		String quizTime =request.getParameter("quiztime");
		String quizDate =request.getParameter("quizexpirydate");
		String message="";
		Quiz quiz=new Quiz();
		Topics topics=new Topics();
		topics.setId(Integer.parseInt(topic));
		quiz.setTopics(topics);
		quiz.setName(quizName);
		quiz.setDurationTime(LocalTime.parse(quizTime));
		quiz.setExpiredDate(LocalDate.parse(quizDate));
		quiz.setStatus(1);
		CreateQuizService createQuizService=new CreateQuizService();
		int successCount=createQuizService.createQuiz(quiz);
		if(successCount>0)
		{
			message="Quiz created successfully";
		}
		else
		{
			message="Quiz not created";
		}
		request.setAttribute("MESSAGE", message);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("addtest.jsp");
		requestDispatcher.forward(request, response);
		
		
	}

}
