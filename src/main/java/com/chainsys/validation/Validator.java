package com.chainsys.validation;

import com.chainsys.customExceptions.DuplicateException;
import com.chainsys.dao.TopicsDAO;

public class Validator {

	public Boolean checkForTopic(String topicName) throws Exception {
		Boolean isFound = false;
		TopicsDAO topicsDAO = new TopicsDAO();
		if (topicsDAO.getTopics() == null) {
			isFound = true;
		} else {
			throw new DuplicateException("The " + topicName + "is already present");

		}
		return isFound;
	}

}
