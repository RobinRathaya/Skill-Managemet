package com.chainsys.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.chainsys.dao.StudentActionsDAO;
import com.chainsys.model.Student;
import com.chainsys.validation.Validator;

public class StudentService {
	public boolean addStudent(Student student) throws Exception {
		StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
		return studentActionsDAO.addStudentInfo(student);
	}

	public boolean updateStudent(Student student) throws Exception {
		StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
		return studentActionsDAO.updateStudentInfoById(student);
	}

	public Student getStudentInfoByName(Student student) throws Exception {
		StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
		return studentActionsDAO.getStudentInfoByName(student);
	}

	public Student getStudentInfoById(Student student) throws Exception {
		StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
		return studentActionsDAO.getStudentInfoById(student);
	}

	public List<Student> getAllStudentInfo() throws Exception {
		StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
		return studentActionsDAO.getAllStudentInfo();
	}

	public boolean deleteStudent(Student student) throws Exception {
		StudentActionsDAO studentInfoDAO = new StudentActionsDAO();
		return studentInfoDAO.deleteStudentInfoById(student);
	}

	public Boolean importFromExcel(String filePath, String fileName) throws Exception {
		try {
			Workbook workbook = WorkbookFactory.create(new File(filePath));
			Validator validator = new Validator();
			Sheet sheet = workbook.getSheetAt(0);
			Boolean isTrue = validator.checkForTemplate(sheet, fileName);
			if (isTrue) {
				StudentActionsDAO studentActionsDAO = new StudentActionsDAO();
				ArrayList<Student> studentList = new ArrayList<Student>();
				for (Row row : sheet) {
					if (row.getRowNum() != 0) {
						Student student = new Student();
						student.setName(row.getCell(0).toString());
						student.setEmail(row.getCell(1).toString());
						student.setPhonenumber(row.getCell(2).toString());
						student.setUserName(row.getCell(3).toString());
						student.setPhonenumber(row.getCell(4).toString());
						student.setCreatedBy(1);
						student.setCreatedDate(LocalDateTime.now());
						studentList.add(student);
					} else {
						continue;
					}
				}
				studentActionsDAO.addBatchOfStudents(studentList);
			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
}
