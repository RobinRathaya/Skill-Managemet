package com.chainsys.services;

import com.chainsys.customExceptions.DuplicateException;
import com.chainsys.dao.TopicsDAO;
import com.chainsys.model.Topics;
import com.chainsys.validation.Validator;

public class AddTopicService {
	public String addNewTopic(String topicName) throws DuplicateException {
		Topics topic = new Topics();
		Validator validate = new Validator();
		Boolean isFound;
		String message = null;
		try {
			isFound = validate.checkForTopic(topicName);
			if (isFound) {
				topic.setName(topicName);
				int statusId = TopicsDAO.addNewTopic(topic);
				if (statusId > 0) {
					message = "success";
				} else {
					message = "failed";
				}
			}
		} catch (Exception e) {
			throw new DuplicateException("The"+topicName+"is already present");
		}
		return message;
	}
}
