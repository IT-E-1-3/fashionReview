
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
 
      justify-content: center;
      align-items: center;
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

h2 {
  font-weight: bold;
  text-align: center;
}

aside {
  float: left;
}

section {
  float: right;
  width: 900px;
  

.nav_factor {
  text-decoration: none;
  color: white;
  margin-right: 2rem;
}

footer {
  height: 60px;
}  
}



</style>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.*"%>
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


	$("#deleteScrap").click(function(){
		var no='${scrapArticle.no }'
		if(window.confirm("스크랩박스에서 해당 게시물을 삭제 하시겠습니까?")){
			$.post('deleteScrap',{no},function(data){
				alert("스크랩이 취소되었습니다.");
				location.href="boardList";
			})
		}else{
			alert("요청이 취소되었습니다.");
		}
	
	});

	
});


</script>

</head>
<body>
 
  <nav class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
    <a href="02_home.html"><img  src="./images/logo.png"   height="50px" alt=""></img></a>
  </nav>

  <div class="main">
	<div  class="main-container">
		<h2>${scrapArticle.bid } 님의 게시글</h2>
			<table class="table table-striped mytable"
							style="text-align: center; border: 1px solid #dddddd">
				<tr><td>아이디</td><td><input value="${scrapArticle.bid }"  readonly style="background:lightgray"></td></tr>
				<tr><td>제목</td><td><input  value="${scrapArticle.title }" readonly style="background:lightgray"></td></tr>
				<tr><td>내용</td><td><textarea rows ="8" cols="20" readonly style="background:lightgray" >${scrapArticle.content}</textarea></td></tr>
				<tr><td>첨부파일</td><td><div id="div1" class="div"><a href="uploadImg/${scrapArticle.fileName }"><img src="uploadImg/${scrapArticle.fileName }" height="50"></a></div></td><tr>			
			</table>
		<footer>
			 <nav aria-label="Page navigation example" class="d-flex justify-content-around mt-3 "> 		 	
  		 		 <ul class="pagination">
					<li><button  type="button" style="background-color:#8E44AD" class="btn btn-secondary btn-lg" id="deleteScrap">스크랩 취소</button></li>
					<li><button type="button" style="background-color:#8E44AD" class="btn btn-secondary btn-lg" onclick="history.back()">뒤로 가기</button></li>			
				</ui>			
			</nav>
		</footer>
	</div>
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