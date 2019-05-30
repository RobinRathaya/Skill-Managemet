package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.AuthenticationException;

import com.chainsys.model.Student;
import com.chainsys.util.ConnectionUtil;

public class LoginDAO {

	public int success(Student student) throws Exception {
		Connection connection;
		int studentId=0;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select id from trn_student_info where user_name=? and password=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getUserName());
			ps.setString(2, student.getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentId=Integer.parseInt(rs.getString("id"));
			}
		} catch (AuthenticationException e) {
			throw new AuthenticationException("Invalid username and password");
		}
		return studentId;
	}
}
