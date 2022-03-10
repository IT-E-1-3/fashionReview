package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.vo.UserVO;

//https://wiper2019.tistory.com/92 -참고

/**
 * Servlet implementation class LoginServlet
 */
//Servlet 실행 순서: init(최초 한번 실행) -> service -> doGet/doPost -> destory 순
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
	
		HttpSession session=request.getSession(); 
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    String id=request.getParameter("id");
	    String pw=request.getParameter("pw");
	    
	    UserDAO userDAO=UserDAO.getInstance();    
	    //테이블에 유저 있는지 확인
	    UserVO userVO = new UserVO();
	    userVO=userDAO.getUser(id);
	    System.out.println(userVO.getId());
	    if( userVO!=null){ //null이 아닐때 mainPage.jsp로 이동
	        if(userVO.getPw().equals(pw)){ //��ȣ Ȯ��   
//	          session.removeAttribute("id");
//	          session.setAttribute("loginUser", userVO);
	          session.setAttribute("id", id);
	        }
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	      }
//	    else {
//	  	    
//	  	    out.println("<script type='text/javascript'>alert('회원이 존재하지 않습니다'); </script>");
//	  	    out.flush();
	    	  
	    //  }
	    }
		//doGet(request, response);
	}


