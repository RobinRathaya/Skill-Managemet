package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.model.Levels;
import com.chainsys.util.ConnectionUtil;

public class LevelsDAO {
	public int addNewLevel(Levels level) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		String query = "INSERT INTO levels VALUES(QUIZEVAL_LVLID_SEQ.nextVal,?)";
		PreparedStatement preparedStatement = connection
				.prepareStatement(query);
		preparedStatement.setString(1, level.getType());
		int rowCount = preparedStatement.executeUpdate();
		ConnectionUtil.close(connection, preparedStatement, null);
		return rowCount;
	}

}
