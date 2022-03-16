<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.btn {
   position: relative;
   border-radius: 10px;
   align-content: center;
   justify-content: center;
   text-align: center;
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

.container {
   position: absolute;
   width: 100%;
   height: 100%;
   text-align: center;
   justify-content: center;
   align-item: center;
   background-color: lightgray;
}

.loginDiv {
   background-color: white;
   margin-top: 50px;
   height: 90%;
   text-algin: center;
   width: 20%;
   margin-left: 40%;
   box-shadow: 2px 3px 5px 0px;
}
</style>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
   integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
   crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<meta charset="UTF-8">
<title>로그인 페이지</title>
<script src="js/my.js"></script>
</head>
<body>

   <div class='container'>
      <div class='loginDiv'>
         <h1 style="padding-top: 5%">
            Fashion <br>Information<br> Platform
         </h1>
         <img src='assets/img/logo.png' height='30%' width='70%'>
         <h5>Fashion is what you make of it!</h5>
         <br />

         <!-- 김보경, 차성호: id와 pw를 입력받아 my.js 파일로 값을 넘김   -->
         <article>
            I D : <input type="text" name="id" id="id" /><br> 
            PW: <input type="password" name="pw" id="pw" /><br>
            <br>
            <button id="loginBtn" class="btn" style="margin-bottom:15px">login</button>
            <br>
            <!-- 김보경, 차성호: 회원가입을 위해 memberInsert.html을 새 브라우저를 띄움 -->
            <button class="btn"
               onclick='window.open("memberInsert.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=2200,width=500,height=800");'>회원가입</button>
         </article>
      </div>

   </div>
</body>
</html>