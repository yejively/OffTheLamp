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
	<!-- 여기 공지사항 꾸며아함. -->
	<div class="container">
		<h1>공지사항</h1>
		<c:if test="${user_id eq 'admin' }">
			<div class="rightButton">
				<input type="button" class="btn" value="공지추가"
					onclick="noticeBoardAdd();">
			</div>
		</c:if>
		<table class="BoardList">
			<tr class="thList">
				<th class="bno">No.</th>
				<th class="subject">Title</th>
				<th class="date">Date</th>
				<th class="readCount">ReadCount</th>
			</tr>
			<c:forEach var="dto" items="${boardList }">
				<tr>
					<td>${dto.notice_bno }</td>
					<td><a
						href="enfBoardContent.bo?notice_bno=${dto.notice_bno }&&pageNum=${pageNum}&&category=${dto.category}&&user_id=${user_id}">${dto.subject }</a>
					</td>
					<td><fmt:formatDate value="${dto.updatedate }"
							pattern="YY-MM-dd" /></td>
					<td>${dto.read_count }</td>
				</tr>
			</c:forEach>

		</table>

		<script type="text/javascript">
				var popupX = (document.body.offsetWidth / 2) - (500 / 2);
				// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
		
				var popupY= (window.screen.height / 2) - (600 / 2) - 20;
				// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
				function noticeBoardAdd() {
					window.open("./noticeBoardAdd.bo?user_id=${user_id}","_black",
							"width=500, height=600, left="+popupX+", top="+popupY);
				}
				function boardList() {
					location.href="./noticeMain.bo?user_id=${user_id}";
				}
			
		</script>
		<div id="page_control">
			<c:if test="${startPage > pageBlock }">
				<a
					href="./noticeMain.bo?pageNum=${startPage-pageBlock }&&user_id=${user_id}">Prev</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
				<a href="./noticeMain.bo?pageNum=${i }&&user_id=${user_id}">${i }</a>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
				<a
					href="./noticeMain.bo?pageNum=${startPage+pageBlock }&&user_id=${user_id}">Next</a>
			</c:if>
		</div>
	</div>
	<div id="table_search">
		<form action="./enfBoardSearch.bo?user_id=${user_id }"
			name="boardSearch" method="post">
			<input type="hidden" name="category"
				value="${boardList[0].category }">

			<table>
				<tr>
					<td><select name="searchField">
							<option value="0">선택</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
					</select></td>

					<td><input type="text" name="searchText" class="input_box">
					</td>
					<td><input type="submit" value="search" class="btn"></td>
				</tr>
			</table>


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