package com.dao;

/**
 * @file Name : BoardDAO.java
 * @project name : fashion_review
 * @package name : com.dao
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 게시글 삽입, 삭제, 수정, 읽기에 대한 DB 프로시져를 실행하기 위한 기능을 전담하기 위한 Object
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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

	/**
	 * @Method Name : selectAllBoard
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : 게시판에 번호, 제목, 작성자, 작성일을 테이블 형식으로 뿌려주기 위한 Board table의 모든 게시글 정보
	 *         읽기
	 */

	public ArrayList<BoardVO> selectAllBoard() {
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * from board";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setUser_id(rs.getString("member_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWrite_date(rs.getDate("write_date"));

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

	/**
	 * @Method Name : selectByCategory
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : 해당하는 카테고리 게시글만 읽기 위한 메서드
	 */

	public ArrayList<BoardVO> selectByCategory(BoardVO board) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = dataFactory.getConnection();
			String[] category = board.getCategory().split(",");
			boolean[] check = new boolean[500];
			Arrays.fill(check, Boolean.FALSE);
			for (String tmp : category) {
				String query = "SELECT * from board where category like ?";
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, '%' + tmp + '%');

				rs = pstmt.executeQuery();

				while (rs.next()) {
					BoardVO board1 = new BoardVO();
					if (check[Integer.parseInt(rs.getString("board_id"))] == false) {
						board1.setUser_id(rs.getString("user_id"));
						board1.setBoard_id(rs.getString("board_id"));
						board1.setTitle(rs.getString("title"));
						board1.setContent(rs.getString("content"));
						board1.setPicture(rs.getString("picture"));
						board1.setWrite_date(rs.getDate("write_date"));
						check[Integer.parseInt(rs.getString("board_id"))] = true;

						list.add(board1);
					}
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

	/**
	 * @Method Name : insertPost
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : Board 패키지의 board_insert 프로시져를 이용하여 게시글 추가
	 */

	public void insertPost(BoardVO board) {
		PreparedStatement pstmt = null;
		try {
			conn = dataFactory.getConnection();

			pstmt = conn.prepareStatement(
					"insert into board (board_id,member_id, title, content, picture,category,write_date) values(board_count.nextval,?,?,?,?,?,sysdate)");

			pstmt.setString(1, board.getUser_id());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getPicture());
			pstmt.setString(5, board.getCategory());

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

	/**
	 * @Method Name : selectPost
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : Board 패키지의 board_select_all_board_id 프로시져를 이용하여 cursor로 선택한 게시글
	 *         정보 가져오기
	 */

	public BoardVO selectPost(String no) {
		BoardVO board = new BoardVO();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * from board where board_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				board.setUser_id(rs.getString("member_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWrite_date(rs.getDate("write_date"));
				board.setCategoryList(rs.getString("category").split(","));

			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;

	}

	/**
	 * @Method Name : selectById
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : 검색 기능에서 게시글 작성자를 통해 게시글 읽어오기
	 */

	public ArrayList<BoardVO> selectById(String user_id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * from board where member_id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setUser_id(rs.getString("member_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWrite_date(rs.getDate("write_date"));

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

	/**
	 * @Method Name : selectByTitle
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : 검색 기능에서 게시글 title을 통해 게시글 읽어오기
	 */

	public ArrayList<BoardVO> selectByTitle(String title) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * from board where title like ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + title + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setUser_id(rs.getString("member_id"));
				board.setBoard_id(rs.getString("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPicture(rs.getString("picture"));
				board.setWrite_date(rs.getDate("write_date"));

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

	/**
	 * @Method Name : updatePost
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : Board 패키지의 board_all_update 프로시져를 이용하여 게시글 수정
	 */

	public void updatePost(BoardVO board) {

		try {
			conn = dataFactory.getConnection();
			String query = "update board set title=?, content=?, write_date=sysdate , category=? where board_id=?";
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

	/**
	 * @Method Name : deletePost
	 * @작성일 : 2022.03.14
	 * @작성자 : 김정휴, 심다혜
	 * @Method 설명 : Board 패키지의 board_delete 프로시져를 이용하여 해당 게시글 id와 멤버 id에 해당하는 게시글
	 *         지우기
	 */

	public void deletePost(String board_id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from board where board_id=?";
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
		int count = 0;

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT count(*) as count from board where member_id=?";
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

	public int[] countByCategory() {
		int[] count = new int[4];
		String[] category = { "top", "bottom", "shoe", "Accessories" };

		try {
			conn = dataFactory.getConnection();
			for (int i = 0; i < 4; i++) {
				String query = "SELECT count(*) as count from board where category like ?";
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, "%" + category[i] + "%");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					count[i] = rs.getInt("count");
				}
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
