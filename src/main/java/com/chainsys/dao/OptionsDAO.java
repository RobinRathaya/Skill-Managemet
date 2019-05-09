package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.chainsys.model.Options;
import com.chainsys.util.ConnectionUtil;

public class OptionsDAO {

	public int addOptions(Options options) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO quiz_options VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, options.getQuesId());
		int i = 0;
		for (String option : options.getOptions()) {

			preparedStatement.setString(++i, option);
		}
		preparedStatement.setString(++i, options.getAnswer());
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}
	
	public ArrayList<String> displayOptions(int questionId)
	{
		ArrayList<String> options=new ArrayList<>();
		
		return null;
		
	}

}
