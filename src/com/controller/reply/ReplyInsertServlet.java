package com.controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyDAO;
import com.vo.ReplyVO;

/*
 * 게시물 작성 서블릿
 * 작성자 : 김보경, 차성호
 */

//insertComment 매핑명렁어에 서블릿 실행됨
@WebServlet("/insertComment")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
    public ReplyInsertServlet() {
        super();
    }

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request 데이터 utf-8형식으로 encoding
	    request.setCharacterEncoding("UTF-8");
		//ReplyDAO 객체 생성
		ReplyDAO replyDAO = new ReplyDAO();
		//ReplyVO 객체 생성
		ReplyVO reply = new ReplyVO();
		//name="board_id"인 항목을 String변수에 저장
		String board_id = (String)request.getParameter("board_id");
		
		//ReplyVO 객체의 필드에 form 태그에서 받아온 값들을 저장
		reply.setBoard_id(request.getParameter("board_id"));
		reply.setUser_id(request.getParameter("user_id"));
		reply.setContent(request.getParameter("content"));
		
		//ReplyDAO의 insertReply메소드를 통해 댓글 생성
		replyDAO.insertReply(reply);
			
		//보고있던 페이지(viewArticle.jsp?no=게시물번호)로 redirect
		response.sendRedirect("viewArticle.jsp?no="+board_id);
		
	}

}
