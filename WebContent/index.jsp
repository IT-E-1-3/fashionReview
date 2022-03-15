<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.dao.*,com.vo.*" pageEncoding="UTF-8"%>
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
      // 구글 오픈 API에서 차트 객체 로드 
      google.charts.load('current', {'packages':['corechart']});
      
      // Google Visualization API  로드시 callback 사용할 콜백 함수 설정.
      google.charts.setOnLoadCallback(drawChart);
      // 이 함수에서 데이터 설정 및 차트를 그린다.
      function drawChart() {
        // 구글 차트는 데이터 테이블이라는 객체로 차트의 데이터를 전달한다.
        var data = new google.visualization.DataTable();
        //열 설정 2개를 설정하고 데이터 타입, 열이름
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        //행 추가 5개의 행을 추가한다. 
        //파이차트는  전체 합의 비율로 표시
        data.addRows([
          ['Mushrooms', 3],
          ['Onions', 2],
          ['Olives', 1],
          ['Zucchini', 1],
          ['Pepperoni', 3]
        ]);
        // chart 옵션 설정 범례, 가로 세로 
        var options = {'title':'Number of posts by fashion category',
                       'width':400,
                       'height':300};
        // 파이 차트 객체 생성 및 div 태그에 내용 전달
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        //차트 그리고 태그, 옵션
        chart.draw(data, options);
      }
        google.charts.load('current', {'packages':['bar']});
        google.charts.setOnLoadCallback(drawChart1);
        function drawChart1() {
        //2번째 차트
        var data1 = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses', 'Profit'],
            ['2014', 1000, 400, 200],
            ['2015', 1170, 460, 250],
            ['2016', 660, 1120, 300],
            ['2017', 1030, 540, 350]
          ]);
          
      //차트 옵션 설정
          var options1 = {
            chart: {
              title: 'Company Performance',
              subtitle: 'Sales, Expenses, and Profit: 2014-2017',
              'width':400,
              'height':300
            }
          };
      
          //차트 객체 생성
          var chart1 = 
            new google.charts.Bar(document.getElementById('chart_div2'));
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
							<%=id%> welcome
						</div>
					</li>

					<li>
						<form method="post" action="logout">
							<input type="submit" class="btn btn-danger btn-fill" value="로그아웃" />
						</form>
					<li>
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
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js"></script>

<!--   file where we handle all the script from the Gaia - Bootstrap Template   -->
<script type="text/javascript" src="assets/js/gaia.js"></script>

</html>