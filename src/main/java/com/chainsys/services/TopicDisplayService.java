package com.chainsys.services;

import java.util.ArrayList;

import com.chainsys.dao.TopicsDAO;
import com.chainsys.model.Topics;

public class TopicDisplayService {

	public ArrayList<Topics> displayTopics() throws Exception
	{
		TopicsDAO topicsDAO = new TopicsDAO();
		ArrayList<Topics> topicsList=topicsDAO.getTopics();
		return topicsList;
	}

}
