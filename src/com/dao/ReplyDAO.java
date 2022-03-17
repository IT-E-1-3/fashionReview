package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.controller.DBmanager.DBManager;
import com.vo.BoardVO;
import com.vo.ReplyVO;

import oracle.jdbc.OracleTypes;

/**
 * @file Name : ReplyDAO.java
 * @project name : fashion_review
 * @package name : com.dao
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 댓글 삽입, 삭제, 수정, 읽기에 대한 DB 프로시져를 실행하기 위한 기능을 전담하기 위한 Object
 */
public class ReplyDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource dataFactory;

	public ReplyDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	   /**
	    * @Method Name : insertReply
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Reply 패키지의 reply_insert 프로시저로 게시글에 댓글 추가
	    */

	 public void insertReply(ReplyVO replyVO) {
	      

	      Connection conn = null;

	      String runSP = "{ call reply_pack.reply_insert(?, ?, ?, sysdate) }";
	      try {
	         conn = DBManager.getConnection();
	         CallableStatement callableStatement = conn.prepareCall(runSP);

	         callableStatement.setString(1, replyVO.getBoard_id());
	         callableStatement.setString(2, replyVO.getUser_id());
	         callableStatement.setString(3, replyVO.getContent());
	         

	         callableStatement.executeUpdate();
	                
	         conn.close();
	         callableStatement.close();
	         

	      } catch (SQLException sqle) {
	         sqle.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	   /**
	    * @Method Name : selectAllReply
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Reply 패키지의 reply_select_all_board_id 프로시저로 해당 게시글에 대한 댓글 모두 읽기
	    */

	public ArrayList<ReplyVO> selectAllReply(String board_id) {
	      ArrayList<ReplyVO> list = new ArrayList<>();
	      Connection conn = null;
	      
	      String runSP = "{call reply_pack.reply_select_all_board_id(?,?)}";
	      

	      try {
	         conn = DBManager.getConnection();
	         CallableStatement callableStatement = conn.prepareCall(runSP);

	         callableStatement.setString(1, board_id);
	         callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

	         callableStatement.execute();

	         ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

	         while (resultSet.next()) {
	            ReplyVO reply = new ReplyVO();
	            
	            reply.setBoard_id(resultSet.getString(1));
	            reply.setUser_id(resultSet.getString(2));
	            reply.setContent(resultSet.getString(3));
	            reply.setReply_date(resultSet.getDate(4));
	            
	            list.add(reply);
	         }
	         resultSet.close();
	         callableStatement.close();
	         conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return list;
	   }

	 /**
	    * @Method Name : deleteReply
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Reply 패키지의 reply_delete 프로시저로 해당 댓글 삭제
	    */

	public void deleteReply(ReplyVO reply) {
		try {
			conn = DBManager.getConnection();
			String query = "delete from reply where board_id=? and member_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, reply.getBoard_id());
			pstmt.setString(2, reply.getUser_id());

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	 /**
	    * @Method Name : countReply
	    * @작성일 : 2022.03.14
	    * @작성자 : 김정휴, 심다혜
	    * @Method 설명 : Reply 패키지의 reply_count 프로시저로 한 회원이 작성한 댓글 개수(멤버십 포인트 계산을 위한) 세기
	    */

	public int countReply(String user_id) {
		int count = 0;

		try {
			conn = DBManager.getConnection();
			String query = "SELECT count(*) as count from reply where member_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
