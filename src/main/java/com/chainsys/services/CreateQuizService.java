package com.chainsys.services;

import com.chainsys.dao.CreateQuizDAO;
import com.chainsys.model.Quiz;

public class CreateQuizService {
	
	public int createQuiz(Quiz quiz)
	{
		CreateQuizDAO createQuizDAO=new CreateQuizDAO();
		return createQuizDAO.createQuiz(quiz);
		
	}

}
