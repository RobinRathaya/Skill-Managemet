package com.chainsys.services;

import java.util.List;

import com.chainsys.dao.StudentActionsDAO;
import com.chainsys.model.Student;

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



}
