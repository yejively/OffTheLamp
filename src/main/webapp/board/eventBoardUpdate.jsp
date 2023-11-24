<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/event.css" rel="stylesheet">
<link href="./css/listPage.css" rel="stylesheet">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<title>Off The Lamp</title>
<!-- 상단 바 고정 -->

</head>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<body>
	<!-- 여기 대관문의 꾸며아함. -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#fileChk").change(function() {
				if ($("#fileChk").is(":checked")) {// alert("체크박스 체크했음!");
					$('#update').append("<input type='file' name='file' id='file2' required>");
				} else {// alert("체크박스 체크 해제!");
					$('#file2').remove();
				}
			});
		});
	</script>
	<div class="container">
		<form action="./enfBoardUpdatePro.bo?category=0&&user_id=${user_id}"
			method="post" enctype="multipart/form-data" id="updateForm">
			<input type="hidden" name="event_bno" value="${dto.event_bno }">
			<table class="boardContent">
				<tr>
					<th class="ttitle" colspan="3">이벤트 수정</th>
				</tr>
				<tr>
					<td class="column">타입</td>
					<td><select name="event_type">
							<option value=-1>선택</option>
							<option value=0>진행중</option>
							<option value=1>완료</option>
					</select></td>
				</tr>
				<tr>
					<td class="column">제 목</td>
					<td colspan="2"><input type="text" class="cntSubject2" name="subject"
						value="${dto.subject }" required></td>
				</tr>
				<tr>
					<td class="column">내 용</td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent2"
							name="content" required>${dto.content }</textarea></td>
				</tr>
				<tr>
					<td class="column">파 일</td>
					<td>
						<figure class="image_figure">
							<img alt="" src="./img/${dto.img }">
						</figure>
					</td>

				</tr>
				<tr>
					<td class="column" id="update" colspan="3">변경하겠습니까?<input type="checkbox" id="fileChk"></td>
				</tr>
			</table>

			<div class="CRUD">
				<input type="submit" value="수정하기" class="btn"> <input type="button"
					value="목록이동" onclick="history.back();" class="btn">
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