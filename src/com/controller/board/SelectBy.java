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
 * 게시물 키워드 검색 서블릿
 * 작성자 : 김보경, 차성호
 */

//selectBy의 매핑명렁어에 서블릿 실행됨
@WebServlet("/selectBy")
public class SelectBy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public SelectBy() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//name='selectOption'인 항목의 parameter값 받아옴
		String option = request.getParameter("selectOption");
		//세션값 사용하기 위해 HttpSession객체 생성
		HttpSession session = request.getSession();
		//BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();

		//BoardVO를 원소로 갖는 ArrayList객체 생성
		ArrayList<BoardVO> list = new ArrayList<>();

		//selectOption에서 받아온 값(id,title)에 따라서 다른 메소드 실행
		if (option.equals("id")) {
			//BoardDAO의 selectById메소드를 실행시키고 결과를 list에 저장
			list = boardDAO.selectById(request.getParameter("keyword"));
			//session의 boardList key값을 갖는 value에 list를 저장
			session.setAttribute("boardList", list);
		} else if (option.equals("title")) {
			//BoardDAO의 selectByTitle메소드를 실행시키고 결과를 list에 저장
			list = boardDAO.selectByTitle(request.getParameter("keyword"));
			//session의 boardList key값을 갖는 value에 list(실행결과)를 저장
			session.setAttribute("boardList", list);
		}
		//session의 selectOption key값을 갖는 value에 list를 저장
		session.setAttribute("selectOption", option);

		//boardList.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
		dispatcher.forward(request, response);
	}

}
