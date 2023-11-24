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
		<h1>검색 리스트페이지</h1>
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
						href="enfBoardContent.bo?notice_bno=${dto.notice_bno }&&category=1&&pageNum=${pageNum}&&user_id=${user_id}">${dto.subject }</a>
					</td>
					<td>
						<fmt:formatDate value="${dto.updatedate }" pattern="YY-MM-dd" />
					</td>
					<td>${dto.read_count }</td>
			</c:forEach>

		</table>
		
		<div class="clear"></div>
		<div id="page_control">
			<c:if test="${startPage > pageBlock }">
				<a href="./enfBoardSearch.bo?pageNum=${startPage-pageBlock }&&searchField=${searchField}&&user_id=${user_id}&&searchText=${searchText}&&category=${boardList[0].category }">Prev</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
				<a href="./enfBoardSearch.bo?pageNum=${i }&&searchField=${searchField}&&user_id=${user_id}&&searchText=${searchText}&&category=${boardList[0].category }">${i }</a>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
				<a href="./enfBoardSearch.bo?pageNum=${startPage+pageBlock }&&searchField=${searchField}&&user_id=${user_id}&&searchText=${searchText}&&category=${boardList[0].category }">Next</a>
			</c:if>
		</div>
		<div class="rightButton">
			<input type="button" value="목록이동" onclick="location.href='noticeMain.bo?user_id=${user_id}';">
		</div>
	</div>
	<script type="text/javascript">
	
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