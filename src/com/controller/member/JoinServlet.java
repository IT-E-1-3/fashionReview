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

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
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
		
		String url="resultPage.jsp";
		
		HttpSession session = request.getSession();  
		
	    UserVO userVO = new UserVO();    
	    userVO.setId(request.getParameter("id"));
	    userVO.setPw(request.getParameter("pw"));
	    userVO.setEmail(request.getParameter("email"));
	    userVO.setPhone(request.getParameter("phone"));
	    userVO.setAge(Integer.parseInt(request.getParameter("age")));
	    userVO.setGender(request.getParameter("gender"));
	    userVO.setHeight(Integer.parseInt(request.getParameter("height")));
	    userVO.setWeight(Integer.parseInt(request.getParameter("weight")));
	    
	    session.setAttribute("id", request.getParameter("id"));    
	    
	    UserDAO userDAO = new UserDAO();
	    try {
			userDAO.insertUser(userVO);
			System.out.println("post에서 회원가입 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
		
		//doGet(request, response);
	}

}
