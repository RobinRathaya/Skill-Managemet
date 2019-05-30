package com.chainsys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.model.Questions;
import com.chainsys.model.Quiz;
import com.chainsys.model.Score;
import com.chainsys.model.Student;
import com.chainsys.model.Topics;
import com.chainsys.services.CreateQuizService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CreateTest
 */
// @WebServlet("/TestManagement")
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("request");
		CreateQuizService createQuizService = new CreateQuizService();
		String message = "";
		List<Quiz> upcomingQuizList = null;
		List<Questions> questionList = null;
		HttpSession httpSession = request.getSession(false);
		int studentId=0;
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
				for (int i = 0; i < upcomingQuizList.size(); i++) {
					Quiz addNoOfQuestions = upcomingQuizList.get(i);
					questionList = createQuizService.takeTest(addNoOfQuestions);
					addNoOfQuestions.setNoOfQuestion(questionList.size());
				}
			} catch (SQLException e) {
				message = "No results found";
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String quizList = gson.toJson(upcomingQuizList);
			response.getWriter().write(quizList);
			/*
			 * request.setAttribute("message", "kk"); request.setAttribute("QuizList",
			 * upcomingQuizList); requestDispatcher =
			 * request.getRequestDispatcher("viewupcomingtest.jsp");
			 * requestDispatcher.forward(request, response);
			 */
			break;
		case "CompletedTest":
			List<Quiz> completedQuizList = null;
			try {
				completedQuizList = createQuizService.getCompletedQuiz();
			} catch (SQLException e) {
				message = "No results found";
			}

			gson = new GsonBuilder().setPrettyPrinting().create();
			quizList = gson.toJson(completedQuizList);
			response.getWriter().write(quizList);
			/*
			 * request.setAttribute("message", message); request.setAttribute("QuizList",
			 * completedQuizList); requestDispatcher =
			 * request.getRequestDispatcher("viewtest.jsp");
			 * requestDispatcher.forward(request, response);
			 */
			break;
		case "CancelTest":
			String quizId = request.getParameter("quizId");
			quiz = new Quiz();
			quiz.setId(Integer.parseInt(quizId));
			quiz.setStatus(2);
			try {
				boolean success = createQuizService.cancelQuiz(quiz);
				if (success) {
					message = "cancelled successfully";
				} else {
					message = "cancellation failed";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				upcomingQuizList = createQuizService.getUpcomingQuiz();
			} catch (SQLException e) {
				message = "No results found";
			}

			gson = new GsonBuilder().setPrettyPrinting().create();
			quizList = gson.toJson(upcomingQuizList);
			response.getWriter().write(quizList);
			break;
		case "TakeTest":
			quizId = httpSession.getAttribute("quizId").toString();
			quiz = new Quiz();
			quiz.setId(Integer.parseInt(quizId));
			try {
				questionList = createQuizService.takeTest(quiz);
				gson = new GsonBuilder().setPrettyPrinting().create();
				String quesList = gson.toJson(questionList);
				response.getWriter().write(quesList);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "GetQuizInfo":
			quizId = request.getParameter("quizId");
			httpSession.setAttribute("quizId", quizId);
			quiz = new Quiz();
			quiz.setId(Integer.parseInt(quizId));
			Quiz quizInfo = null;
			try {
				quizInfo = createQuizService.getQuizInfo(quiz);
				questionList = createQuizService.takeTest(quiz);
				quizInfo.setNoOfQuestion(questionList.size());
				gson = new GsonBuilder().setPrettyPrinting().create();
				quizList = gson.toJson(quizInfo);
				response.getWriter().write(quizList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "endExam":
			quizId = httpSession.getAttribute("quizId").toString();
			quiz = new Quiz();
			quiz.setId(Integer.parseInt(quizId));
			String answerList=request.getParameter("answerList");
			gson=new Gson();
			HashMap<String,String> answer=gson.fromJson(answerList,HashMap.class);
			Score score=new Score();
			int rightQues=0;
			try {
				questionList = createQuizService.takeTest(quiz);
				for(Map.Entry<String, String> s:answer.entrySet())
				{
					String ans="";
					int question=Integer.parseInt(s.getKey());
					
					for(Questions q:questionList)
					{
						if(q.getId()==question)
						{
							ans=q.getAnswers();
							break;
						}
					}
					if(ans.equals(s.getValue().trim()))
					{
						rightQues+=1;
					}
				}
				score.setQuiz(quiz);
				Student student=new Student();
				studentId=Integer.parseInt(httpSession.getAttribute("STUDENTID").toString());
				student.setId(studentId);
				score.setStudent(student);
				score.setCorrect(rightQues);
				score.setInCorrect(questionList.size()-rightQues);
				score.setCreatedBy(1);
				score.setCreatedDate(LocalDateTime.now());
				successCount=createQuizService.addTestScore(score);
				String result="";
				if(successCount>0)
				{
					result="success";
				}
				response.getWriter().write(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "getTestScore":
			/*quizId = httpSession.getAttribute("quizId").toString();
			quiz = new Quiz();
			quiz.setId(Integer.parseInt(quizId));*/
			score=new Score();
			/*score.setQuiz(quiz);*/
			studentId=Integer.parseInt(httpSession.getAttribute("STUDENTID").toString());
			Student student=new Student();
			student.setId(studentId);
			score.setStudent(student);
			String testResult="";
			try {
				List<Score> resultList=createQuizService.getTestScoreByQuizId(score);
				gson=new Gson();
				testResult=gson.toJson(resultList);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(testResult);
			break;
		default:
			System.out.println("Invalid process");
			break;
		}

	}

}
