package com.chainsys.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chainsys.model.Questions;
import com.chainsys.model.Quiz;
import com.chainsys.model.Score;
import com.chainsys.model.Topics;
import com.chainsys.util.ConnectionUtil;

public class CreateQuizDAO {
	Logger log=Logger.getLogger("CreateQuizDAO");

	public int createQuiz(Quiz quiz) {
		int rowCount = 0;
		try {
			Connection connection = ConnectionUtil.getConnection();
			String query = "INSERT INTO Quiz_test VALUES(quiz_test_seq.nextVal,?,?,?,?,?,?)";
			log.debug("Query"+query);
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
			log.error("Exception catched"+e.getMessage());
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
			log.debug("Query"+query);
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
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return upcomingQuizList;

	}

	
	public Quiz getQuizById(Quiz quizDetails) throws SQLException {
		List<Quiz> upcomingQuizList = new ArrayList<Quiz>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Quiz quiz = new Quiz();
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select q.ID as qid,q.QUIZ_NAME as qname,qtop.TOPIC_ID as topicid,qtop.TOPIC_NAME as topicname,q.TEST_DURATION as duration,q.EXPIRED_DATE as expired_date,q.ATTENDED_COUNT as count from quiz_test q  inner join quiz_topics qtop on q.topic=qtop.topic_id where  q.status=1 and q.id=?";
			log.debug("Query"+query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,quizDetails.getId() );
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
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
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return quiz;

	}

	
	public List<Quiz> completedQuiz() throws SQLException {
		List<Quiz> upcomingQuizList = new ArrayList<Quiz>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select q.ID as qid,q.QUIZ_NAME as qname,qtop.TOPIC_ID as topicid,qtop.TOPIC_NAME as topicname,q.TEST_DURATION as duration,q.EXPIRED_DATE as expired_date,q.ATTENDED_COUNT as count from quiz_test q  inner join quiz_topics qtop on q.topic=qtop.topic_id where q.EXPIRED_DATE<=? and q.status=0";
			log.debug("Query"+query);
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
			log.error("Exception catched"+e.getMessage());
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
			log.debug("Query"+query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,quiz.getStatus());
			preparedStatement.setInt(2,quiz.getId());
			int count = preparedStatement.executeUpdate();
			if(count>0)
			{
				success=false;
			}
			
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return success;

	}
	
	public List<Questions> getQuizQuestion(Quiz quiz) throws SQLException {
		List<Questions> questionList = new ArrayList<Questions>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select ques.ques_id as quesid,ques.QUESTION as question,ques.OPTIONS as options,qto.TOPIC_NAME as topicname,ques.answer as answer from  quiz_test qte inner join quiz_topics qto on qte.topic = qto.topic_id inner join quiz_questions ques on  ques.topic_id=qto.topic_id where qte.id=?";
			log.debug("Query"+query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, quiz.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Topics topics=new Topics();
				topics.setName(resultSet.getString("topicname"));
				Questions questions=new Questions();
				questions.setId(Integer.parseInt(resultSet.getString("quesid")));
				questions.setQuestion(resultSet.getString("question"));
				questions.setOptions(resultSet.getString("options"));
				questions.setAnswers(resultSet.getString("answer"));
				questions.setTopic(topics);
				questionList.add(questions);
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return questionList;

	}
	
	
	public int addScore(Score score) {
		int rowCount = 0;
		try {
			Connection connection = ConnectionUtil.getConnection();
			String query = "INSERT INTO trn_score(score_id,quiz_id,student_id,correct,incorrect,created_by,created_date) VALUES(score_id_seq.nextVal,?,?,?,?,?,?)";
			log.debug("Query"+query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, score.getQuiz().getId());
			preparedStatement.setInt(2, score.getStudent().getId());
			preparedStatement.setInt(3, score.getCorrect());
			preparedStatement.setInt(4, score.getInCorrect());
			preparedStatement.setInt(5, score.getCreatedBy());
			preparedStatement.setTimestamp(6, Timestamp.valueOf(score.getCreatedDate()));
			rowCount = preparedStatement.executeUpdate();
			if(rowCount>0)
			{
				rowCount=updateTestAttendedCount(score);
			}
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		}
		return rowCount;
	}
	
	
	public int updateTestAttendedCount(Score score) {
		int rowCount = 0;
		try {
			Connection connection = ConnectionUtil.getConnection();
			String query = "update quiz_test set attended_count=attended_count+1 where id=?";
			log.debug("Query"+query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, score.getQuiz().getId());
			rowCount = preparedStatement.executeUpdate();			
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public Score getTestScoreByQuizId(Score scoreDetails) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Score score=new Score();
		try {
			connection = ConnectionUtil.getConnection();
			String query = "select t.quiz_name,qt.topic_name,s.correct,s.incorrect from trn_score s inner join quiz_test t on s.quiz_id=t.id inner join quiz_topics qt on qt.topic_id=t.topic where s.quiz_id=?";
			log.debug("Query"+query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, scoreDetails.getQuiz().getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Quiz quiz=new Quiz();
				quiz.setName(resultSet.getString("quiz_name"));
				score.setQuiz(quiz);
				Topics topics=new Topics();
				topics.setName(resultSet.getString("topic_name"));
				score.setTopics(topics);
				score.setCorrect(resultSet.getInt("correct"));
				score.setInCorrect(resultSet.getInt("incorrect"));
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return score;

	}
	
}
