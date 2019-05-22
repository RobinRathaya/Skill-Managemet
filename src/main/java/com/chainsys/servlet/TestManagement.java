package com.chainsys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.model.Quiz;
import com.chainsys.model.Topics;
import com.chainsys.services.CreateQuizService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CreateTest
 */
//@WebServlet("/TestManagement")
public class TestManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestManagement() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("request");
		CreateQuizService createQuizService = new CreateQuizService();
		String message="";
		List<Quiz> upcomingQuizList = null;
		switch (action) {
		case "addquiz":
			String topic = request.getParameter("topic");
			String quizName = request.getParameter("quizname");
			String quizTime = request.getParameter("quiztime");
			String quizDate = request.getParameter("quizexpirydate");
			Quiz quiz = new Quiz();
			Topics topics = new Topics();
			topics.setId(Integer.parseInt(topic));
			quiz.setTopics(topics);
			quiz.setName(quizName);
			quiz.setDurationTime(LocalTime.parse(quizTime));
			quiz.setExpiredDate(LocalDate.parse(quizDate));
			quiz.setStatus(1);
			int successCount = createQuizService.createQuiz(quiz);
			if (successCount > 0) {
				message = "Quiz created successfully";
			} else {
				message = "Quiz not created";
			}
			request.setAttribute("MESSAGE", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("addtest.jsp");
			requestDispatcher.forward(request, response);
			break;
			
		case "UpcomingTest":
			try {
				upcomingQuizList = createQuizService.getUpcomingQuiz();
			} catch (SQLException e) {
				message="No results found";
			}
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			String quizList=gson.toJson(upcomingQuizList);
			response.getWriter().write(quizList);
			/*request.setAttribute("message", "kk");
			request.setAttribute("QuizList", upcomingQuizList);
			requestDispatcher = request.getRequestDispatcher("viewupcomingtest.jsp");
			requestDispatcher.forward(request, response);*/
			break;
		case "CompletedTest":
			List<Quiz> completedQuizList = null;
			try {
				completedQuizList = createQuizService.getCompletedQuiz();
			} catch (SQLException e) {
				message="No results found";
			}
			
			
			gson=new GsonBuilder().setPrettyPrinting().create();
			quizList=gson.toJson(completedQuizList);
			response.getWriter().write(quizList);
			/*request.setAttribute("message", message);
			request.setAttribute("QuizList", completedQuizList);
			requestDispatcher = request.getRequestDispatcher("viewtest.jsp");
			requestDispatcher.forward(request, response);*/
			break;
		case "CancelTest":
			String quizId=request.getParameter("quizId");
			quiz=new Quiz();
			quiz.setId(Integer.parseInt(quizId));
			quiz.setStatus(2);
			try {
				boolean success=createQuizService.cancelQuiz(quiz);
				if(success)
				{
					message="cancelled successfully";
				}
				else
				{
					message="cancellation failed";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				upcomingQuizList = createQuizService.getUpcomingQuiz();
			} catch (SQLException e) {
				message="No results found";
			}
			
			gson=new GsonBuilder().setPrettyPrinting().create();
			quizList=gson.toJson(upcomingQuizList);
			response.getWriter().write(quizList);
			break;
		default:
			System.out.println("Invalid process");
			break;
		}

	}

}
