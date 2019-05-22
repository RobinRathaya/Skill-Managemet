package com.chainsys.dao;

/**
 * performs topics related methods
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.model.Topics;
import com.chainsys.util.ConnectionUtil;

/**
 * 
 * 
 *
 */
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
/**
 * 
 * @param topicId
 * @return
 * @throws Exception
 */
	public String selectTopicNameById(int topicId) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT topic_name FROM quiz_topics WHERE topic_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, topicId);
		ResultSet resultSet = preparedStatement.executeQuery();
		String topicName = "";
		while (resultSet.next()) {
			topicName = resultSet.getString("topic_name");
		}
		ConnectionUtil.close(connection, preparedStatement, null);
		return topicName;
	}
/**
 * 
 * @param newTopicName
 * @param oldTopicName
 * @return
 * @throws Exception
 */
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
/**
 * 
 * @return
 * @throws Exception
 */
	public ArrayList<Topics> getTopics() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "SELECT topic_id,topic_name FROM quiz_topics";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Topics> topicsList = new ArrayList<>();
		while (resultSet.next()) {
			Topics topics = new Topics();
			topics.setId(Integer.parseInt(resultSet.getString("topic_id")));
			topics.setName(resultSet.getString("topic_name"));
			topicsList.add(topics);
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		return topicsList;
	}
}
