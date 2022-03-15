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

@WebServlet("/selectBy")
public class SelectBy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectBy() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String option = request.getParameter("selectOption");
		HttpSession session = request.getSession();
		BoardDAO boardDAO = new BoardDAO();

		ArrayList<BoardVO> list = new ArrayList<>();

		if (option.equals("id")) {
			list = boardDAO.selectById(request.getParameter("keyword"));
			session.setAttribute("boardList", list);
		} else if (option.equals("title")) {
			list = boardDAO.selectByTitle(request.getParameter("keyword"));
			session.setAttribute("boardList", list);
		}
		session.setAttribute("selectOption", option);

		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
		dispatcher.forward(request, response);
	}

}
