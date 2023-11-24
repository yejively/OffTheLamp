<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<style type="text/css">
.rentimg{
	width: 100%;
	height: 30%;
}
.rent_btn{
background-color: #a3a1a1;
    color: #e3dede;
    margin-right: -2px;
    padding : 5px;
    border-radius: 5px;
    width: 185px;
    height: 50px;
    cursor : pointer;
    display: inline-block;
}
.rent_btn:hover {
	background-color: white;
	color : black;
	}
.hr1{
background-color : black;
}
.title{
text-align: center;
}
</style>
</head>
<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->
<!-- 여기 대관문의 꾸며아함. -->

	<div class="container">
		<hr class = "hr1">
		<h1 class = "title">
			대관 문의 게시판 
		</h1>
		<hr>
		<h2> 대관신청 절차 안내 </h2>
		<ul>
			<li>
				하단의 '대관문의작성' 버튼을 클릭하여 양식에 맞게 폼을 작성하시면 7일이내 결과를 통보 해 드립니다. 이후 계약을 진행하시면 됩니다. 			
			</li>
			<li>
				<img alt="대관 절차" src="./img/rentMain.jpg" class = "rentimg"> 			
			</li>
		</ul>
		<h2> 승인 부결 통보 </h2>
		<ul>
			<li>
				심의에서 승인 또는 부결된 결과는 개별적으로 통보해 드립니다. 			
			</li>
			<li>
				심의에서 승인된 신청 건에 대해서는 기본대관료 내역 및 계약금과 승인일이 명시된 대관승인서를 메일로 보내드립니다. 			
			</li>
		</ul>
		<h2> 대관 계약</h2>
		<ul>
			<li>
				계약금을 납부함으로써 대관계약이 성립됩니다.	
			</li>
			<li>
				계약금은 기본대관료의 10%이며, 납부기한은 청구일로부터 10일 이내입니다.	
			</li>
			<li>
				계약금을 납부 기한 내 납부하지 않을 시 자동으로 대관승인이 취소됩니다.
			</li>
		</ul>
		
		<div>
			<input type="button" value="대관문의작성" onclick="location.href = './rentWrite.bo'" class = "rent_btn">		
		</div>

	</div>
	
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