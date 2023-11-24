<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Off The Lamp</title>
<link rel="stylesheet" href="./css/userInfo.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
</head>
<body>
<%-- <%session.setAttribute("user_id", "1"); %> --%>
<c:if test="${empty sessionScope.user_id}">
	<script>
		alert('로그인 해주세요.');
		location.href='./UserLogin.me';
	</script>
</c:if>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->
<!--center -------------------------------------------------------------  -->
<main>
	<h1>${sessionScope.user_id } 님 환영합니다</h1>
	<div id="container">
		<input id="btn1" type="button" value="예매/취소내역"	onclick="location.href='./MyPageMain.or'">
		<input id="btn2" type="button" value="회원정보수정"	style="background: gray;"onclick="location.href='./UserInfoCheck.me'">
		
	</div>
	<div id="userInfo_box">
		<form action="./UserInfoCheckAction.me" method="post">
			<fieldset id="userInfo_wrap">
				<input type="hidden" name="user_id" value="${sessionScope.user_id }" readonly> 
				<p id='chId'></p>
				<input type="hidden" id="isCheckId" value="false">
				<label>비밀번호 </label><br> <input type="password" name="user_pass" placeholder="비밀번호 입력"> <br>
				<p id='hiddenMsgPw'></p>
				 <label>비밀번호 확인 </label><br> <input type="password" name="user_chpw" placeholder="비밀번호 입력 확인"> <br> 
				<p id='hiddenMsgPwCheck'></p>
				
				 <input type="hidden" id="isDelete"> 
				 <input type="submit" value="확인"	onclick="return check()">
			</fieldset>
		</form>
	</div>

</main>
<!--center end-------------------------------------------------------------  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>

<script type="text/javascript">
//입력값 공백 및 비밀번호 일치확인
	function check() {
		
		/* 비밀번호 및 비밀번호 확인 유효성 검사 */
		if ($('input[name="user_pass"]').val().length == 0) {
			$("#hiddenMsgPw").text("비밀번호를 입력해 주세요.");
			$("#hiddenMsgPw").css('color', 'red');
			$('input[name="user_pass"]').focus();
			return false;
		}else{
			$("#hiddenMsgPw").text("");
		}
	
		if ($('input[name="user_chpw"]').val().length == 0) {
			$("#hiddenMsgPwCheck").text("비밀번호 확인을 입력해주세요.");
			$("#hiddenMsgPwCheck").css('color', 'red');
			$('input[name="user_chpw"]').focus();
			return false;
		}else{
			$("#hiddenMsgPwCheck").text("");
		}
	
		if ($('input[name="user_pass"]').val() != $('input[name="user_chpw"]').val()) {
			$("#hiddenMsgPw").text("비밀번호가 일치하지 않습니다.");
			$("#hiddenMsgPw").css('color', 'red');
			$('input[name="user_pass"]').select(); 
			return false;
		}
	
	}
</script>


</body>
</html>