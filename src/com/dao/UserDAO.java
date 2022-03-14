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

//	private Connection conn;
//	private Statement stmt;
//	private ResultSet rs;
//	private DataSource dataFactory;
//	UserVO user = new UserVO();

	public void insertUser(UserVO userVO) throws SQLException {

//		UserVO userVO = null;
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
			System.out.println("성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	public void updateUser(UserVO userVO) throws SQLException{
		
		Connection conn = null;
		
		String runSP = "{call member_pack.member_all_update(?,?,?,?,?,?,?,?,?)}";

		try{
			conn=DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			//conn.setAutoCommit(true); //�ڵ� Ŀ���� false�� ����
//			System.out.println(query);			
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

//			pstmt=conn.createStatement();
//			pstmt.executeUpdate(query);
//			conn.commit();
			
			System.out.println("업데이트 완료");
		}catch(SQLException sqle) {
			conn.rollback();
		}catch(Exception e){
            e.printStackTrace();
        }
            
	}
//	
//	public ArrayList<UserVO> list(){
//		ArrayList<UserVO> list=new ArrayList<UserVO>();
//		try {
//			conn=dataFactory.getConnection();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			String query="SELECT * from users";
//			System.out.println(query);
//			stmt=conn.createStatement();
//			rs=stmt.executeQuery(query); //ResultSet�� ���� ����
//			
//			while(rs.next()) {
//				String id=rs.getString("user_id");
//				String pw=rs.getString("pw");
//				UserVO data=new UserVO(id, pw); //getInstance�� �ٲٱ� new ������ ����?
//				
//				list.add(data);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

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

				System.out.println();
				System.out.println("id: " + id);
				System.out.println("pw: " + pw);
				System.out.println("성공");

				userVO.setId(id);
				userVO.setPw(pw);
				
				System.out.println(userVO.toString());




			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			DBManager.close(conn, callableStatement);
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

//				while (resultSet.next()) {
//					System.out.print(resultSet.getString(1) + "   ");
//					System.out.print(resultSet.getString(2) + "   ");
//					System.out.print(resultSet.getString(3) + "   ");
//					System.out.print(resultSet.getString(4) + "   ");
//					System.out.print(resultSet.getString(5) + "   ");
//					System.out.print(resultSet.getInt(6) + "   ");
//					System.out.print(resultSet.getString(7) + "   ");
//					System.out.print(resultSet.getInt(8) + "   ");
//					System.out.println(resultSet.getInt(9));
//				}


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

			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			DBManager.close(conn, callableStatement);
		}
		return userVO;
	}
}