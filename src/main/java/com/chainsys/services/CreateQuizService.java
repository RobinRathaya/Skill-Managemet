package com.chainsys.services;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.dao.CreateQuizDAO;
import com.chainsys.model.Questions;
import com.chainsys.model.Quiz;
import com.chainsys.model.Score;

public class CreateQuizService {

	public int createQuiz(Quiz quiz) {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.createQuiz(quiz);
	}

	public List<Quiz> getUpcomingQuiz() throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.upcomingQuiz();
	}
	
	public Quiz getQuizInfo(Quiz quizDetails) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.getQuizById(quizDetails);
	}

	public List<Quiz> getCompletedQuiz() throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.completedQuiz();
	}
	
	public boolean cancelQuiz(Quiz quiz) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.cancelQuiz(quiz);
	}
		
	public List<Questions> takeTest(Quiz quiz) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.getQuizQuestion(quiz);
	}
	
	public int addTestScore(Score score) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.addScore(score);
	}
	
	public Score getTestScoreByQuizId(Score score) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.getTestScoreByQuizId(score);
	}
	

}
  