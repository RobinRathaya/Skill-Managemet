package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.model.Topics;
import com.chainsys.util.ConnectionUtil;

public class TopicsDAO {
	public static int addNewTopic(Topics topic) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO quiz_topics VALUES(QUIZEVAL_TOPICID_SEQ.nextVal,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, topic.getName());
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}

	public int deleteTopic(String topicName) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "DELETE FROM TABLE quiz_topics WHERE topic_id = (SELECT topic_id FROM quiz_topics WHERE topic_name = ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, topicName);
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}

	public int updateTopicName(String newTopicName, String oldTopicName) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "UPDATE quiz_topics SET topic_name = ? WHERE topic_id = (SELECT topic_id FROM quiz_topics WHERE topic_name = ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, newTopicName);
		preparedStatement.setString(2, oldTopicName);
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;

	}

	public ArrayList<String> getTopics() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT topic_name FROM quiz_topics";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> topicsList = new ArrayList<>();
		while (resultSet.next()) {
			topicsList.add(resultSet.getString("topic_name"));
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		return topicsList;
	}
}
