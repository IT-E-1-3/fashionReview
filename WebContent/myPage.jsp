<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.vo.*,com.dao.*" pageEncoding="UTF-8"%>

<%
	String id = (String)session.getAttribute("id"); 
	

	MemberShipVO m =  new MemberShipVO();
	MemberShipDAO  membershipDAO = MemberShipDAO.getInstance();
	m = membershipDAO.getMemberShipALL(id);	

	MemberShipVO membership = new MemberShipVO();
	membership = membershipDAO.getMemberShipALL(id);
	
	ReplyVO reply=new ReplyVO();
	ReplyDAO replyDAO=new ReplyDAO();
	int reply_count=replyDAO.countReply(id);
	
	BoardVO board=new BoardVO();
	BoardDAO boardDAO=new BoardDAO();
	int post_count=boardDAO.countPost(id);

	//ArrayList<MemberShipVO> rankers = new ArrayList<MemberShipVO>();
	//rankers = membershipDAO.selectTopPostRankers();


%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style type="text/css">
.btn {
	position: relative;
	left: 40%;
	transform: translateX(-50%);
	border-radius: 10px;
	margin-bottom: 20px;
	margin-top: 20px;
	width: 80%;
	height: 40px;
	background: linear-gradient(125deg, #81ecec, #6c5ce7, #81ecec);
	background-position: left;
	background-size: 200%;
	color: white;
	font-weight: bold;
	border: none;
	cursor: pointer;
	transition: 0.4s;
	display: inline;
}

.btn:hover {
	background-position: right;
}
</style>
<title>마이페이지</title>
</head>
<body>
	<%

	   if(m==null||m.equals("") || m.getGrade()==null || m.getGrade().equals("")){

	%>

	<p align="center">
		<b><span style="font-size: 9pt;"> 등록된 회원이 없습니다. </span></b>
	</p>

	<%
	}else{
		
		String path="";
		
		switch(m.getGrade()){
		case "bronze":
			path="bronze.PNG";
			break;
		case "silver":
			path="silver.PNG";
			break;
		case "gold":
			path="gold.PNG";
			break;
		case "platinum":
			path="platinum.PNG";
			break;
		case "dia":
			path="dia.PNG";
			break;
		}
	%>

	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4"
			style="width: 60%; align-content: center; margin-left: 20%">
			<div class="w3-center w3-large w3-margin-top">
				<h3><%=id %>님의 My Page
				</h3>
			</div>
			<div>
				<br />
				<p>
					<label>멤버쉽 등급 : <img src="assets/img/grade/<%= path%>"/></label>
				</p>
				<br />
				<p>
					<label>멤버쉽 포인트 : <%= m.getPoint() %></label>
				</p>
				<br />
				<p>
					<label>작성한 게시글 수 : <%=post_count %></label>
				</p>
				<br />
				<p>
					<label>작성한 댓글 수 : <%= reply_count %></label>
				</p>
				<br />

				<p class="w3-center"></p>
				<p class="w3-center">
					<button onClick="location.href='memberUpdate.jsp'" class="btn">회원정보 변경</button>
					<%}%>
			</div>
		</div>
	</div>
</body>
</html>