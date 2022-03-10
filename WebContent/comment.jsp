<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="/your-path-to-fontawesome/css/all.css" rel="stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


<script type="text/javascript">
//메인화면 id 가져오기
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


var bno =${article.no}; //게시글 번호

//댓글 등록 버튼 클릭시 
$("#commentInsertBtn").click(function(){ 
   
	const insertData = $("#content").val();
    commentInsert(insertData); //Insert 함수호출
});
 
 
//댓글 목록 
function commentList(){
	
    $.ajax({
        url : 'commentlist',
        type : 'get',
        data : {'bno':bno},
        cache: false,
        dataType: "json",
   
        
        success : function(data){
        	
			  var a =''; 
          data.commentList.forEach(function(item,index){
          	  a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
          	  a += '<br>';
                a += '<div><i class="far fa-grin-wink fa-2x"></i> :'+item.writer+'님의 댓글</div>';
                a += '<br>';
                a += '<div class="commentContent'+item.cno+'"> <p> : '+item.content +'</p>';
                a += '<span style="display: inline-block; width: 1000px; background-color: burlywood;"></span>'
                a += '<a  class="btn btn-outline-dark" onclick="commentUpdate('+item.cno+',\''+item.content+'\');"> 수정 </a>';
                a += '<a class="btn btn-outline-dark" onclick="commentDelete('+item.cno+',\''+item.writer+'\');"> 삭제 </a> </div>';
                a += '</div></div>';
          });
      
          $(".commentList").html(a);
      },
   
    });
}  

//댓글 등록
function commentInsert(insertData){

    		const id=getCookie("id");
    		
    if(window.confirm("댓글을 등록하시겠습니까?")){
	        $.post('commentInsert',{insertData,id},function(){		 	
			    
	    	    alert("댓글이 등록되었습니다.");
	    		commentList();
	    		$("#content").val('');
	   			 }); 
    	}else{
		    	alert("등록이 취소되었습니다.");
		    	$("#content").val('');
   	 }
		 
}

//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(cno,content){

  	var id=getCookie("id");
  	var a ='';
   
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" id="updateContent" name="content_'+cno+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+cno).html(a);

}

 //댓글 수정
 function commentUpdateProc(cno){
  
    var updateContent=$("#updateContent").val();
		if(window.confirm("댓글을 수정하시겠습니까?")){
			    $.post('commentUpdate',{updateContent,'cno':cno},function(data){
					alert(data);
					commentList(bno);						
				})
		}else{
			alert("수정이 취소되었습니다.");
			$("#updateContent").val(updateContent);
			commentList(bno);
		}
} 
 
//댓글 삭제 
function commentDelete(cno,writer){
	
	 const id=getCookie("id");
	 if(window.confirm("댓글을 삭제하시겠습니까?")){
			if(writer==id){
					 $.post('commentDelete',{cno},function(){
						alert("댓글이 삭제되었습니다.");
						 commentList(bno);
					 }); 
				}else{
					
				alert("작성자 본인만 삭제가 가능합니다.");
			}
	 }else{
		 alert("취소되었습니다.");
	 }
	
} 


 $(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력 
});

 
</script>
</head>
<body>
<div>

</div>
</body>
</html>