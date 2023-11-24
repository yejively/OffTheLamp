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
	<!-- 여기 문의 꾸며아함. -->
	<div class="container">
		<h1>글 내용</h1>
		<table class="boardContent">
			<tr>
				<th class="ttitle" colspan="4"></th>
			</tr>
			<tr>
				<td class="column">글번호 </td>
				<td class="cntBno">${dto.qna_bno }</td>

				<td class="column">작성일 </td>
				<td class="cntDate"><fmt:formatDate value="${dto.updatedate }"
						pattern="YY-MM-dd" /></td>

			</tr>
			<tr>
				<td class="column">질 문 </td>
				<td class="cntSubject" colspan="3">${dto.subject }</td>
			</tr>
			<tr>
				<td class="column">내 용 </td>
				<td class="cntContent" colspan="3">${dto.content }</td>
			</tr>
			<tr>
				<td class="column">답 변 </td>
				<td class="cntContent" colspan="3">${dto.answer_context }</td>
			</tr>

		</table>
		<c:if test="${dto.user_id eq user_id }">
			<div class="CRUD">
				<c:if test="${dto.answer == 0 }">
					<!-- 답변 못받을 경우만 수정가능하게  -->
					<input type="button" value="수정하기" class="btn"
						onclick="location.href='qnaBoardUpdate.bo?qna_bno=${dto.qna_bno}&&pageNum=${param.pageNum }&&user_id=${user_id}';">
				</c:if>
				<input type="button" value="삭제하기" class="btn" onclick="deleteQna();">
			</div>
		</c:if>
	
		<c:if test="${user_id eq 'admin' }">
	
			<!-- 				관리자만 가능하게   -->
			<div class="CRUD">
				<input type="button" value="답변쓰기" class="btn"
					onclick="location.href='qnaBoardAnswer.bo?qna_bno=${dto.qna_bno}&&pageNum=${param.pageNum }&&user_id=${user_id}';">
			</div>
		</c:if>
		<div class="rightButton">
			<input type="button" value="이전 게시판" class="btn"
				onclick="location.href='./qnaBoardList.bo?user_id=${user_id}&&pageNum=${param.pageNum }';">
		</div>
	</div>
	<script type="text/javascript">
		function deleteQna() {
			var popupX = (document.body.offsetWidth / 2) - (500 / 2);
			// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
			
			var popupY= (window.screen.height / 2) - (250 / 2) - 50;
			// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
			console.log(popupX);
			console.log(popupY);
			window.open("./qnaBoardDelete.bo?qna_bno=${dto.qna_bno }&&pageNum=${param.pageNum }&&user_id=${user_id }",
					"_black","height=250, width=500, left="+popupX+", top="+popupY);
		}
		
		function boardList() {
			location.href="qnaBoardList.bo?pageNum=${param.pageNum}&&user_id=${user_id}";
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