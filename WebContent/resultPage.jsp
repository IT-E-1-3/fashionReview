
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
	<c:if test="${empty param.id or empty param.pw}">
		<c:redirect url="loginForm.jsp">
			<c:param name="errMsg" value="Please Enter Id and PW" />
		</c:redirect>
	</c:if>
	<c:if test="${not empty param.id and not empty param.pw }">
		<c:out value="환영합니다. ${param.id } 고객님" escapeXml="true" default="id 입력안함" />
		<c:out value="${param.pw }" escapeXml="true" default="pw 입력안함" />
	</c:if>
</body>
</html>