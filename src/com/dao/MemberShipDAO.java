package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.vo.MemberShipVO;
import com.vo.UserVO;

public class MemberShipDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource dataFactory;
	
	public MemberShipDAO() {		
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberShipVO> selectMemberShip(String id) {
		ArrayList<MemberShipVO> list = new ArrayList<>();
		MemberShipVO membership = new MemberShipVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * from membership where user_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				membership.setId(rs.getString("user_id"));
				membership.setGrade(rs.getString("grade"));
				membership.setPost_count(rs.getInt("post_count"));
				membership.setPoint(rs.getInt("m_point"));
				
				
				list.add(membership);
				System.out.println(membership.toString());
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
