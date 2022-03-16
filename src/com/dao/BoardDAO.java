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
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<BoardVO> selectByCategory(BoardVO board) {
		
		ArrayList<BoardVO> list= new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			String[] category=board.getCategory().split(",");
			for(String tmp: category) {
				String query="SELECT * from board where category like ?";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, '%'+tmp+'%');
					
				rs=pstmt.executeQuery(); 
				
				while(rs.next()) {
					BoardVO board1 = new BoardVO();
					
					board1.setUser_id(rs.getString("user_id"));
					board1.setBoard_id(rs.getString("board_id"));
					board1.setTitle(rs.getString("title"));
					board1.setContent(rs.getString("content"));
					board1.setPicture(rs.getString("picture"));
					board1.setWirte_date(rs.getDate("write_date"));
					
					list.add(board1);
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	public void insertPost(BoardVO board) {
		PreparedStatement pstmt=null;
		try{
			conn=dataFactory.getConnection();
			
			pstmt=conn.prepareStatement("insert into board (board_id,user_id, title, content, picture,category,write_date) values(board_count.nextval,?,?,?,?,?,sysdate)");		
			
			pstmt.setString(1,board.getUser_id());
			pstmt.setString(2,board.getTitle());
			pstmt.setString(3,board.getContent());
			pstmt.setString(4,board.getPicture());
			pstmt.setString(5, board.getCategory());
			
			pstmt.executeUpdate();

			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
		}catch(SQLException sqle) {
			sqle.printStackTrace();
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
				board.setCategory(rs.getString("category").split(","));
							
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
		
	}

	public ArrayList<BoardVO> selectById(String user_id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * from board where user_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user_id);
			
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
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<BoardVO> selectByTitle(String title) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * from board where title like ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+title+"%");
			
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
			e.printStackTrace();
		}
		return list;
		
	}
	public void updatePost(BoardVO board) {
		
		try {
			conn=dataFactory.getConnection();
			String query="update board set title=?, content=?, write_date=sysdate , category=? where board_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getCategory());
			pstmt.setString(4, board.getBoard_id());
			
			pstmt.executeUpdate(); 
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deletePost(String board_id) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from board where board_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board_id);
			
			pstmt.executeUpdate(); 
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	   public int countPost(String user_id) {
	      int count=0;
	      
	      try {
	         conn=dataFactory.getConnection();
	         String query="SELECT count(*) as count from board where user_id=?";
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setString(1, user_id);
	         rs=pstmt.executeQuery(); 
	         
	         while(rs.next()) {
	            count=rs.getInt("count");
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
