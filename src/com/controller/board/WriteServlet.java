package com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.controller.util.UploadUtil;
import com.dao.BoardDAO;

import com.vo.BoardVO;

@WebServlet("/boardWrite")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50)
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WriteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		BoardVO board = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();

		board.setUser_id(request.getParameter("user_id"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));

		UploadUtil uploadUtil = UploadUtil.create(request.getServletContext());

		Part part = request.getPart("file");

		if (part.getSubmittedFileName().equals("")) {

		} else {
			String filePath = uploadUtil.saveFiles(part, uploadUtil.createFileParth());
			int index = filePath.indexOf("upload");
			String newPath = filePath.substring(index);
			board.setPicture(newPath);
		}

		String[] categories = request.getParameterValues("category");

		board.setCategory(categories);

		boardDAO.insertPost(board);

		RequestDispatcher dispatcher = request.getRequestDispatcher("boardWriteForm.jsp");
		dispatcher.forward(request, response);
	}

}