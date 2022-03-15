package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.vo.BoardVO;
import com.vo.ReplyVO;

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

	public void insertReply(ReplyVO reply) {
		PreparedStatement pstmt = null;
		try {
			conn = dataFactory.getConnection();

			pstmt = conn.prepareStatement(
					"insert into reply (board_id,user_id,reply_id, content, reply_date) values(?,?,reply_count.nextval,?,sysdate)");

			pstmt.setString(1, reply.getBoard_id());
			pstmt.setString(2, reply.getUser_id());
			pstmt.setString(3, reply.getContent());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ReplyVO> selectAllReply(String board_id) {
		ArrayList<ReplyVO> list = new ArrayList<>();

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * from reply where board_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, board_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setUser_id(rs.getString("user_id"));
				reply.setBoard_id(rs.getString("board_id"));
				reply.setReply_id(rs.getString("reply_id"));
				reply.setContent(rs.getString("content"));
				reply.setReply_date(rs.getDate("reply_date"));

				list.add(reply);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void deleteReply(String reply_id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from reply where reply_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, reply_id);

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int countReply(String user_id) {
		int count = 0;

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT count(*) as count from reply where user_id=?";
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
