<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Jquery 라이브러리 추가 -->
<script src="../js/code.jquery.com_jquery-3.7.1.js"></script>
<link rel="stylesheet" href="./css/findId.css">
<title>Off The Lamp</title>
</head>
<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->	
<!-- 여기 회원가입 페이지 꾸며아함. -->
	<section id="center" class="center_o pt-2 pb-2">
	<form action="./findIdForm.jsp" method="post">
	
			
			<script type="text/javascript">
			alert("고객님의 아이디는 "+id+" 입니다");
			</script>
		
	</form>

	<hr>
 </section>
<!--center end-------------------------------------------------------------  -->
	<!-- footer아래로는 코드 금지 -->

<!-- 하단바 고정  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
<!-- 하단바 고정  -->


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
	</script>

</body>
</html>