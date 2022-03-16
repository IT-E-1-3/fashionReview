package com.controller.reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.ReplyDAO;

/*
 * 댓글 삭제 서블릿
 * 작성자 : 김보경, 차성호
 */

//deleteComment의 매핑명렁어에 서블릿 실행됨
@WebServlet("/deleteComment")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public ReplyDeleteServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//ReplyDAO 객체 생성
		ReplyDAO replyDAO = new ReplyDAO();
		//name="reply_id"인 항목과 board_id인 항목을 String변수에 저장
		String reply_id = (String) request.getParameter("reply_id");
		String board_id = (String) request.getParameter("board_id");

		//ReplyDAO의 deleteReply를 통해 reply_id의 번호를 갖는 댓글 삭제
		replyDAO.deleteReply(reply_id);

		//보고있던 페이지(viewArticle.jsp?no=게시물번호)로 redirect
		response.sendRedirect("viewArticle.jsp?no=" + board_id);
	}

}
