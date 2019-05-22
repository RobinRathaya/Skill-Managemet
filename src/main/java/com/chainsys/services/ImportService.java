package com.chainsys.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.chainsys.dao.QuestionsDAO;
import com.chainsys.dao.TopicsDAO;
import com.chainsys.model.Questions;
import com.chainsys.model.Topics;
import com.chainsys.validation.Validator;

public class ImportService {

	public Boolean importToExcel(String filePath, int topicId, String fileName) throws Exception {
		try {
			Workbook workbook = WorkbookFactory.create(new File(filePath));
			Validator validator = new Validator();
			Sheet sheet = workbook.getSheetAt(0);
			Boolean isTrue = validator.checkForTemplate(sheet, fileName);
			if (isTrue) {
				TopicsDAO topicsDAO = new TopicsDAO();
				Topics topic = new Topics();
				topic.setId(topicId);
				topic.setName(topicsDAO.selectTopicNameById(topicId));
				ArrayList<Questions> questionList = new ArrayList<Questions>();
				QuestionsDAO questionsDAO = new QuestionsDAO();
				for (Row row : sheet) {
					if (row.getRowNum() != 0) {
						Questions question = new Questions();
						question.setTopic(topic);
						question.setQuestion(row.getCell(0).toString());
						question.setOptions(row.getCell(1).toString());
						question.setAnswers(row.getCell(2).toString());
						questionList.add(question);
					} else {
						continue;
					}
				}
				System.out.println(questionList);
				questionsDAO.addBatchOfQueestions(questionList);
			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
