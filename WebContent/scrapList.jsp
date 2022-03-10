<!DOCTYPE html>
<html lang="ko">
<head>
 <link href="/your-path-to-fontawesome/css/all.css" rel="stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<style type="text/css">
body {
      margin:10px;
      background-image: url('../img/gradient-bg.jpg');
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
    }
.main {
  width: 90%;
  height: 90%;
  background-color: rgba(255,255,255,0.45);
  padding: 20px;
}

.a_style {
  text-decoration: none;
}

h1 {
  font-weight: bold;
  text-align: center;
}
section {
 text-align:center;
  width: 900px;
}  


footer {
  height: 60px;

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
  <link rel="stylesheet" href="01_nav_footer.css">
  <link rel="stylesheet" href="03_community.css">

  <title>Community</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script type="text/javascript">	

function getCookie(cname) {
	  let name = cname + "=";
	  let decodedCookie = decodeURIComponent(window.opener.document.cookie);
	  let ca = decodedCookie.split(';');
	  for(let i = 0; i <ca.length; i++) {
	    let c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}



$(document).ready(function(){
	
	<% String ids = (String) session.getAttribute("id"); %> 
	
	
	$("#searchBtn").click(function(){
		const searchType=$("#searchType").val();
		const keyword=$("#keyword").val();
		$.post('search',{searchType,keyword},function(data){
			$('#boardTable > tbody').empty();
			console.log(data[0].no);
			if(data.length>=1){
				data.forEach(function(item){
					str='<tr>'
					str +="<td>"+item.no+"</td>";
					str+="<td><a href = 'viewArticle?no=" + item.no + "'>" + item.title + "</a></td>";
					str+="<td>" + item.id + "</td>";
					str+="<td >"+item.writeDate+"</td>";
					str+="</tr>"
					$('#boardTable').append(str);
        			})				 
				}
				
		});
	});
		

	
	});	
	

</script>   

</head>
<body>

  <nav class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
    <a href="02_home.html"><img  src="./images/logo.png"   height="50px" alt=""></img></a>
     <div class="me-2">
      <div class="collapse navbar-collapse" id="navbarNav">
     	 <ul class="navbar-nav">
          <li class="nav-item">
            <div class="nav-link text-white" id='loginOK'>  <%=ids %>님의 ScrapBox</div>
          </li>
        </ul>
       </div>
      </div>
  </nav>

<div class="main">  
 <div class="container">
     <div class="row row-cols-lg-10 mt-5">
   <h1>ScrapBox <i class="fas fa-thumbtack"></i></i></h1>

    <section>
      <div class="row row-cols-lg-10 mt-5">
	        <table class="table table-striped table-hover" id="boardTable">
	          <thead class="table-dark">
	     		 <tr><th>번호</th><th>제목</th><th>아이디</th><th>작성일</th><tr>
	          </thead>
	        	  <tbody>
	       			  <tr><c:forEach items="${scrapList}" var="scrapArticle"> <tr>
						<td>${scrapArticle.no }</td>
						<td><a href="viewScrapArticle?no=${scrapArticle.no }">${scrapArticle.title }</a></td>
						<td>${scrapArticle.bid }</td>
						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${scrapArticle.writeDate }"/></td>
					</tr></c:forEach>
	            </tr>
	          </tbody>
	        </table> 
	   </div>
		   <footer>
		       <nav aria-label="Page navigation example" class="d-flex justify-content-around mt-3 ">           
	   			    <ul class="pagination">
	 		       	    <button type="button" style="background-color:#8E44AD" class="btn btn-secondary btn-lg" onclick="history.back()">뒤로 가기</button>        
	 		     </ui>         
		 	  </nav>
			</footer>
	</section>
	</div>
 </div>
</div>
  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>  

</body>
</html>