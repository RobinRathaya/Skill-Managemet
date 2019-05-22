package com.chainsys.services;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.dao.CreateQuizDAO;
import com.chainsys.model.Quiz;

public class CreateQuizService {

	public int createQuiz(Quiz quiz) {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.createQuiz(quiz);
	}

	public List<Quiz> getUpcomingQuiz() throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.upcomingQuiz();
	}

	public List<Quiz> getCompletedQuiz() throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.completedQuiz();
	}
	
	public boolean cancelQuiz(Quiz quiz) throws SQLException {
		CreateQuizDAO createQuizDAO = new CreateQuizDAO();
		return createQuizDAO.cancelQuiz(quiz);
	}

}
