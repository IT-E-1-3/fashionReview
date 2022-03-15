package com.controller.reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.ReplyDAO;


@WebServlet("/deleteComment")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyDeleteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		ReplyDAO replyDAO = new ReplyDAO();
		String reply_id = (String) request.getParameter("reply_id");
		String board_id = (String) request.getParameter("board_id");

		replyDAO.deleteReply(reply_id);

		response.sendRedirect("viewArticle.jsp?no=" + board_id);
	}

}
