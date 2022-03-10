<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.btn {
  position:relative;
  border-radius:10px;
  align-content:center;
  justify-content:center;
  text-align: center;
  width:100px;
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

.container{
  position:absolute;

  border:1px solid red;
  text-align: center;
}
.logindiv{
	position:relative;
	width:250px;
	height:200px;
	border:1px solid red;
	align-items: center;
	align-content:center;
  	justify-content:center;
  	text-align: center;
}


</style>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

<div class='container'>
	
      <h1>Login Page</h1>
<article>
	<form action="login" method="post">
		I D : <input type="text" name="id" /><br/>
		PW: <input type="password" name="pw" /><br/>
		<input class="btn" type="submit" value="login"/>		
	</form>
	<button class="btn" onclick='window.open("memberInsert.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=2200,width=500,height=800");'>회원가입</button>
	
</article>
   
 
</div>
</body>
</html>