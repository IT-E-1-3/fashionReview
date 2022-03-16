package com.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.vo.BoardVO;

/*
 * 게시물 카테고리 검색 서블릿
 * 작성자 : 김보경, 차성호
 */

//selectCategory의 매핑명렁어에 서블릿 실행됨
@WebServlet("/selectCategory")
public class SelectCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public SelectCategoryServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//BoardVO 객체 생성
		BoardVO board = new BoardVO();
		//BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		//BoardVO를 원소로 갖는 ArrayList객체 생성
		ArrayList<BoardVO> list = new ArrayList<>();
		//세션값 사용하기 위해 HttpSession객체 생성
		HttpSession session = request.getSession();

		//카테고리가 선택되었다는 정보를 전달하기 위해 
		//session의 category key값을 갖는 value에 "checked"를 저장
		session.setAttribute("category", "checked");

		//name="choice"에서 선택된 항목들을 String[] categories에 저장
		String[] categories = request.getParameterValues("choice");
		//가져온 값이 없을경우
		if (categories == null || categories.length == 0) {
			//선택된 값이 없다는 정보를 session의 category key값을 갖는 속성값에 null값을 줌으로써 전달
			session.setAttribute("category", null);
			//boardList.jsp로 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
			dispatcher.forward(request, response);
		} else {
			//게시물 검색을 위해 받아온 category값들을 BoardVO객체에 저장
			board.setCategory(categories);
			//BoardDAO의 selectByCategory메소드를 실행시키고 실행결과를 list에 저장
			list = boardDAO.selectByCategory(board);
			//session의 boardList key값을 갖는 value에 list(실행결과)를 저장
			session.setAttribute("boardList", list);
			//boardList.jsp로 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
			dispatcher.forward(request, response);

		}

	}

}
