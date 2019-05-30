package com.chainsys.services;

import com.chainsys.dao.LoginDAO;
import com.chainsys.model.Student;

public class LoginService {

	
	public int isLogin(Student student) throws Exception
	{
		LoginDAO loginDAO=new LoginDAO();
		return loginDAO.success(student);
	}
	
}
