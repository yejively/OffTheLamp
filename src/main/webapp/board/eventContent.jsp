<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/event.css" rel="stylesheet">
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

	<div class="eventContainer">
		<div class="container" id="contentImg">
			<div id="left">
				<!-- 			<figure id="leftImage" class=image_figure> -->
				<img src="./img/${dto.img }">
				<!-- 			</figure> -->
			</div>
		</div>
		<div id="right">
			<div class="container">
				<h1>이벤트</h1>
				<table class="boardContent" id="imgContent">
					<tr>
						<th class="ttitle" colspan="4"></th>
					</tr>
					<tr>
						<td class="column">글번호</td>
						<td class="cntBno">${dto.event_bno }</td>

						<td class="column">작성일</td>
						<td class="cntDate"><fmt:formatDate value="${dto.updatedate }"
								pattern="YY-MM-dd" /></td>

					</tr>
					<tr>
						<td class="column">제 목</td>
						<td colspan="3" class="cntSubject">${dto.subject }</td>
					</tr>
					<tr>
						<td class="column">내 용</td>
						<td colspan="3" class="cntContent">${dto.content }</td>
					</tr>
				</table>
				<c:if test="${user_id eq 'admin'}">
					<div class="CRUD">
						<input type="button" value="수정하기" class="btn"
							onclick="location.href='eventBoardUpdate.bo?event_bno=${dto.event_bno}&&event_type=${dto.event_type }&&category=0&&user_id=${user_id}';">
						<input type="button" value="삭제하기" class="btn" onclick="eventDelete();">
					</div>
				</c:if>
					<div class="rightButton2">					
						<input type="button" value="목록이동" class="btn" onclick="boardList();">
					</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		if(${dto.event_type==1}){ alert("종료된 이벤트입니다.")}
		
		function eventDelete() {
			var popupX = (document.body.offsetWidth / 2) - (400 / 2);
			// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
			
			var popupY= (window.screen.height / 2) - (200 / 2) - 50;
			// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
			window.open("./eventBoardDelete.bo?event_bno=${dto.event_bno }&&category=0&&user_id=${user_id}",
					"_black","width=400, height=200, left="+popupX+", top="+popupY);
		}
		function boardList() {
			location.href="./eventMain.bo?user_id=${user_id}";
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