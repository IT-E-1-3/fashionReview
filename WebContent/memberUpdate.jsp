<%@ page language="java" contentType="text/html; charset=UTF-8"   
import="java.util.*,com.vo.*,com.dao.*" pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id"); 
	

	UserVO user =  new UserVO();
	UserDAO  userDAO=new UserDAO();
	user = userDAO.getUser(id);	


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
<title>회원정보 수정</title>
</head>
<body>
	<%
	   if(id==null || id.equals("")){
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
            <h3><%=user.getId() %>님의 회원정보</h3>
         </div>
         <div>
            <form action="memberUpdate" method="post">
               <p>
                  <label>ID : <%= user.getId() %></label> 
                  <input class="w3-input" type="text" id="id" name="id" readonly value=" <%= user.getId() %>"> 
               </p>
               <p>
                  <label>pw : <%= user.getPw() %></label> 
                  <input class="w3-input" type="text" id="pw" name="pw"  value="<%= user.getPw() %>"> 
               </p>
               <p>
                  <label>email : <%= user.getEmail() %></label> 
                  <input class="w3-input" type="text" id="email" name="email"  value="<%= user.getEmail() %>"> 
               </p>
               <p>
                  <label>age : <%= user.getAge() %></label> 
                  <input class="w3-input" type="number" id="age" name="age"  value="<%= user.getAge() %>"> 
               </p>
               <p>
                  <label>gender : <%= user.getGender() %></label> 
                  <input class="w3-input" type="text" id="gender" name="gender"  value="<%= user.getGender() %>"> 
               </p>
               <p>
                  <label>height : <%= user.getHeight() %></label> 
                  <input class="w3-input" type="number" id="height" name="height"  value="<%= user.getHeight() %>"> 
               </p>
               <p>
                  <label>weight : <%= user.getWeight() %></label> 
                  <input class="w3-input" type="number" id="weight" name="weight" value="<%= user.getWeight() %>"> 
               </p>
              
               <p class="w3-center">
               </p>
               <p class="w3-center">
                  <input type="submit" class="btn" value="회원정보 변경">
               </p>
               </form>
                  <p class="w3-center">
                  <button type="submit" class="btn">메인화면</button>
               </p>
               <%}%>
            
         </div>
      </div>
   </div>
</body>
</html>