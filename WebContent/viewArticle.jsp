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
      
    }
.main {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.main-container{
  width: 90%;
  height: 90%;
  background-color: rgba(255,255,255,0.45);
  padding: 10px;
}
.a_style {
  text-decoration: none;
}
h1 {
  font-weight: bold;
  text-align: center;
}
aside {
  float: left;
}
section {
  float: right;
  width: 900px;
  
}
.nav_factor {
  text-decoration: none;
  color: white;
  margin-right: 2rem;
}
footer {
  height: 60px;
}  
.feeling_div {
  display: flex;
  justify-content: center;
  align-items: center;
} 
.button-container {
  margin-left: 20px;
}
.feeling_a {
  background-color: #fff;
  border: 2px solid #F08080;
  padding: 10px 20px;
  border-radius: 2px;
  color: #F08080;
}     
.feeling_b {
  background-color: #fff;
  border: 2px solid #F08080;
  padding: 10px 20px;
  border-radius: 2px;
  color: #F08080;
}     
	
.articleDiv{
text-align: right;
}
.active {
  background-color: #F08080;
  color: #fff;
}
</style>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dao.*,com.vo.*"%>
<%@ page session="true" %> 
<% String ids = (String) session.getAttribute("id"); 

	BoardVO board = new BoardVO();
	BoardDAO boardDAO = new BoardDAO();
	board = boardDAO.selectPost(request.getParameter("no"));
	
%> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<!-- Custom CSS -->
  <link rel="stylesheet" href="01_nav_footer.css">
  <link rel="stylesheet" href="03_community.css">



<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="css/jquery.lightbox.css" media="screen" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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
	
	$("#deleteBtn").click(function(){
	var id=getCookie("id");
	if(window.confirm("해당 게시글을 삭제하시겠습니까?")){
		$.post('delete',{id},function(data){
			if(data=="ok"){
				alert("게시글이 삭제되었습니다.");
				location.href="deleteOK.html";
			}else{
				alert("작성자 본인만 수정/삭제가 가능합니다.");
			 location.href = "deleteFail.html";}
			
	});
	}else{
		alert("취소 되었습니다.");
	}
	});
	
	
	$("#updateBtn").click(function(){
		var id = getCookie("id");
		var title='${article.title}';
		var content='${article.content}';
		if(window.confirm("해당 게시글을 수정하시겠습니까?")){
			if(id=='${article.id}'){
				$.post('updateArticleForm',{id,title,content},function(){
					location.href="updateArticleForm";
				});
			}else{
				alert("작성자 본인만 수정/삭제가 가능합니다.");
			}
		}else{
			alert("취소 되었습니다.");
		}
	});
	
	$('.like-container > .feeling_a, .dislike-container  > .feeling_a').click(function(){
	
	    event.preventDefault();
	    $('.active').removeClass('active');
	    $(this).addClass('active');
	});
	
	
	$("#scrapBtn").click(function(){
		var cookieId = getCookie("id");
		var id='${article.id}';
		var no = '${article.no}';
		
		if(window.confirm("스크랩 하시겠습니까?")){
			$.post('scrapArticle',{no,id,cookieId},function(data){
				alert("스크랩 되었습니다.");				
			})			
		}else{
			alert("스크랩처리가 취소 되었습니다.")
		}		
	});
	
});
</script>


</head>
<body>
 <nav class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
    <a href="02_home.html"><img  src="./images/logo.png"   height="50px" alt=""></img></a> 
  </nav>
  <!-- 03_community.html -->
<div class="main">
  <div class="main-container">
	<h2  style="text-align:center"><%=board.getUser_id()%>님의 게시글</h2>
<br>	
<br>

<form action="updatePost" method="post">
<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
	<tr><td>아이디</td><td><input type="text" value="<%=board.getUser_id() %>" name="user_id" readonly></td></tr>
	<tr><td>제목</td><td><input value="<%=board.getTitle() %>" name="title"></td></tr>

	<tr><td>내용</td><td><input  style="height:100px" value="<%=board.getContent() %>" name="content" ></td></tr>
	<tr><td>첨부파일</td><td><div id="div1" class="div"><a href="getImg/${article.fileName }"><img src="getImg/${article.fileName }" height="50"></a></div></td><tr>



</table>
		 <input type='hidden' value="<%=board.getBoard_id() %>" name="board_id">

		<div class="articleDiv">
		<input type="submit" id="updateBtn"  class="btn btn-secondary" value="수정">
		 &nbsp;
		 </form>
		 <form action="deletePost" method="post">
		 <input type='hidden' value="<%=board.getBoard_id() %>" name="board_id">
		  <input type="submit" class="btn btn-secondary" value="삭제">
	   </form>
	   </div>
	   </div>
<br><br>
</div>
</div>
	 	<div class="feeling_div">
			<div class="button-container like-container">
			    <button class="feeling_a">
			      <i class="fa fa-heart"> Like</i>    
			    </a>
			  </div>
			  <div class="button-container dislike-container">
			    <button class="feeling_a">
			      <i class="far fa-sad-tear"> Dislike</i>
			    </a>
			</div>
			<div class="button-container dislike-container">
			    <button class="feeling_b" id="scrapBtn">
			     <i class="far fa-star"> 스크랩</i>
			    </a>
			</div>
			
		</div> 
	<br>
	<br>
	<h3 style="text-align:center"><i style="color:salmon" class="far fa-comments  fa-4x"></i></h3>



  <!--  댓글  -->
    <div class="container">
        <label for="content">comment</label>
            <div class="input-group" id='input'>
               <input type="hidden" name="bno" value="${article.no}"/>              
               <input type="text" class="form-control" id="content" name="content" placeholder="댓글을 입력해주세요.">
               <span class="input-group-btn">
                    <button class="btn btn-dark" id="commentInsertBtn">등록</button>
               </span>
              </div>
			<div class="commentList" id="commentList"></div>
		</div>

<%@ include file="comment.jsp" %>


<footer>
	 <nav aria-label="Page navigation example" class="d-flex justify-content-around mt-3 "> 		 	
 		  <ul class="pagination">		
			<li><button type="button" class="btn btn-secondary btn-lg" href="boardList.jsp">목록으로 가기</button></li>		
		</ui>			
	</nav>
</footer>

</div>

<script type="text/javascript" src="js/jquery.1.2.3.js"></script>
<!-- Add jQuery lightBox plugin script to the end of the DOM -->
<!-- Make sure there is a jQuery framework, loaded, before the plugin script loads -->
<script type="text/javascript" src="js/jquery.lightbox.min.js"></script>
<!-- Activate the jQuery lightBox plugin -->

<script type="text/javascript">
$(function() {
	$('#div1 a').lightBox();
});
</script>

</body>
</html>