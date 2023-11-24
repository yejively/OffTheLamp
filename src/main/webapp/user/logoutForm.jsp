<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Off The Lamp</title>
</head>

<body>
<!-- ��� �� ���� -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- ��� �� ���� -->
<!--center -------------------------------------------------------------  -->
<section id="center" class="center_o pt-2 pb-2">
		
			<script type="text/javascript">
			alert("로그아웃 성공");
			location.href="./Main.me";
			</script>
	<hr>
 </section>
<!--center end-------------------------------------------------------------  -->
	<!-- footer�Ʒ��δ� �ڵ� ���� -->

<!-- �ϴܹ� ����  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
<!-- �ϴܹ� ����  -->

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