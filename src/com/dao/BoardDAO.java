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
import com.vo.MemberShipVO;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource dataFactory;
	
	public BoardDAO() {		
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardVO> selectAllBoard() {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * from board";
			pstmt = conn.prepareStatement(query);
			
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWirte_date(rs.getDate("write_date"));
				
				
				list.add(board);
				
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

	public void insertPost(BoardVO board) {
		PreparedStatement pstmt=null;
		try{
			conn=dataFactory.getConnection();
			
			
			pstmt=conn.prepareStatement("insert into board (board_id,user_id, title, content, picture,write_date) values(board_count.nextval,?,?,?,?,sysdate)");
//			stmt=conn.createStatement();
		
			
			pstmt.setString(1,board.getUser_id());
			pstmt.setString(2,board.getTitle());
			pstmt.setString(3,board.getContent());
			pstmt.setString(4,board.getPicture());
			
			
			pstmt.executeUpdate();
			conn.commit();
		
			
			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
		}catch(SQLException sqle) {
			sqle.printStackTrace();
//			conn.rollback();
		}catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public BoardVO selectPost(String no) {
		BoardVO board = new BoardVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * from board where board_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, no);
			
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWirte_date(rs.getDate("write_date"));
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
		
	}

	public void updatePost(BoardVO board) {
		
		try {
			conn=dataFactory.getConnection();
			String query="update board set title=?, content=?, write_date=sysdate where board_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getBoard_id());
			
			pstmt.executeUpdate(); 
			
			
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deletePost(String board_id) {
		BoardVO board = new BoardVO();
		try {
			conn=dataFactory.getConnection();
			String query="delete from board where board_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board_id);
			
			pstmt.executeUpdate(); 
			
			
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
