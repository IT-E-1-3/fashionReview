package com.dao;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.vo.UserVO;

import oracle.jdbc.OracleTypes;

import com.controller.DBmanager.*;

public class UserDAO {
	private UserDAO() {
	}// 싱글턴 패턴

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	public void insertUser(UserVO userVO) throws SQLException {

		Connection conn = null;

		String runSP = "{ call member_pack.member_insert(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, userVO.getId());
			callableStatement.setString(2, userVO.getPw());
			callableStatement.setString(3, userVO.getName());
			callableStatement.setString(4, userVO.getEmail());
			callableStatement.setString(5, userVO.getPhone());
			callableStatement.setInt(6, userVO.getAge());
			callableStatement.setString(7, userVO.getGender());
			callableStatement.setInt(8, userVO.getHeight());
			callableStatement.setInt(9, userVO.getWeight());

			callableStatement.executeUpdate();

			conn.close();
			callableStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateUser(UserVO userVO) throws SQLException {

		Connection conn = null;

		String runSP = "{call member_pack.member_all_update(?,?,?,?,?,?,?,?,?)}";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, userVO.getId());
			callableStatement.setString(2, userVO.getPw());
			callableStatement.setString(3, userVO.getName());
			callableStatement.setString(4, userVO.getEmail());
			callableStatement.setString(5, userVO.getPhone());
			callableStatement.setInt(6, userVO.getAge());
			callableStatement.setString(7, userVO.getGender());
			callableStatement.setInt(8, userVO.getHeight());
			callableStatement.setInt(9, userVO.getWeight());

			callableStatement.executeUpdate();

			conn.close();
			callableStatement.close();

		} catch (SQLException sqle) {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public UserVO getUser(String id) {

		UserVO userVO = new UserVO();
		Connection conn = null;

		String runSP = "{ call member_pack.member_select_pw_user_id(?, ?) }";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, id);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

			try {
				callableStatement.executeQuery();

				String pw = callableStatement.getString(2);

				userVO.setId(id);
				userVO.setPw(pw);

				conn.close();
				callableStatement.close();
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

	public UserVO getUserAll(String id) {

		UserVO userVO = null;
		Connection conn = null;

		String runSP = "{ call member_pack.member_select_all_user_id(?, ?) }";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, id);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.execute();

				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

				userVO = new UserVO();
				while (resultSet.next()) {
					userVO.setId(resultSet.getString(1));
					userVO.setPw(resultSet.getString(2));
					userVO.setName(resultSet.getString(3));
					userVO.setEmail(resultSet.getString(4));
					userVO.setPhone(resultSet.getString(5));
					userVO.setAge(resultSet.getInt(6));
					userVO.setGender(resultSet.getString(7));
					userVO.setHeight(resultSet.getInt(8));
					userVO.setWeight(resultSet.getInt(9));
				}
				conn.close();
				callableStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

	public boolean checkId(String id) {
		Connection conn = DBManager.getConnection();
		int count = 1;
		try {
			String query = "select count(*) as count from member where user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 0) {
				return true;
			}

			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}