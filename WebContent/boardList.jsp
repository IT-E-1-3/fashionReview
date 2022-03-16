<!-- 

   작성자: 김보경, 차성호

 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,com.dao.*,com.vo.*" pageEncoding="UTF-8"%>

<%
   //한글 인코딩
    request.setCharacterEncoding("UTF-8");
 
    //세션에 저장된 id값 불러오기
    String id = (String)session.getAttribute("id");
    
    BoardVO board = new BoardVO();    
    BoardDAO boardDAO = new BoardDAO();
    
    //카테고리 체크된 값 가져오기
    String check = (String)session.getAttribute("category");
    String select = (String)session.getAttribute("selectOption");
    
    
    ArrayList<BoardVO> boardList =new ArrayList<>();
    
    //카테고리 선택 NullPointException 방지
    if((check==null || check.equals(""))&&(select==null || select.equals(""))){
       boardList = boardDAO.selectAllBoard();
 

 
    }else{
       //세션에 저장한 boardList값 불러오기
       boardList = (ArrayList<BoardVO>)session.getAttribute("boardList");
 
    }
    
    

    
 %>
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
   display: flex;
   justify-content: center;
   align-items: center;
}

.searchDiv {
   text-align: right;
}

.colorset {
   background-color: rgba(255, 255, 255, 0.36);
}

.main {
   width: 90%;
   height: 90%;
   background-color: rgba(255, 255, 255, 0.45);
   padding: 20px;
}
</style>
<!--jquery lib-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
   integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
   crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- my.js 파일 사용을 위한 스크립트 -->
<script src="js/my.js"></script>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page session="true"%>
<!-- 현재 페이지를 세션에 추가해줌 -->

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap CSS -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
   crossorigin="anonymous">

<title>Community</title>

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script type="text/javascript">

</script>

</head>
<body>

   <nav
      class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md fixed-top "
      style="background-color: lightgray">
      <div style="display: flex; flex-direction: horizontal">
         <img src='assets/img/logo.png' height="50px" width="50px"
            style="margin-left: 10px; margin-right: 10px"></img>
         <h1>
            Community <i class="fas fa-bullhorn fa-x"></i>
         </h1>
      </div>
      <div class="me-2 ">
         <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
               <li class="nav-item"></li>
            </ul>
         </div>
      </div>
   </nav>
   <!-- 03_community.html -->
   <div class="main">

      <div class="main-container">


         <!-- Board -->
         <br> <br>
         <div class="searchDiv" style="margin-top: 50px">
            <div class="row row-cols-lg-1 mt-5" style="float: left">
                <!-- 선택버튼 클릭시 SelectCategoryServlet으로 post 매핑 -->
               <form method="post" action="selectCategory">
                  <div>
                     <label><input type="checkbox" name="choice" value="top">
                        상의</label> <label><input type="checkbox" name="choice"
                        value="bottom"> 하의</label> <label><input type="checkbox"
                        name="choice" value="shoes"> 신발</label> <label><input
                        type="checkbox" name="choice" value="Accessories"> 악세사리</label>
                     <input type="submit" class="btn btn-secondary" value="선택" />
                  </div>
               </form>
            </div>
            <!-- 검색버튼 누를시 SelectBy 서블릿으로 post 매핑 -->
            <form method="post" action="selectBy">
               <select id="searchType" name="selectOption" style="height: 35px;">
                  <!-- 선택한 옵션 값에 따라 게시글 필터링 -->
                  <option value="" selected>검색조건</option>
                  <option value="title">제목</option>
                  <option value="id">아이디</option>
               </select> <input type="text" id="keyword" name="keyword"
                  style="width: 230px; height: 35px" placeholder="제목/아이디를 입력해주세요." />
               <input style="margin-bottom: 4px" type="submit" id="searchBtn"
                  class="btn btn-secondary " value="검색" />
            </form>

         </div>
         <!-- 등록된 게시글 영역 -->
         <section>
            <div class="row row-cols-lg-10 mt-5">
               <table class="table table-striped table-hover" id="boardTable">

                  <thead class="table-dark">
                     <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>아이디</th>
                        <th>작성일</th>
                     <tr>
                  </thead>
                  <tbody>

                     <%for(BoardVO boardVO:boardList) {%>
                     <tr>
                        <td><%= boardVO.getBoard_id() %></td>
                        <!-- getBoard_id() 값에 따른 게시글 display -->
                        <td><a
                           href="viewArticle.jsp?no=<%= boardVO.getBoard_id() %>"><%=boardVO.getTitle() %></a></td>
                        <td><%=boardVO.getUser_id() %></td>
                        <!-- 작성날짜 반환 -->
                        <td><fmt:formatDate pattern="yyyy/MM/dd"
                              value="<%=boardVO.getWrite_date() %>" /></td>
                     </tr>
                     <%} %>


                  </tbody>
               </table>
            </div>


            <footer>

               <nav aria-label="Page navigation example"
                  class="d-flex justify-content-around mt-3 ">
                  <ul class="pagination">

                     <li><a href="boardWriteForm.jsp"
                        class="btn btn-secondary btn-lg">글 작성하러 가기</a></li>

                  </ul>
               </nav>


               <nav aria-label="Page navigation example"
                  class="d-flex justify-content-around mt-3">
                  <ul class="pagination">
                     <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                     <li class="page-item"><a class="page-link" href="#">1</a></li>
                     <li class="page-item"><a class="page-link" href="#">2</a></li>
                     <li class="page-item"><a class="page-link" href="#">3</a></li>
                     <li class="page-item"><a class="page-link" href="#">Next</a></li>
                  </ul>
               </nav>
         </section>
      </div>


   </div>


   <!-- Bootstrap JS -->
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
      crossorigin="anonymous"></script>

</body>
</html>