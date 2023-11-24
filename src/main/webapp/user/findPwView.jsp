<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<link rel="stylesheet" href="./css/findPw.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
</head>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
	<!--center -------------------------------------------------------------  -->
<section id="center" class="center_o pt-2 pb-2">
	   <form action="./UserInfoChangeAction.me" method="post">
		   <section id="join_box">
			<h1>비밀번호 변경</h1>
			<input type="hidden" name="user_id" value="${user_id }">
			<input type="hidden" name="user_name" value="null">

			<fieldset id="join_wrap">
				<label>비밀번호 </label><br> <input type="password" name="user_pass" placeholder="비밀번호 입력"> <br>
				<p id='hiddenMsgPw'></p>
				 <label>비밀번호 확인 </label><br> <input type="password" name="user_chpw" placeholder="비밀번호 입력 확인"> <br> 
				<p id='hiddenMsgPwCheck'></p>
			 	<input type="submit" value="비밀번호 변경"	onclick="return check()">
			</fieldset>
		</section>
	</form>
</section>

	<hr>
<!--center end-------------------------------------------------------------  -->
	<!-- footer아래로는 코드 금지 -->

<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>



	<script>
		window.onscroll = function() {
			myFunction()
		};

		var navbar_sticky = document.getElementById("navbar_sticky");
		var sticky = navbar_sticky.offsetTop;
		var navbar_height = document.querySelector('.navbar').offsetHeight;

		function myFunction() {
			if (window.pageYOffset >= sticky + navbar_height) {
				navbar_sticky.classList.add("sticky")
				document.body.style.paddingTop = navbar_height + 'px';
			} else {
				navbar_sticky.classList.remove("sticky");
				document.body.style.paddingTop = '0'
			}
		}
		
		
		//입력값 공백 및 비밀번호 일치확인 및 인증체크
		function check() {
//	 		var str = "";
			let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
			let phoneRule = /^(01[016789]{1})[0-9]{4}[0-9]{4}$/;

			/* 비밀번호 및 비밀번호 확인 유효성 검사 */
			if ($('input[name="user_pass"]').val().length == 0) {
				$("#hiddenMsgPw").text("비밀번호를 입력해 주세요.");
				$("#hiddenMsgPw").css('color', 'red');
				$('input[name="user_pass"]').focus();
				return false;
			}else if(reg.test($('input[name="user_pass"]').val()) === false) {
				$("#hiddenMsgPw").text("비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.");
				$("#hiddenMsgPw").css('color', 'red');
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