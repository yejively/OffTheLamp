<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<link href="./css/listPage.css" rel="stylesheet">
</head>
<body>
	<!-- 상단 바 고정 -->
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<!-- 상단 바 고정 -->

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
					<c:choose>
						<c:when test="${dto.category == 0 }">
							<c:if test="${user_id eq 'admin' }">
								<tr>
									<td>${dto.qna_bno }</td>
									<td><a
										href="qnaBoardContent.bo?qna_bno=${dto.qna_bno }&&pageNum=${pageNum}&&user_id=${user_id }">${dto.subject }</a>
									</td>
							</c:if>
							<c:if test="${user_id eq dto.user_id}">
								<tr>
									<td>${dto.qna_bno }</td>
									<td><a
										href="qnaBoardContent.bo?qna_bno=${dto.qna_bno }&&pageNum=${pageNum}&&user_id=${user_id }">${dto.subject }</a>
									</td>
							</c:if>
						</c:when>

						<c:otherwise>
							<td>${dto.rent_bno }</td>
							<td><a
								href="rentBoardContent.bo?rent_bno=${dto.rent_bno }&pageNum=${pageNum}&&user_id=${user_id}">${boardList[0].category}</a>
							</td>
						</c:otherwise>
					</c:choose>

					<td><fmt:formatDate value="${dto.updatedate }"
							pattern="YY-MM-dd" /></td>
					<td>${dto.read_count }</td>
				</tr>
			</c:forEach>

		</table>

		<div class="clear"></div>
		<div id="page_control">
			<c:if test="${startPage > pageBlock }">
				<a
					href="./qrBoardSearch.bo?pageNum=${startPage-pageBlock }&&user_id=${user_id}&&searchField=${searchField}&&searchText=${searchText}&&category=${boardList[0].category }">Prev</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
				<a
					href="./qrBoardSearch.bo?pageNum=${i }&&user_id=${user_id}&&searchField=${searchField}&&searchText=${searchText}&&category=${boardList[0].category }">${i }</a>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
				<a
					href="./qrBoardSearch.bo?pageNum=${startPage+pageBlock }&&user_id=${user_id}&&searchField=${searchField}&&searchText=${searchText}&&category=${boardList[0].category }">Next</a>
			</c:if>
		</div>
		<div class="rightButton">
			<input type="button" value="이전 게시판" class="btn"
				onclick="beforeBoardList();">
		</div>
	</div>

	<script type="text/javascript">
	
		function beforeBoardList() {
// 			alert("category : "+category);
				location.href = "qnaBoardList.bo?user_id=${user_id}";
		}
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