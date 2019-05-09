package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.model.Questions;
import com.chainsys.util.ConnectionUtil;

public class QuestionsDAO {

	public int addNewQuestion(Questions questions) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO questions VALUES(QUIZEVAL_QUES_SEQ.nextVal,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, questions.getQuestion());
		preparedStatement.setInt(2, questions.getTopic().getId());
		preparedStatement.setInt(3, questions.getLevel().getId());
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}

	public int deleteQuestion(String question) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "DELETE FROM TABLE quiz_questions WHERE ques_id = (SELECT ques_id FROM quiz_questions WHERE question = ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, question);
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}

	public List<Questions> viewByTopic(String topic) throws Exception {
		List<Questions> listOfQues = new ArrayList<Questions>();
		Questions question = null;
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT q.ques_id,q.question,q.topic_id,q.level_id,topic_name,level_name FROM quiz_questions q"
				+ "INNER JOIN quiz_topics t ON (t.topic_id = q.topic_id AND t.topic_name = ? ) AS topic_name"
				+ "INNER JOIN quiz_levels l ON (q.level_id = l.level_id) AS level";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, topic);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			question = new Questions();
			question.setId(resultSet.getInt("q.ques_id"));
			question.setQuestion((resultSet.getString("q.question")));
			question.getTopic().setId(resultSet.getInt("q.topic_id"));
			question.getTopic().setName(resultSet.getString("topic_name"));
			question.getLevel().setId(resultSet.getInt("q.level_id"));
			question.getLevel().setType(resultSet.getString("level_name"));
			listOfQues.add(question);
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);

		return listOfQues;
	}

	public List<Questions> viewByLevel(String level) throws Exception {
		List<Questions> listOfQues = new ArrayList<Questions>();
		Questions question = null;
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT q.ques_id,q.question,q.topic_id,q.level_id,topic_name,level_name FROM quiz_questions q"
				+ "INNER JOIN quiz_topics t ON (t.topic_id = q.topic_id) AS topic_name"
				+ "INNER JOIN quiz_levels l ON (q.level_id = l.level_id AND l.level_name = ?) AS level";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, level);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			question = new Questions();
			question.setId(resultSet.getInt("q.ques_id"));
			question.setQuestion((resultSet.getString("q.question")));
			question.getTopic().setId(resultSet.getInt("q.topic_id"));
			question.getTopic().setName(resultSet.getString("topic_name"));
			question.getLevel().setId(resultSet.getInt("q.level_id"));
			question.getLevel().setType(resultSet.getString("level_name"));
			listOfQues.add(question);		
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		return listOfQues;
	}

}
