package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * 로그아옷 서블릿
 * 작성자 : 김보경, 차성호
 */

//logout의 매핑명렁어에 서블릿 실행됨
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public LogoutServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//세션값 사용하기 위해 HttpSession객체 생성
		HttpSession session = request.getSession();
		//session의 id key값을 같는 value값이 존재할 경우
		if (session.getAttribute("id") != null || session.getAttribute("id").equals("")) {
			//session정보 초기화
			session.invalidate();
		}
		
		//loginForm.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginForm.jsp");
		dispatcher.forward(request, response);
	}

}
