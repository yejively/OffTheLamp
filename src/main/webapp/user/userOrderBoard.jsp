<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Off The Lamp</title>
</head>
<body>
<c:if test="${empty sessionScope.user_id}">
	<script>
		alert('로그인 해주세요.');
		location.href='./UserLogin.me';
	</script>
</c:if>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!--center -------------------------------------------------------------  -->
<main>
	<h1>${sessionScope.user_id } 님 환영합니다</h1>
	<div id="container">
		<input id="btn1" type="button" value="예매/취소내역"	onclick="location.href='./UserOrderBoardAction.me'">
		<input id="btn2" type="button" value="회원정보수정"	onclick="location.href='./UserInfoCheck.me'">
		
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
				 <input type="submit" value="확인"	onclick="">
			</fieldset>
		</form>
	</div>

</main>
<!--center end-------------------------------------------------------------  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
</body>
</html>