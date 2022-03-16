<!-------------------

 작성자: 김보경, 차성호 

 -------------------->
<!DOCTYPE html>
<html lang="ko">
<head>

<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
   rel="stylesheet">
<style type="text/css">
body {
   margin: 10px;
   background-size: cover;
}

.main {
   width: 100%;
   display: flex;
   justify-content: center;
   align-items: center;
}

.main-container {
   width: 90%;
   height: 90%;
   background-color: rgba(255, 255, 255, 0.45);
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

.articleDiv {
   text-align: right;
}

.active {
   background-color: #F08080;
   color: #fff;
}
</style>
<!-- 한글 설정 및 세션 설정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
   import="com.dao.*,com.vo.*,java.util.*,java.time.*"%>
<%@ page session="true"%>
<%
String ids = (String) session.getAttribute("id");

BoardVO board = new BoardVO();
BoardDAO boardDAO = new BoardDAO();
board = boardDAO.selectPost(request.getParameter("no"));

ReplyDAO replyDAO = new ReplyDAO();
ArrayList<ReplyVO> replyList = new ArrayList<>();
replyList = replyDAO.selectAllReply(request.getParameter("no"));
%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- Bootstrap CSS -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
   crossorigin="anonymous">

<!--jquery lib-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript"
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script type="text/javascript">
   $(document).ready(function() {
      // id 쿠키값 설정

      const id = $.cookie("id");
      //const id = 'tjdgh6273';
      const board_user_id = $("#board_user_id").val();

      // id와 게시글 쓴 id가 다르면 버튼의 가시성 유무, 제목과 내용부분 readonly처리
      if (id != board_user_id) {
         $("#updateBtn").attr("style", "visibility:hidden");
         $("#deleteBtn").attr("style", "visibility:hidden");
         $("#title").attr("readonly",true);
         $("#content").attr("readonly",true);
      } else {

         $("#updateBtn").attr("style", "visibility:visible");
         $("#deleteBtn").attr("style", "visibility:visible");
         $("#title").attr("readonly",false);
         $("#content").attr("readonly",false);
      }

   });
</script>

<title>Insert title here</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



</head>
<body>
   <nav
      class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">

   </nav>

   <div class="main">
      <div class="main-container">
      <!-- 게시글 작성한 사용자의 id값 반환 --> 
         <h2 style="text-align: center"><%=board.getUser_id()%>님의 게시글
         </h2>
         <br> <br>
      <!-- 수정 버튼을 누를시 BoardUpdateServlet으로 post 매핑 -->
         <form action="updatePost" method="post"
            onsubmit="return chkchk(this);">
            <table class="table table-striped"
               style="text-align: center; border: 1px solid #dddddd">
               <!-- 게시글의 아이디, 제목, 내용, 첨부파일 값 boardDAO에서 불러오기 -->
               <tr>
                  <td>아이디</td>
                  <td><input style="width: 400px;" type="text"
                     value="<%=board.getUser_id()%>" name="user_id"
                     id="board_user_id" readonly></td>
               </tr>
               <tr>
                  <td>제목</td>
                  <td><input style="width: 400px;"
                     value="<%=board.getTitle()%>" name="title" id="title"></td>
               </tr>

               <tr>
                  <td>첨부파일</td>
                  <td><div id="div1" class="div">
                        <img src="<%=board.getPicture()%>" height="400px" width="300px"
                           id="picture">
                     </div></td>
               </tr>
               <tr>
                  <td>내용</td>
                  <td><input style="width: 400px; height: 250px"
                     value="<%=board.getContent()%>" name="content" id="content"></td>
               </tr>

               <tr>
                  <td>카테고리</td>
                  <!-- 기존 선택한 카테고리 항목 -->
                  <td><input type="text" value="<%=board.getCategory()%>"
                     readonly> <label><input type="checkbox"
                        name="category" value="top" id="category"> 상의</label> <label><input
                        type="checkbox" name="category" value="bottom"> 하의</label> <label><input
                        type="checkbox" name="category" value="shoes"> 신발</label> <label><input
                        type="checkbox" name="category" value="Accessories">
                        악세사리</label> <script type="text/javascript">
                           // 체크박스 체크 validate (체크박스 유효성 검증)

                           function check(form) {
                              var arr_form = document
                                    .getElementsByName('category');
                              var num = 0;
                              for (var i = 0; i < arr_form.length; i++) {
                                 if (arr_form[i].checked) {
                                    num++;
                                 }
                              }
                              //카테고리 선택 필수 처리
                              if (!num) {
                                 alert('카테고리를 하나 이상 선택해주시기 바랍니다');
                                 return false;
                              }
                           }
                        </script></td>
               </tr>
            </table>
            <input type='hidden' value="<%=board.getBoard_id()%>"
               name="board_id">

            <div class="articleDiv">
               <div style="display: flex; float: right;">
               <!-- 수정 버튼 누를시 BoardUpdateServlet으로 post 매핑 -->
                  <input type="submit" id="updateBtn" class="btn btn-secondary"
                     value="수정"> &nbsp;
         </form>
         <!-- 삭제 버튼 누를시 BoardDeleteServlet으로 post 매핑 -->
         <form action="deletePost" method="post">
         <!-- 게시글을 쓴 작성자의 id에 따라 가시성 유무 -->
            <input type='hidden' value="<%=board.getBoard_id()%>"
               name="board_id"> <input type="submit" id="deleteBtn"
               class="btn btn-secondary" value="삭제">
         </form>
      </div>
   </div>
   </div>
   <br>
   <br>
   </div>
   </div>
   <br>
   <br>
   <h3 style="text-align: center">
      <i style="color: salmon" class="far fa-comments  fa-4x"></i>
   </h3>



   <!--  댓글 영역  -->
   <table class="table table-striped table-hover" id="boardTable">

      <thead class="table-dark">
         <tr>
            <th style="width: 15%;">user</th>
            <th style="width: 70%;">내용</th>
            <th>작성날짜</th>
            <th></th>
         <tr>
      </thead>
      <tbody>



         <tr>
         <!-- 작성 버튼 누를시 ReplyInsertServlet으로 post 매핑 -->
            <form action="insertComment" method="post">
            
               <input type="hidden" name="board_id" value="<%=board.getBoard_id()%>" />
               <!-- 댓글 쓸 작성자 id 띄움 -->
               <td><input type="text" class="form-control" name="user_id" value="<%=ids%>" readonly></td>
               <!-- 댓글 작성한 id -->
               <td><input type="text" class="form-control" id="content" name="content" placeholder="댓글을 입력해주세요."></td>
               <!--  현재 날자 불러오기 -->
               <td><%LocalDate now = LocalDate.now();%> <%=now%></td>
               <!-- 작성 버튼 누를시 ReplyInsertServlet으로 post 매핑 -->
               <td><input type="submit" value="작성" class="btn btn-secondary"></td>
            </form>
         </tr>
         <%
         //for문으로 댓글 리스트 정렬
         for (ReplyVO reply : replyList) {
         %>
         <!-- 삭제 버튼 누를시 ReplyDeleteServlet으로 post 매핑 -->
         <form action="deleteComment" method="post">
            <tr>
               <input type="hidden" name="reply_id" value="<%=reply.getReply_id()%>">
               <input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
               <input type="hidden" value="<%=reply.getUser_id()%>" readonly>
               <td><%=reply.getUser_id()%></td>
               <td><%=reply.getContent()%></td>
               <td><%=reply.getReply_date()%></td>
               <%
               String condition;
               //session에 저장된 id와 댓글 작성한 id가 다를때 버튼 가시성
               if (session.getAttribute("id").equals(reply.getUser_id())) {
                  condition = "visible";
               } else {
                  condition = "hidden";
               }
               %>
               <td><input style="visibility:<%=condition%>" type="submit" value="삭제" class="btn btn-secondary"></td>
         </form>
         </tr>
         <%
         }
         %>
      </tbody>
   </table>


   <footer>
      <nav aria-label="Page navigation example"
         class="d-flex justify-content-around mt-3 ">
         <ul class="pagination">
         <!-- boardList.jsp로 페이지 전환 -->
            <li><button class="btn btn-secondary btn-lg"
                  onclick='location.href="boardList.jsp"'>목록으로 가기</button></li>
            </ui>
      </nav>
   </footer>

   </div>

</body>
</html>