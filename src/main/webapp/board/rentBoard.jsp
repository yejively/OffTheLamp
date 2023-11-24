<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<link rel="stylesheet" href="./css/rentBoard.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
			var select = "<option value='start' class ='start1'>선택하세요</option>";
			$.ajax({
				url : "./CinemaSelectAction.bo",
				dataType : "json",
				success : function(data){
					
					$.each(data,function(idx,item){
						$('#region_name').append("<option value="+item.region+">"+item.region+"</option>");
					});//each
					
					$('#region_name').change(data,function(){
						$("#cinema_name").empty();
						var re = $("select#region_name option:checked").val();

						$.each(data,function(idx,item){
							if(item.region == re){
								var list = item.cinema_list.split(",");
								console.log(list);
								for(var i = 0; i < list.length; i++){
								$("#cinema_name").append("<option value="+list[i]+">"+list[i]+"</option>");
								}//for
							}//if
							});//each	
						});//change
				}//success
			});//ajax 끝
		}); //JQuery 끝
	</script>
<script type="text/javascript">
		function cancel(){
			// 대관문의 작성 취소 
			if(confirm("대관문의 작성을 취소하시겠습니까?")){
				location.href = "./rentMain.bo";
			}//if
		}//cancel
		function checkForm(){
			var form = document.rentForm;
			if(form.ruser_id.value == ""){
				alert("아이디를 입력해주세요");
				form.ruser_id.focus();
				return false;
			}else if(form.rname.value == ""){
				alert("이름 또는 기업명을 입력해주세요");
				form.rname.focus();
				return false;
			}else if(form.region.value == "start" || form.cinema.value == "start"){
				alert("지역 및 극장을 선택해주세요");
				return false;
			}else if(form.rphone.value == ""){
				alert("휴대폰 번호를 입력해주세요");
				form.rphone.focus();
				return false;
			}else if(form.remail.value == ""){
				alert("이메일 주소를 입력해주세요");
				from.remail.focus();
				return false;
			}else if(form.rsubject.value == ""){
				alert("제목을 입력해주세요");
				form.rsubject.focus();
				return false;
			}else if(form.rcontent.value == ""){
				alert("내용을 입력해주세요");
				form.rcontent.focus();
				return false;
			}else{
				alert("작성이 완료되었습니다.");
				form.submit();
				
			}
			}//checkForm
		</script>
</head>
<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->
<fieldset id = "rent_box">
	<hr id = renthr>
	<h1 class = "renth1">대관문의 작성</h1>
	
	<form action="./rentWriteAction.bo" method="post" name ="rentForm">
	
	
		<br><label> 아이디 </label><br>
		<input type="text" id="wInput" name="ruser_id" placeholder="회원아이디 입력"><br>
		
		<br><label> 이름(기업명) </label><br>
		<input type="text" id="wInput" name="rname" placeholder="이름(기업명)입력"><br>
		
		<br><label > 극장선택 </label><br>
		<select name = "region" id ="region_name">
	    	<option value="start" class ="start">선택하세요</option>
	    	</select>
	    	<select name = "cinema" id ="cinema_name">
	    	<option value="start" class ="start1">선택하세요</option>
	    	</select><br>
	    	
	    <br><label > 휴대폰 번호 </label><br>
	    	<input type="text" id="wInput" name="rphone" placeholder="01000000000"><br>
	    	
	    <br><label > 이메일 </label><br>
	    	<input type="text" id="wInput" name="remail" placeholder="email@email.com"><br>
	    	
	    <br><label > 제 목 </label><br>
	    	<input type="text" id="wInput" name="rsubject"><br>
		
		 <br><label > 내 용 </label><br>
		 	<textarea rows="" cols="" id="wInput" name="rcontent"></textarea><br>
		 	
		 <input type="button" value="글쓰기" class="btn1" onclick = "checkForm();">
		 <input type="button" value="닫기" class="btn1" onclick ="cancel();" >
		
	
</form>
	</fieldset>
<!-- 하단바 고정  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
<!-- 하단바 고정  -->

</body>
</html>