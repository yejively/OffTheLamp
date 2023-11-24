<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>
</head>

<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->
<!-- 여기 대관문의 꾸며아함. -->

	<div class="container">
		<form action="./qnaBoardUpdatePro.bo?pageNum=${pageNum }&&user_id=${user_id}"
			method="post">
			<input type="hidden" name="qna_bno" value="${dto.qna_bno }">
			<input type="hidden" name="answer" value="${dto.answer }">
			<table class="boardContent">
				<tr>
					<th class="ttitle" colspan="3">1:1문의 수정하기</th>
				</tr>

				<tr>
					<td class="column">제 목 </td>
					<td colspan="2"><input type="text" class="cntSubject" name="subject"
						value="${dto.subject }"></td>
				</tr>
				<tr>
					<td class="column">내 용 </td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content">${dto.content }</textarea></td>
				</tr>
			</table>

			<div class="CRUD">
				<input type="submit" value="수정하기" class="btn">
				<input type="button" value="취소" class="btn" onclick="history.back();">
			</div>
		</form>
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