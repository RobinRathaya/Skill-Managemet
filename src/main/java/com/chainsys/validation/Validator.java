package com.chainsys.validation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.chainsys.customExceptions.DuplicateException;
import com.chainsys.customExceptions.InvalidFileExceptions;
import com.chainsys.dao.TopicsDAO;

public class Validator {

	public Boolean checkForTopic(String topicName) throws DuplicateException {
		Boolean isNotContain = false;
		TopicsDAO topicsDAO = new TopicsDAO();
		try {
			if (!topicsDAO.getTopics().contains(topicName)) {
				isNotContain = true;
			} else {
				throw new DuplicateException("The " + topicName + " is already present");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isNotContain;
	}
	public Boolean checkForTemplate(Sheet sheet,String fileName) throws InvalidFileExceptions {
		Boolean isTrue = false;
		Row row = sheet.getRow(0);
		if ((row.getCell(0)).toString().equalsIgnoreCase("Questions")
				&& (row.getCell(1)).toString().equalsIgnoreCase("options")
				&& (row.getCell(2)).toString().equalsIgnoreCase("answer")) {
			isTrue = true;
		} else {
			throw new InvalidFileExceptions("The File :" + fileName + "is in Different Format");
		}
		return isTrue;
	}
}
