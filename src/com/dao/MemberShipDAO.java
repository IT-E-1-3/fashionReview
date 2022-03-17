package com.dao;

/**
 * @file Name : MemberShipDAO.java
 * @project name : fashion_review
 * @package name : com.dao
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 멤버십 정보 삽입, 삭제, 수정, 읽기에 대한 DB 프로시져를 실행하기 위한 기능을 전담하기 위한 Object (멤버십 정보는 회원과 연동되어 있어 수정, 삭제, 삽입은 불가하고 읽기만 가능)
 */

import java.net.URLEncoder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.vo.MemberShipVO;
import com.vo.UserVO;

import oracle.jdbc.OracleTypes;

import com.controller.DBmanager.*;


public class MemberShipDAO {
   private Connection conn;
   private PreparedStatement pstmt;
//   private ResultSet rs;
//   private DataSource dataFactory;

   private MemberShipDAO() {
   }// 싱글턴 패턴

   private static MemberShipDAO instance = new MemberShipDAO();

   public static MemberShipDAO getInstance() {
      return instance;
   }
   
   /**
    * @Method Name : getMemberShipALL
    * @작성일 : 2022.03.14
    * @작성자 : 김정휴, 심다혜
    * @Method 설명 : MemberShip 패키지의 membership_select_all_member_id 프로시저로 해당 id에 대한 멤버십 정보 가져오기
    */

	public MemberShipVO getMemberShipALL(String id) {

		MemberShipVO membershipVO = null;
		Connection conn = null;

		String runSP = "{ call membership_pack.membership_select_all_member_id(?, ?) }";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, id);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.execute();

				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

				membershipVO = new MemberShipVO();
				while (resultSet.next()) {
					
					String tId = resultSet.getString(1);
					String tGrade = resultSet.getString(2);
					int tPostCount = resultSet.getInt(3);
					int tMPoint = resultSet.getInt(5);

					membershipVO.setId(tId);
					membershipVO.setGrade(tGrade);
					membershipVO.setPost_count(tPostCount);
					membershipVO.setReply_count(resultSet.getInt(4));
					membershipVO.setPoint(tMPoint);

					
					resultSet.close();
					callableStatement.close();
					conn.close();

				}

			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membershipVO;
	}

	// TOP 5 of m_point view
	   public ArrayList<MemberShipVO> selectRankers() {
	      ArrayList<MemberShipVO> rankerList = new ArrayList<MemberShipVO>();
	      try {

	         // sql --> table --> view
	         String query = "SELECT * from v_2";
	         System.out.println(query);
	         // DHCP
	         conn = DBManager.getConnection();
	         pstmt = conn.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            String user_id = rs.getString("c_1");
	            String grade = rs.getString("c_2");
	            int post_count = rs.getInt("c_3");
	            int reply_count = rs.getInt("c_4");
	            int point = rs.getInt("c_5");
	            
	            MemberShipVO ranker = new MemberShipVO();
	            ranker.setId(user_id);
	            ranker.setGrade(grade);
	            ranker.setPost_count(post_count);
	            ranker.setReply_count(reply_count);
	            ranker.setPoint(point);

	            rankerList.add(ranker);
	         } // end while
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } // end try
	      return rankerList;
	   }// end
}