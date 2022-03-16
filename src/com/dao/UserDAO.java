package com.dao;

/**
 * @file Name : UserDAO.java
 * @project name : fashion_review
 * @package name : com.dao
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 회원정보 삽입, 삭제, 수정, 읽기에 대한 DB 프로시져를 실행하기 위한 기능을 전담하기 위한 Object
 */

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

	 /**
	    * @Method Name : insertUser
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Member 패키지의 member_insert 프로시져를 이용하여 회원 가입
	    */

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
	
	 /**
	    * @Method Name : updateUser
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Member 패키지의 member_all_update 프로시져를 통해 회원 정보 수정하기
	    */


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

	 /**
	    * @Method Name : getUser
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Member 패키지의 member_select_pw_member_id 프로시져를 사용하여 로그인시 아이디와 비밀번호 확인
	    */

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

	 /**
	    * @Method Name : getUserAll
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Member 패키지의 member_select_all_member_id 프로시져를 사용하여 한 id에 대한 모든 정보 가져오기
	    */

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

	   /**
	    * @Method Name : checkId
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : 회원가입 창에서 id값을 가져와 중복되는 id가 있는지 여부 파악
	    */

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