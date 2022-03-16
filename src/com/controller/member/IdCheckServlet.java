package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dao.UserDAO;

/*
 * 회원가입시 아이디 중복검사 서블릿
 * 작성자 : 김보경, 차성호
 */

//idCheck의 매핑명렁어에 서블릿 실행됨
@WebServlet("/idCheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//기본생성자
    public IdCheckServlet() {
        super();
    }

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("utf-8");
		//response 데이터를 json형식으로 선언해주고, utf-8로 인코딩해줌
		response.setContentType("application/json;charset=utf-8");
		//name='id'인 항목의 parameter값 받아옴
		String id = (String)request.getParameter("id");
		//결과값을 내보내기 위한 PrintWriter객체 생성
		PrintWriter out = response.getWriter();

		//UserDAO 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		//JSONObject 객체 생성
		JSONObject jObject = new JSONObject();
		
		//UserDAO의 checkId결과값이 true일 경우
		if(userDAO.checkId(id)) {
			//JSONObject객체의 idCheck의 key값을 같는 value에 true값을 저장
			jObject.put("idCheck", true);
		}else {
			//JSONObject객체의 idCheck의 key값을 같는 value에 false값을 저장
			jObject.put("idCheck", false);
		}
		
		
		//PrintWriter객체에 JSONObject객체를 넣어줌으로써 데이터 전달
		out.println(jObject.toJSONString());
	}

}
