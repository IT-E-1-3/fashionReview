package com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.vo.BoardVO;

/*
 * 게시물 수정 서블릿
 * 작성자 : 김보경, 차성호
*/

//updatePost의 매핑명렁어에 서블릿 실행됨
@WebServlet("/updatePost")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public BoardUpdateServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");

		//name='board_id'인 항목의 parameter값 받아옴
		String board_id = request.getParameter("board_id");
		//BoardVO 객체 생성
		BoardVO board = new BoardVO();
		//BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();

		//BoardVO 객체의 변수에 필드값 설정
		board.setBoard_id(board_id);
		board.setContent(request.getParameter("content"));
		board.setTitle(request.getParameter("title"));
		board.setCategory(request.getParameterValues("category"));
		
		//BoardDAO의 updatePost메소드 실행
		boardDAO.updatePost(board);

		//boardList.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
		dispatcher.forward(request, response);

	}

}
