package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.model.Questions;
import com.chainsys.model.Topics;
import com.chainsys.util.ConnectionUtil;

/**
 * Represents a question added in a topic
 * A topic can have many questions
 * 
 *
 */
public class QuestionsDAO {

	/**
	 * Create a new question 
	 * question object includes question id,question,topic id,options and answers
	 * @param question
	 * @return the row count after executing query
	 * @throws Exception
	 */
	public int addNewQuestion(Questions question) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO quiz_questions VALUES(QUIZEVAL_QUES_SEQ.nextVal,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, question.getQuestion());
		preparedStatement.setInt(2, question.getTopic().getId());
		preparedStatement.setString(3, question.getOptions());
		preparedStatement.setString(4, question.getAnswers());
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}
	
	/**
	 * Creates a list of question
	 * Questions are imported from excel sheet
	 * @param questionsList a ArrayList of questions included members of question class
	 * @return rowCount the count of effect rows
	 */
	public int addBatchOfQueestions(ArrayList<Questions> questionsList) {

		try {
			Connection connection = ConnectionUtil.getConnection();
			String query = "INSERT INTO quiz_questions VALUES(QUIZEVAL_QUES_SEQ.nextVal,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			for (Questions question : questionsList) {
				preparedStatement.setString(1, question.getQuestion());
				preparedStatement.setInt(2, question.getTopic().getId());
				preparedStatement.setString(3, question.getOptions());
				preparedStatement.setString(4, question.getAnswers());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Questions> viewByTopic(String topic) throws Exception {
		List<Questions> listOfQues = new ArrayList<Questions>();
		Questions question = null;
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT q.ques_id,q.question,q.topic_id,q.level_id,topic_name,level_name FROM quiz_questions q INNER JOIN quiz_topics t ON (t.topic_id = q.topic_id AND t.topic_name = ? ) AS topic_name INNER JOIN quiz_levels l ON (q.level_id = l.level_id) AS level";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, topic);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			question = new Questions();
			question.setId(resultSet.getInt("q.ques_id"));
			question.setQuestion((resultSet.getString("q.question")));
			question.getTopic().setId(resultSet.getInt("q.topic_id"));
			question.getTopic().setName(resultSet.getString("topic_name"));
			listOfQues.add(question);
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		return listOfQues;
	}

	public ArrayList<Questions> viewAll() throws Exception {
		ArrayList<Questions> listOfQues = new ArrayList<Questions>();
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT q.ques_id AS quesid,q.question AS ques,q.topic_id AS topicid,t.topic_name AS topicname,q.options AS options,q.answer as answer FROM quiz_questions q INNER JOIN quiz_topics t ON (t.topic_id = q.topic_id)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Questions question = new Questions();
			Topics topic = new Topics();
			question.setId(resultSet.getInt("quesid"));
			question.setQuestion((resultSet.getString("ques")));
			topic.setId(resultSet.getInt("topicid"));
			topic.setName(resultSet.getString("topicname"));
			question.setTopic(topic);
			question.setOptions(resultSet.getString("options"));
			question.setAnswers((resultSet.getString("answer")));
			listOfQues.add(question);
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		return listOfQues;
	}
}
