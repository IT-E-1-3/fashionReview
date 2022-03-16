
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result Page</title>
</head>
<body>
<!-- 작성자 :김보경, 차성호 --> 
   <!-- 파라미터로 받아온 id와 pw 중 하나라도 값이 비어있을 경우 다시 loginForm.jsp로 반환 -->
   <c:if test="${empty param.id or empty param.pw}">
      <c:redirect url="loginForm.jsp">
         <c:param name="errMsg" value="Please Enter Id and PW" />
      </c:redirect>
   </c:if>
   <!-- 회원가입이 완료됐을 때 뜨는 메시지 -->
   <!-- 파라미터로 받아온 id와 pw가 값이 있을 때 반환 -->
   <c:if test="${not empty param.id and not empty param.pw }"> 
      <c:out value="환영합니다. ${param.id } 고객님 가입한 id와 pw로 로그인해주세요." escapeXml="true" default="id 입력안함" />
      <c:out value="" escapeXml="true" default="pw 입력안함" />
   </c:if>
</body>
</html>