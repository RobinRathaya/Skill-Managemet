package com.chainsys.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.model.Quiz;
import com.chainsys.model.Topics;
import com.chainsys.util.ConnectionUtil;

public class CreateQuizDAO {

	public int createQuiz(Quiz quiz) {
		int rowCount = 0;
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public List<Quiz> upcomingQuiz() throws SQLException {
		List<Quiz> upcomingQuizList = new ArrayList<Quiz>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select q.ID as qid,q.QUIZ_NAME as qname,qtop.TOPIC_ID as topicid,qtop.TOPIC_NAME as topicname,q.TEST_DURATION as duration,q.EXPIRED_DATE as expired_date,q.ATTENDED_COUNT as count from quiz_test q  inner join quiz_topics qtop on q.topic=qtop.topic_id where q.EXPIRED_DATE>=? and q.status=1";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Quiz quiz = new Quiz();
				quiz.setId(resultSet.getInt("qid"));
				quiz.setName(resultSet.getString("qname"));
				Topics topics = new Topics();
				topics.setId(resultSet.getInt("topicid"));
				topics.setName(resultSet.getString("topicname"));
				quiz.setTopics(topics);
				quiz.setDurationTime(resultSet.getTime("duration").toLocalTime());
				quiz.setExpiredDate(resultSet.getDate("expired_date").toLocalDate());
				quiz.setAttendedCount(resultSet.getInt("count"));
				upcomingQuizList.add(quiz);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return upcomingQuizList;

	}

	
	public List<Quiz> completedQuiz() throws SQLException {
		List<Quiz> upcomingQuizList = new ArrayList<Quiz>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select q.ID as qid,q.QUIZ_NAME as qname,qtop.TOPIC_ID as topicid,qtop.TOPIC_NAME as topicname,q.TEST_DURATION as duration,q.EXPIRED_DATE as expired_date,q.ATTENDED_COUNT as count from quiz_test q  inner join quiz_topics qtop on q.topic=qtop.topic_id where q.EXPIRED_DATE<=? and q.status=0";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Quiz quiz = new Quiz();
				quiz.setId(resultSet.getInt("qid"));
				quiz.setName(resultSet.getString("qname"));
				Topics topics = new Topics();
				topics.setId(resultSet.getInt("topicid"));
				topics.setName(resultSet.getString("topicname"));
				quiz.setTopics(topics);
				quiz.setDurationTime(resultSet.getTime("duration").toLocalTime());
				quiz.setExpiredDate(resultSet.getDate("expired_date").toLocalDate());
				quiz.setAttendedCount(resultSet.getInt("count"));
				upcomingQuizList.add(quiz);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return upcomingQuizList;

	}
	
	
	public boolean cancelQuiz(Quiz quiz) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean success=false;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "update quiz_test set status=? where id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,quiz.getStatus());
			preparedStatement.setInt(2,quiz.getId());
			int count = preparedStatement.executeUpdate();
			if(count>0)
			{
				success=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return success;

	}
	
}
