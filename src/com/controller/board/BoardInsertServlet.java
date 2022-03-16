package com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.controller.util.UploadUtil;
import com.dao.BoardDAO;
import com.vo.BoardVO;

/*
 * 게시물 작성 서블릿
 * 작성자 : 김보경, 차성호
 */

//boardWrite의 매핑명렁어에 서블릿 실행됨
@WebServlet("/boardWrite")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50)
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//기본생성자
	public BoardInsertServlet() {
		super();
	}

	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//request 데이터 utf-8형식으로 encoding
		request.setCharacterEncoding("UTF-8");
		//BoardVO 객체 생성
		BoardVO board = new BoardVO();
		//BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		//form태그에서 받아온 category값들이 여러개일 수 있으므로 String[]형식의 변수로 받아줌
		String[] categories = request.getParameterValues("category");


		//BoardVO객체의 필드에 form태그를 통해 받아온 값들을 저장
		board.setUser_id(request.getParameter("user_id"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setCategoryList(categories);
		//파일 저장을 위한 UploadUtil클래스의 객체를 UploadUtil.create메소드를 통해 생성
		UploadUtil uploadUtil = UploadUtil.create(request.getServletContext());

		//form태그를 통해 들어온 multipartFile형식의 파일을 Part객체에 저장
		Part part = request.getPart("file");

		if (part.getSubmittedFileName().equals("")) {

		}
		//저장된 Part객체가 null이 아닐경우 실행
		else {
			//UploadUtil의 saveFiles메소드를 통해 저장될 파일경로에 대한 변수를 선언
			String filePath = uploadUtil.saveFiles(part, uploadUtil.createFileParth());
			//이미지파일 표시를 위한 url을 추출하기 위해 상대경로값으로 변환
			int index = filePath.indexOf("upload");
			//상대경로값을 변수에 저장
			String newPath = filePath.substring(index);
			//BoardVO객체의 picture필드에 저장된 파일의 상대경로값 저장
			board.setPicture(newPath);
		}

		//BoardDAO의 insertPost메소드를 BoardVO객체를 넘겨주면서 실행
		boardDAO.insertPost(board);

		//boardList.jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardWriteForm.jsp");
		dispatcher.forward(request, response);
	}

}