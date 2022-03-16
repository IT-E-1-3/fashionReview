<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,com.dao.*,com.vo.*" pageEncoding="UTF-8"%>
<!-- 김보경, 차성호: 세션에서 id를 받아와 로그아웃할 때 이용 -->
<%
    request.setCharacterEncoding("UTF-8");

    String id = (String)session.getAttribute("id");%> 


<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="apple-touch-icon" sizes="76x76"
   href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
   href="assets/img/favicon.png">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>FIP - Fashion Information Platform</title>
<meta
   content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
   name='viewport' />
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/gaia.css" rel="stylesheet" />

<!--jquery lib-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
   integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
   crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!--     Fonts and icons     -->
<link
   href='https://fonts.googleapis.com/css?family=Cambo|Poppins:400,600'
   rel='stylesheet' type='text/css'>
<link
   href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
   rel="stylesheet">
<link href="assets/css/fonts/pe-icon-7-stroke.css" rel="stylesheet">

<script src="js/my.js"></script>

<script type="text/javascript"
   src="https://www.gstatic.com/charts/loader.js">
</script>
</head>
<script type="text/javascript">
	
	google.charts.setOnLoadCallback(drawChart1);
	function drawChart1() {
		//2번째 차트
		var data1 = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses', 'Profit' ],
				[ '2014', 1000, 400, 200 ], [ '2015', 1170, 460, 250 ],
				[ '2016', 660, 1120, 300 ], [ '2017', 1030, 540, 350 ] ]);

		//차트 옵션 설정
		var options1 = {
			chart : {
				title : 'Company Performance',
				subtitle : 'Sales, Expenses, and Profit: 2014-2017',
				'width' : 400,
				'height' : 300
			}
		};

		//차트 객체 생성
		var chart1 = new google.charts.Bar(document
				.getElementById('chart_div2'));
		//파이 차트 그르기 데이터, 옵션
		chart1.draw(data1, google.charts.Bar.convertOptions(options1));

	}
</script>

<body>
   <nav class="navbar navbar-default navbar-transparent navbar-fixed-top"
      style="background-color: white">
      <!-- if you want to keep the navbar hidden you can add this class to the navbar "navbar-burger"-->
      <div class="container">
         <div class="navbar-header">

            <button id="menu-toggle" type="button" class="navbar-toggle"
               data-toggle="collapse" data-target="#example">
               <span class="sr-only">Toggle navigation</span> <span
                  class="icon-bar bar1"></span> <span class="icon-bar bar2"></span>
               <span class="icon-bar bar3"></span>
            </button>
            <img width="50px" height="50px" src='assets/img/logo.png'
               style="margin-top: 10px" />
         </div>
         <a class="navbar-brand"
            style="margin-left: 5px; color: black; font-size: 30px; font-family: fantasy;">
            F I P </a>
         <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right navbar-uppercase">
               <li style="margin-top: 25px; margin-right: 5px">
                  <div id="loginDiv">
                  <!-- 김보경, 차성호: id값 받아옴 -->
                     <%=id%> welcome
                  </div>
               </li>

               <li>
               <!-- 김보경, 차성호: LogoutServlet으로 post로 매핑 -->
                  <form method="post" action="logout">
                     <input type="submit" class="btn btn-danger btn-fill" value="로그아웃" />
                  </form>
               <li>
               <!-- 김보경, 차성호: 멤버쉽 정보를 담고 있는 마이페이지로 새 브라우저 창 띄우기 -->
                  <button class="btn btn-danger btn-fill"
                     onclick='window.open("myPage.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=1200,height=800");'>마이페이지</button>
               </li>
            </ul>
         </div>
         <!-- /.navbar-collapse -->
      </div>
   </nav>
   <div class="section section-header">
      <div class="parallax filter filter-color-black">
         <div class="image"
            style="background-image: url('assets/img/header-1.jpg')"></div>

         <div class="container">
            <div class="content">
               <div class="title-area">
                  <h1 class="title-modern">Fashion Information Platform</h1>

                  <div class="separator line-separator">♦</div>
                  <h3>Fashion is what you make of it!</h3>
               </div>

               <div class="button-get-started">
               <!-- 김보경, 차성호: 게시판 페이지로 새 브라우저 창 띄우기 -->
                  <a class="btn btn-white btn-fill btn-lg"
                     onclick='window.open("boardList.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=1200,height=800");'>
                     Community </a>
               </div>
            </div>

         </div>
      </div>
   </div>
   <div class="section section-our-team-freebie"
      style="background-color: #717171; padding: 0px 0px 0px 0px;">
      <div class="parallax filter filter-color-black">
         <div class="image" style="background-color: white"></div>
         <div class="container">
            <div class="content">
               <div class="row">
                  <div class="title-area">
                     <h2>Analysis Charts</h2>
                     <div class="separator separator-danger">✻</div>

                  </div>
               </div>
               <div class="team">
                  <div class="row">
                     <div class="col-md-10 col-md-offset-1">
                        <div class="row">
                           <div class="col-md-4" style="width: 460px;">
                              <div class="card card-member">
                                 <div class="content">

                                    <div class="description" id='chart_div'></div>
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4" style="width: 460px;">
                              <div class="card card-member">
                                 <div class="content">


                                    <div class="description" id='chart_div2'
                                       style="height: 300px;"></div>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <footer class="footer footer-big footer-color-black"
         data-color="black">
         <div class="container">
            <hr>
            <div class="copyright">
               ©
               <script> document.write(new Date().getFullYear()) </script>
               Creative 3Team, 현대 it&E 3기
            </div>
         </div>
      </footer>
</body>
<!--   core js files    -->
<script src="assets/js/jquery.min.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.js" type="text/javascript"></script>

<!--  js library for devices recognition -->
<script type="text/javascript" src="assets/js/modernizr.js"></script>

<!--  script for google maps   -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<!-- 필요 라이브러리 끝-->
	
	<script type="text/javascript">
		//db 데이터 저장 객체
		var queryObject = "";
		//db 데이터 저장 객체 객수 저장
		var queryObjectLen = "";
		

		$.ajax({
			type : 'GET',
			url : 'http://localhost:8090/getData/SelectByCategory',
			dataType : 'json',
			success : function(data) {				 
				queryObject = data;
				//queryObjectLen = 4;
			},
			error : function(xhr, type) {
				alert('server error occoured')
			}
		});//ajax 데이터 로드 끝

		//구글 그래프 그리기 시작
		google.load("visualization", "1", {
			packages : [ "corechart" ]
		});
		google.setOnLoadCallback(drawChart);

		const category = ["top","bottom","shoe","acc"];
		function drawChart() {
			var data = new google.visualization.DataTable();
			// 칼럼 추가
			data.addColumn('string', 'category');
			data.addColumn('number', 'count');

			// 설정된 칼럼에 대응하는 행 추가, 현재의 경우 TOP 3 이므로 3행이다.
			console.log(queryObject);
			data.addRows([ [ "top", queryObject.top ], ["bottom",queryObject.bottom]
			,["shoe",queryObject.shoe],["accessaries",queryObject.acc]]);
			

			var options = {
				title : '카테고리별 게시물 수',
			};

			var chart = new google.visualization.PieChart(document
					.getElementById('chart_div'));

			chart.draw(data, options);
		}//drawChart() end 그래프 그리기 끝
	</script>
	<script type="text/javascript">
		// ######################### 데이터 요청과 수신 시작
		//db 데이터 저장 객체
		var queryObject1 = "";
		//db 데이터 저장 객체 객수 저장
		var queryObjectLen = "";

		$.ajax({
			type : 'GET',
			url : 'http://localhost:8090/getData/Top5_point',
			dataType : 'json',
			success : function(data) {
				queryObject1 = eval('(' + JSON.stringify(data) + ')');
				queryObjectLen = queryObject1.members.length;
			},
			error : function(xhr, type) {
				alert('server error occoured')
			}
		});//ajax 데이터 로드 끝	
		// ######################### 데이터 요청과 수신 끝
		

		// 구글 오픈 API에서 차트 객체 로드
		google.charts.load('current', {
			'packages' : [ 'bar' ]
		});
		// Google Visualization API  로드시 callback 사용할 콜백 함수 설정.
		google.charts.setOnLoadCallback(drawChart);

		function drawChart() {
			// 설정된 칼럼에 대응하는 행 추가, 현재의 경우 TOP 5 이므로 5행이다.
			var arr1=['Top 5 rankers of m_point'];
			var arr2=[' '];
			
			for (var i = 0; i < queryObjectLen; i++) {
				var id = queryObject1.members[i].id;
				var point = queryObject1.members[i].point;
				
				arr1.push(id);
				arr2.push(point);
			}
			console.log(queryObject1);
			
			//1번째 행이 열이름 나머지는 데이터 배열 데이터 테이블
			//csv나 R과의 연동을 위해
			var data = google.visualization.arrayToDataTable([
				arr1,
				arr2 ]);

			//차트 옵션 설정
			var options = {
				chart : {
					title : 'Top5 rankers of m_point',
				}
			};

			//차트 객체 생성
			var chart = new google.charts.Bar(document
					.getElementById('chart_div2'));
			//파이 차트 그르기 데이터, 옵션
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
	</script>
	<!--pie chart_version 1_가 그려지기 위한 js 끝-->
	
	<!--bar chart가 실제로 그려질 부분 끝-->
<!--   file where we handle all the script from the Gaia - Bootstrap Template   -->
<script type="text/javascript" src="assets/js/gaia.js"></script>
</html>