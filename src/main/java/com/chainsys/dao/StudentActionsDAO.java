package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chainsys.model.Student;
import com.chainsys.util.ConnectionUtil;
import com.chainsys.util.SendEmail;

/**
 * methods to maintain student management
 * 
 *
 */
public class StudentActionsDAO {
	Logger log=Logger.getLogger("CreateQuizDAO");
	/**
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentInfo(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO trn_student_info(id,name,email_id,phone_number,user_name,password,created_by,created_date) VALUES(student_id_seq.nextval,?,?,?,?,?,?,?)";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getPhonenumber());
			preparedStatement.setString(4, student.getUserName());
			preparedStatement.setString(5, student.getPassword());
			preparedStatement.setInt(6, student.getCreatedBy());
			preparedStatement.setTimestamp(7, Timestamp.valueOf(student.getCreatedDate()));
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				SendEmail.sendMail(student);
				success = true;
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return success;
	}

	/**
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public Student getStudentInfoByName(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		Student studentData = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where name=? and status=1";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					studentData = new Student();
					studentData.setId(resultSet.getInt("id"));
					studentData.setName(resultSet.getString("name"));
					studentData.setEmail(resultSet.getString("email_id"));
					studentData.setPhonenumber(resultSet.getString("phone_number"));
					studentData.setUserName(resultSet.getString("user_name"));
					studentData.setPassword(resultSet.getString("password"));
				}
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return studentData;
	}

	/**
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public Student getStudentInfoById(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		Student studentInfo = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where id=? and status=1";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					studentInfo = new Student();
					studentInfo.setId(resultSet.getInt("id"));
					studentInfo.setName(resultSet.getString("name"));
					studentInfo.setEmail(resultSet.getString("email_id"));
					studentInfo.setPhonenumber(resultSet.getString("phone_number"));
					studentInfo.setUserName(resultSet.getString("user_name"));
					studentInfo.setPassword(resultSet.getString("password"));
				}
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return studentInfo;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Student> getAllStudentInfo() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		List<Student> studentInfoList = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where status=1";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					Student studentInfo = new Student();
					studentInfo.setId(resultSet.getInt("id"));
					studentInfo.setName(resultSet.getString("name"));
					studentInfo.setEmail(resultSet.getString("email_id"));
					studentInfo.setPhonenumber(resultSet.getString("phone_number"));
					studentInfo.setUserName(resultSet.getString("user_name"));
					studentInfo.setPassword(resultSet.getString("password"));
					studentInfoList.add(studentInfo);
				}
			}
		} catch (Exception e) {
			log.error("Ëxception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return studentInfoList;
	}

	/**
	 * 
	 * @param studentInfoModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateStudentInfoById(Student studentInfoModel) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE trn_student_info SET name=?,email_id=?,phone_number=?,user_name=?,password=?,modified_by=?,modified_date=? WHERE id=?";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentInfoModel.getName());
			preparedStatement.setString(2, studentInfoModel.getEmail());
			preparedStatement.setString(3, studentInfoModel.getPhonenumber());
			preparedStatement.setString(4, studentInfoModel.getUserName());
			preparedStatement.setString(5, studentInfoModel.getPassword());
			preparedStatement.setInt(6, studentInfoModel.getModifiedBy());
			preparedStatement.setTimestamp(7, Timestamp.valueOf(studentInfoModel.getModifiedDate()));
			preparedStatement.setInt(8, studentInfoModel.getId());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				success = true;
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return success;
	}

	/**
	 * 
	 * @param studentInfoModel
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStudentInfoById(Student studentInfoModel) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE trn_student_info SET status=0 WHERE id=?";
			log.debug("sql"+sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentInfoModel.getId());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				success = true;
			}
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		
		return success;
	}
	public int addBatchOfStudents(ArrayList<Student> studentList) {

			try {
			Connection connection = ConnectionUtil.getConnection();
			String query = "INSERT INTO trn_student_info(id,name,email_id,phone_number,user_name,password,created_by,created_date) VALUESVALUES(student_id_seq.nextval,?,?,?,?,?,?,?)";
			log.debug("sql"+query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			for (Student student : studentList) {
				preparedStatement.setString(1, student.getName());
				preparedStatement.setString(2, student.getEmail());
				preparedStatement.setString(3, student.getPhonenumber());
				preparedStatement.setString(4, student.getUserName());
				preparedStatement.setString(5, student.getUserName());
				preparedStatement.setInt(6, student.getCreatedBy());
				preparedStatement.setTimestamp(7, Timestamp.valueOf(student.getModifiedDate()));
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (Exception e) {
			log.error("Exception catched"+e.getMessage());
			e.printStackTrace();
		}
		
		
		return 0;
	
	}
	

}
