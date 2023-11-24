<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Off The Lamp</title>
<link href="./css/faqMain.css" rel="stylesheet">
</head>
<body>
	<!-- 상단 바 고정 -->
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<div class="container">
		<!-- 		<h1> -->
		<!-- 			여기 faq게시판 꾸미는자리<br> 밑에 footer 알아서 내려감 -->
		<!-- 		</h1> -->
		<script type="text/javascript">
			var popupX=null;
			// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
			var popupY=null;
			// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
			console.log(popupX);
			console.log(popupY);
			function qnaBoard() {
				var user_id = "${user_id}";
				
				if(user_id == 'admin'){
					alert("관리자 계정입니다.");	
				}else if(user_id == null || user_id.search('@') == -1){
// 					console.log(user_id);
// 					console.log(user_id.search('@'));
					alert("로그인 후 이용해주세요");
				}else{
					popupX = (document.body.offsetWidth / 2) - (500 / 2);
					popupY= (window.screen.height / 2) - (600 / 2);
					window.open("./qnaBoard.bo?user_id=${user_id}","_black","width=500, height=600, left="+popupX+", top="+popupY);
				}
			}
			function qnaBoardList() {
// 				console.log(${user_id})
				var user_id = "${user_id}";
				if(user_id == 'admin'){
					window.open("./qnaBoardList.bo?user_id=${user_id}", "_self");
				}else if(user_id == null || user_id.search('@') == -1){
					alert("로그인 후 이용해주세요");
				}else{
					window.open("./qnaBoardList.bo?user_id=${user_id}", "_self");
				}
			}
			function faqBoardAdd() {
				popupX = (document.body.offsetWidth / 2) - (550 / 2)-60;
				popupY= (window.screen.height / 2) - (550 / 2);
				window.open("./faqBoardAdd.bo?user_id=${user_id}","_black","width=500, height=550, left="+popupX+", top="+popupY);
			}
			function faqBoardUpdate(bno) {
				popupX = (document.body.offsetWidth / 2) - (500 / 2)-60;
				popupY= (window.screen.height / 2) - (500 / 2);
				window.open("./faqBoardUpdate.bo?faq_bno="+bno+"&&category=2&&user_id=${user_id}","_black","width=500, height=500, left="+popupX+", top="+popupY);
			}
			function faqBoardDelete(bno) {
				popupX = (document.body.offsetWidth / 2) - (500 / 2)-60;
				popupY= (window.screen.height / 2) - (200 / 2);
				window.open("./faqBoardDelete.bo?faq_bno="+bno+"&&category=2&&user_id=${user_id}","_black","width=500, height=200, left="+popupX+", top="+popupY);
			}
			function boardList() {
				location.href="./faqMain.bo?user_id=${user_id}";
			}
			

		</script>
		<div class="faq-container">
			<div class="qnaButton">
				<c:if test="${user_id eq 'admin'}">
					<div>
						<input type="button" class="btn" value="faq추가"
							onclick="faqBoardAdd();">
					</div>
				</c:if>
				<input type="button" onclick="qnaBoard();" value="1:1 문의"
					class="btn" /> <input type="button" onclick="qnaBoardList();"
					value="1:1 문의내역" class="btn" />
			</div>
			<h1>자주 묻는 질문</h1>
			<c:forEach var="dto" items="${boardList }">
				<div class="faq">
					<c:if test="${user_id eq 'admin'}">
						<input type="button" class="btn" id="updateFaq" value="faq수정"
							onclick="faqBoardUpdate(${dto.faq_bno});">
						<input type="button" class="btn" id="deleteFaq" value="faq삭제"
							onclick="faqBoardDelete(${dto.faq_bno});">
					</c:if>
					<h4 class="faq-title">${dto.subject }</h4>

					<p class="faq-text">${dto.content }</p>

					<button class="faq-toggle">
						<i class="fas fa-chevron-down"></i> <i class="fas fa-times"></i>
					</button>
				</div>
			</c:forEach>

		</div>


	</div>
	<script type="text/javascript">
		const toggles = document.querySelectorAll(".faq-toggle");

		toggles.forEach((toggle) => {
	  	toggle.addEventListener("click", () => {
	   		 toggle.parentNode.classList.toggle("active");
	  		});
		});
	</script>
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