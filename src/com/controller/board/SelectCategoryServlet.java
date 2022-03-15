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

@WebServlet("/selectCategory")
public class SelectCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectCategoryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardVO board = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardVO> list = new ArrayList<>();

		HttpSession session = request.getSession();

		session.setAttribute("category", "checked");

		String[] categories = request.getParameterValues("choice");
		if (categories == null || categories.length == 0) {
			session.setAttribute("category", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
			dispatcher.forward(request, response);
		} else {
			board.setCategory(categories);
			list = boardDAO.selectByCategory(board);

			session.setAttribute("boardList", list);

			RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
			dispatcher.forward(request, response);

		}

	}

}
