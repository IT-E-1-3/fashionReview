package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.vo.UserVO;

//DB Connection�� �ִ°�
//DAO���� DB�� ���õ� �޼��带 ó���Ѵ�.
public class UserDAO {
	
	private static UserDAO instance;
	
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserDAO getInstance() {
		if(instance==null)
			instance=new UserDAO();
		return instance;
	}
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DataSource dataFactory;
	UserVO user = new UserVO();
	
	public void insertUser(UserVO user) throws SQLException{
		PreparedStatement pstmt=null;
		
		try{
			conn=dataFactory.getConnection();
			//conn.setAutoCommit(true); //�ڵ� Ŀ���� false�� ����
			
			pstmt=conn.prepareStatement("insert into users (user_id, pw, email, phone, age, gender, height, weight) values(?,?,?,?,?,?,?,?)");
//			stmt=conn.createStatement();
			
			pstmt.setString(1,user.getId() );
			pstmt.setString(2,  user.getPw());
			pstmt.setString(3,  user.getEmail());
			pstmt.setString(4,  user.getPhone());
			pstmt.setInt(5,  user.getAge());
			pstmt.setString(6,  user.getGender());
			pstmt.setInt(7,  user.getHeight());
			pstmt.setInt(8,  user.getWeight());
			
			
			pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("회원가입 완료");
			
			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
		}catch(SQLException sqle) {
			conn.rollback();
		}catch(Exception e){
            e.printStackTrace();
        }
            
	}
	public void UpdateUser(UserVO userVO) throws SQLException{
		PreparedStatement pstmt=null;
		
		try{
			conn=dataFactory.getConnection();
			//conn.setAutoCommit(true); //�ڵ� Ŀ���� false�� ����
			String query = "update users set pw=?, email=?, age=?, gender=?, height=?, weight=? where user_id=?";
			pstmt=conn.prepareStatement(query);
					
			
			System.out.println(userVO);
			pstmt.setString(1,  userVO.getPw());
			pstmt.setString(2,  userVO.getEmail());
			pstmt.setInt(3,  userVO.getAge());
			pstmt.setString(4,  userVO.getGender());
			pstmt.setInt(5,  userVO.getHeight());
			pstmt.setInt(6,  userVO.getWeight());
			pstmt.setString(7,userVO.getId());
			
//			pstmt=conn.createStatement();
			pstmt.executeUpdate();
			conn.commit();
			
			
			System.out.println("업데이트 완료");
			
			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
		}catch(SQLException sqle) {
//			conn.rollback();
			sqle.printStackTrace();
		}catch(Exception e){
            e.printStackTrace();
        }
            
	}
	
	public ArrayList<UserVO> list(){
		ArrayList<UserVO> list=new ArrayList<UserVO>();
		try {
			conn=dataFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String query="SELECT * from users";
			System.out.println(query);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query); //ResultSet�� ���� ����
			
			while(rs.next()) {
				String id=rs.getString("user_id");
				String pw=rs.getString("pw");
				UserVO data=new UserVO(id, pw); //getInstance�� �ٲٱ� new ������ ����?
				
				list.add(data);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public UserVO getUser(String id) {
		UserVO userVO= null;
	    String sql = "select * from users where user_id=?";	     
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			conn=dataFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	      
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, id);
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	    	  userVO = new UserVO();
	    	  userVO.setId(rs.getString("user_id"));
	    	  userVO.setPw(rs.getString("pw"));
	    	  userVO.setEmail(rs.getString("email"));
	    	  userVO.setAge(rs.getInt("age"));
	    	  userVO.setGender(rs.getString("gender"));
	    	  userVO.setHeight(rs.getInt("height"));
	    	  userVO.setWeight(rs.getInt("weight"));             

	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      //DBConnection.close(connn, pstmt, rs);
	    	
	    }
	    return userVO;
	  }
	}
	
	
	

