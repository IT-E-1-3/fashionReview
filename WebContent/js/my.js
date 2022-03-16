$(document).ready(function(){
	//cookie에서 key값이 id인 value값을 id에 저장
	const id = $.cookie("id");
	if (id) {
		//id가 존재하면 loginDiv에 로그인정보 출력
		$("#loginDiv").html("<div class='text-white' >"+id + "님 환영합니다.</div>"); 
	} 
	//loginBtn이 클릭되면
	$("#loginBtn").click(function(){
		//id="id"와 id="pw"인 값들을 각각 변수에 저장
		const id = $("#id").val();
		const pw = $("#pw").val();
		//입력된 id값이 없을경우 alert출력
		if(id==null || id==""){
			alert("아이디값은 필수입니다.");
		//입력된 pw값이 없을 경우 alert출력
		}else if(pw==null || pw==""){
			alert("비밀번호값은 필수입니다.");
		//id, pw 둘 다 입력값이 있을 경우 login기능 수행
		}else{
			$.post('../login',{id,pw},function(data){	
				//받아온 json데이터에서 id가 존재할경우 즉 table에 유저정보가 있을 경우 로그인 실행
				if(data.id){
					//cookie의 id를 key값을 갖는 value값에 data.id를 저장
					$.cookie("id",data.id);
					//index.jsp로 페이지 전환
					location.href="index.jsp";
					//loginDiv에 로그인정보 출력
					$("#loginDiv").html("<div class='text-white' >"+data.id + "님 환영합니다.</div>")
				//받아온 json데이터에서 id가 존재하지 않을 경우 즉 table에 유저정보가 없을 경우 alert로 data.msg출력
				}else{
					alert(data.msg);
				}
			});
		}	
	});
	//logoutBtn버튼이 눌리면 쿠키에서 id값 삭제
	$("#logoutBtn").click(function() {
		$.removeCookie("id");	
	});
	
});