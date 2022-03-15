package com.controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyDAO;
import com.vo.ReplyVO;


@WebServlet("/insertComment")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyInsertServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    request.setCharacterEncoding("UTF-8");
		
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyVO reply = new ReplyVO();
		String board_id = (String)request.getParameter("board_id");
		
		reply.setBoard_id(request.getParameter("board_id"));
		reply.setUser_id(request.getParameter("user_id"));
		reply.setContent(request.getParameter("content"));
		
		replyDAO.insertReply(reply);
				
		response.sendRedirect("viewArticle.jsp?no="+board_id);
		
	}

}
