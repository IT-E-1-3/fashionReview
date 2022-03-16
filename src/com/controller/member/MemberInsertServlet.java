package com.controller.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.vo.UserVO;

/*
 * 회원가입 서블릿
 * 작성자 : 김보경, 차성호
 */

//join의 매핑명렁어에 서블릿 실행됨
@WebServlet("/join")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public MemberInsertServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//UserVO 객체 생성
		UserVO userVO = new UserVO();
		//UserDAO 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//UserVO객체의 필드에 form태그로 받아온 회원정보들을 저장
		userVO.setId(request.getParameter("id"));
		userVO.setPw(request.getParameter("pw"));
		userVO.setName(request.getParameter("name"));
		userVO.setEmail(request.getParameter("email"));
		userVO.setPhone(request.getParameter("phone"));
		userVO.setGender(request.getParameter("gender"));
		userVO.setAge(Integer.parseInt(request.getParameter("age")));
		userVO.setHeight(Integer.parseInt(request.getParameter("height")));
		userVO.setWeight(Integer.parseInt(request.getParameter("weight")));

		
		try {
			//UserDAO의 inserUser에 UserVO객체를 넣어주고 실행(회원가입)
			userDAO.insertUser(userVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//resultPage.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("resultPage.jsp");
		dispatcher.forward(request, response);

	}

}
