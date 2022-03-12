<%@ page language="java" contentType="text/html; charset=UTF-8"   
import="java.util.*,com.vo.*,com.dao.*" pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id"); 
	

	MemberShipVO m =  new MemberShipVO();
	MemberShipDAO  membershipDAO = MemberShipDAO.getInstance();
	m = membershipDAO.getMemberShipALL(id);	


%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style type="text/css">
.btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 20px;
  margin-top:20px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.btn:hover {
  background-position: right;
}
</style>
<title>마이페이지</title>
</head>
<body>
	<%
	   if(m==null||m.equals("")){
	%>
	  
	<p align="center">
	    	<b><span style="font-size:9pt;">
	            등록된 회원이  없습니다.
	        </span></b>
	</p>
	
	  
	  <%
	}else{
	   
	%>
   <div class="w3-content w3-container w3-margin-top">
      <div class="w3-container w3-card-4">
         <div class="w3-center w3-large w3-margin-top">
            <h3><%=id %>님의 My Page</h3>
         </div>
         <div>
            
               <p>
                  <label>멤버쉽 등급 : <%= m.getGrade() %></label> 
                  <input class="w3-input" type="text" id="id" name="id" readonly value="<%= m.getGrade() %>"> 
               </p>
               <p>
                  <label>멤버쉽 포인트 : <%= m.getPoint() %></label> 
                  <input class="w3-input" type="text" id="email" name="email" value=<%= m.getPoint() %> required> 
               </p>
            
            <br />
           
     
               <p>
                  <label>작성한 게시글 수 : <%= m.getPost_count() %></label>
                  <input class="w3-input" id="old_pw" name="old_pw" type="text" required>
               </p>
               <p>
                  <label>작성한 댓글 수</label> 
                  <input class="w3-input" id="pw" name="pw" type="text" required>
               </p>
               
               <p class="w3-center">
               </p>
               <p class="w3-center">
                  <button onClick="location.href='memberUpdate.jsp'" class="btn">회원정보 변경</button>
               </p>
                  <p class="w3-center">
                  <button type="submit" class="btn">메인화면</button>
               </p>
               <%}%>
            
         </div>
      </div>
   </div>
</body>
</html>