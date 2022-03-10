<!DOCTYPE html>
<html lang="ko">
<head>
 <link href="/your-path-to-fontawesome/css/all.css" rel="stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">



<style type="text/css">
body {
      margin:10px;
      
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
    }

.searchDiv{
text-align: right;
}
.colorset{
      background-color: rgba(255, 255, 255, 0.36);

}

.main {
  width: 90%;
  height: 90%;
  background-color: rgba(255,255,255,0.45);
  padding: 20px;
}

</style>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.*, java.util.List" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %> <!-- 현재 페이지를 세션에 추가해줌 -->
  
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <!-- Custom CSS -->
  <link rel="stylesheet" href="nav_footer.css">
  <link rel="stylesheet" href="community.css">

  <title>Community</title>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script type="text/javascript">

</script>

</head>
<body>
<% String id = (String)session.getAttribute("id"); %>
  <nav class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
    <a href="02_home.html"><img  src="./images/logo.png"   height="50px" alt=""></img></a>
    <div class="me-2">
      <div class="collapse navbar-collapse" id="navbarNav">
         <ul class="navbar-nav">
          <li class="nav-item">
            <div class="nav-link text-white"  id='loginOK'>   <a href="scrapList" style="background-color:lightgrey" class="a_style btn btn-light"> <%=id %>님의 ScrapBox</a></div>
          </li>
        </ul>
      </div>
    </div>
  </nav> 
  <!-- 03_community.html -->
  <div class="main">
  <div class="main-container">
    <h1>Community <i class="fas fa-bullhorn fa-x"></i></h1>

    <!-- Board -->
<br><br>
                                 <center>
    <div class="searchDiv" >
          <select id="searchType" style="height:43px">       
             <option value="title">제목</option>
             <option value="id">아이디</option>
          </select>
      <input type="text" id="keyword" name="keyword" style="width: 230px; height:43px" placeholder="제목/아이디를 입력해주세요."/>
          <button type="button" id="searchBtn" class="btn btn-secondary btn-lg"  >검색</button>
      
    </div>
   <section>
      <div class="row row-cols-lg-10 mt-5">
        <table class="table table-striped table-hover" id="boardTable">

          <thead class="table-dark" >
            <tr><th>번호</th><th>제목</th><th>아이디</th><th>작성일</th><tr>

          </thead>
             <tbody>
                 <tr>
                   <c:forEach items="${boardList}" var="article">
             <tr>
         <td>${article.no }</td>
         <td><a href="viewArticle?no=${article.no }">${article.title }</a></td>
         <td>${article.id }</td>
         <td><fmt:formatDate pattern="yyyy/MM/dd" value="${article.writeDate }"/></td>
            </tr>
               </c:forEach>

               </tr>
          </tbody>
        </table> 
      </div>
    
  
<footer>

    <nav aria-label="Page navigation example" class="d-flex justify-content-around mt-3 ">           
       <ul class="pagination" >
         
         <li><a href="boardWriteForm.jsp"  class="btn btn-secondary btn-lg">글 작성하러 가기</a></li>
         
      </ui>         
   </nav>

  </footer>
</section>   
</center>
 </div>
</div>
	
  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>  
	
</body>
</html>