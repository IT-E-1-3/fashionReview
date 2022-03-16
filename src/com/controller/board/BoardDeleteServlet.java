package com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

/*
 * 게시물 삭제 서블릿
 * 작성자 : 김보경, 차성호
 */

//deletePost의 매핑명렁어에 서블릿 실행됨
@WebServlet("/deletePost")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public BoardDeleteServlet() {
		super();

	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		
		//name='board_id'인 항목의 parameter값 받아옴
		String board_id = request.getParameter("board_id");
		//BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		//BoardDAO의 deletePost메소드 실행
		boardDAO.deletePost(board_id);

		//boardList.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
		dispatcher.forward(request, response);
	}

}
