package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.dao.UserDAO;
import com.vo.UserVO;

/*
 * 로그인 서블릿
 * 작성자 : 김보경, 차성호
 */

//login의 매핑명렁어에 서블릿 실행됨
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public LoginServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//response 데이터를 json형식으로 선언해주고, utf-8로 인코딩해줌
		response.setContentType("application/json;charset=utf-8");
		//세션값 사용하기 위해 HttpSession객체 생성
		HttpSession session = request.getSession();
		
		//name='id'인 항목의 parameter값 받아옴
		String id = request.getParameter("id");
		//name='pw'인 항목의 parameter값 받아옴
		String pw = request.getParameter("pw");
		//JSONObject 객체 생성
		JSONObject json = new JSONObject();
		//결과값을 내보내기 위한 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		//UserDAO 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//받아온 id값을 가지는 user가 있는지 UserDAO의 getUser메소드를 통해 검사
		UserVO userVO = userDAO.getUser(id);
		
		// null이 아닐때 mainPage.jsp로 이동
		if (userVO != null) { 
			//getUser를 통해 받아온 id값이 없을경우
			if (userVO.getId() == null || userVO.getId().equals("")) {
				//JSONObject의 msg의 key값을가지는 value에 안내메시지 저장
				json.put("msg", "아이디가 존재하지 않습니다");
				//PrintWriter객체에 JSONObject객체를 넣어줌으로써 데이터 전달
				out.println(json.toJSONString());
			//id값은 있지만 pw값이 일치하지 않는 경우
			} else if (!(userVO.getPw().equals(pw))) {
				//JSONObject의 msg의 key값을가지는 value에 안내메시지 저장
				json.put("msg", "비밀번호가 일치하지 않습니다");
				//PrintWriter객체에 JSONObject객체를 넣어줌으로써 데이터 전달
				out.println(json.toJSONString());
			//id가 존재하면서 pw도 일치할 경우
			} else {
				//로그인 유지를 위해 session의 id key값을 가지는 value에 id값 저장
				session.setAttribute("id", id);
				//JSONObject의 id의 key값을가지는 value에 id값 저장
				json.put("id", userVO.getId());
				//PrintWriter객체에 JSONObject객체를 넣어줌으로써 데이터 전달
				out.println(json.toJSONString());
			}

		}
	}

}
