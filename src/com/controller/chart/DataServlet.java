package com.controller.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.BoardDAO;
import com.dao.MemberShipDAO;
import com.vo.MemberShipVO;

/**
 * Servlet implementation class MemberServlet
 * 
 * 
 * 
 * 
 * 
 * 참고자료 
 * 1. 자바 웹을 다루는 기술 p 658 
 * 2. 수업실습파일 MVC2_replyBoard.oracle_SP 의 sec03.brd08.BoardController.java
 */
@WebServlet("/getData/*")
public class DataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// get 요청시 doHand..로 전달
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}// endo..

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// post 요청시 doHand..로 전달
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}// end doP...

	// 실제 수행 코드
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글화 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// 커맨드 가져오기
		String action = request.getPathInfo();
		// 커맨드 출력
		System.out.println("action:" + action);

		// 브라우저로 데이터_jsonInfo_를 전송해주는 PrintWriter 객체 인스턴스 선언
		PrintWriter writer = response.getWriter();

		// url 별 처리 try catch
		try {
			// 최다 포스트 TOP 3 에 대한 JSON 전송 부분
			if (action.equals("/SelectByCategory")) {
				// Membership DAO를 활용한 최다 포스트 Top 3 리스트 불러오기
				BoardDAO boardDAO = new BoardDAO();

				int[] count = new int[4];
				String[] category = {"top","bottom","shoe","Accessories"};
				count = boardDAO.countByCategory();

				// JSON 반환을 위한 JSON 오브젝트 선언
				JSONObject totalObject = new JSONObject();
				JSONArray membersArray = new JSONArray();


				JSONObject countInfo = new JSONObject();
					
				countInfo.put("top", count[0]);
				countInfo.put("bottom", count[1]);
				countInfo.put("shoe", count[2]);
				countInfo.put("acc", count[3]);		

				

				
				writer.print(countInfo);
			}
			// 최다 포스트 TOP 3 에 대한 JSON 전송 부분 2
			else if (action.equals("/Top5_point")) {
				
				// Membership DAO를 활용한 싱글턴 패턴 DB 연결
				MemberShipDAO membershipDAO = MemberShipDAO.getInstance();
				
				// Top 5 ranker of point 객체 저장을 위한 생성
				ArrayList<MemberShipVO> rankers = new ArrayList<MemberShipVO>();
				rankers = membershipDAO.selectRankers();

				// JSON 반환을 위한 JSON 오브젝트 선언
				JSONObject totalObject = new JSONObject();
				JSONArray membersArray = new JSONArray();

				for (int i = 0; i < rankers.size(); i++) {
					System.out.println(rankers.get(i).getId());
					System.out.println(rankers.get(i).getGrade());
					System.out.println(rankers.get(i).getPost_count());
					System.out.println(rankers.get(i).getReply_count());
					System.out.println(rankers.get(i).getPoint());

					JSONObject memberInfo = new JSONObject();
					
					memberInfo.put("id", rankers.get(i).getId());
					memberInfo.put("grade", rankers.get(i).getGrade());
					memberInfo.put("post_count", rankers.get(i).getPost_count());
					memberInfo.put("reply_count", rankers.get(i).getReply_count());
					memberInfo.put("point", rankers.get(i).getPoint());
					
					membersArray.add(memberInfo);
				}

				totalObject.put("members", membersArray);

				String jsonInfo = totalObject.toJSONString();
				System.out.print(jsonInfo);
				writer.print(jsonInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} // end try

	}// end doHandle

}
