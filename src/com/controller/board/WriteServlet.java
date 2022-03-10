package com.controller.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dao.BoardDAO;

import com.vo.BoardVO;


/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/boardWrite")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();  
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
	    BoardVO board = new BoardVO();
	    board.setUser_id(request.getParameter("user_id"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setPicture(request.getParameter("file"));
		
		System.out.println(board.toString());
	    
	    	    
	    BoardDAO boardDAO = new BoardDAO();
	    boardDAO.insertPost(board);
		System.out.println("post에서 등록 완료");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
	    dispatcher.forward(request, response);
	}

}
