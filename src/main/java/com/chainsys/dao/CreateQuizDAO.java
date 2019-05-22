package com.chainsys.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.chainsys.model.Quiz;
import com.chainsys.util.ConnectionUtil;

public class CreateQuizDAO {
	
	public int createQuiz(Quiz quiz) 
	{
		int rowCount=0;
		try
		{
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO Quiz_test VALUES(quiz_test_seq.nextVal,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, quiz.getName());
		preparedStatement.setInt(2, quiz.getTopics().getId());
		preparedStatement.setTime(3, Time.valueOf(quiz.getDurationTime()));
		preparedStatement.setDate(4, Date.valueOf(quiz.getExpiredDate()));
		preparedStatement.setInt(5, quiz.getStatus());
		preparedStatement.setInt(6, quiz.getAttendedCount());
		rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowCount;
	}
	
	 
	

}
