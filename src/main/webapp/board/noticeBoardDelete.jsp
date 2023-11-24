<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>

<script type="text/javascript">
	
</script>
</head>

<body>
	<!-- 상단 바 고정 -->
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<!-- 상단 바 고정 -->
	<h2>공지게시판 글 삭제</h2>

	<h3>삭제하시겠습니까?</h3>

	<form
		action="./enfBoardDelete.bo?pageNum=${param.pageNum }&&category=1&&user_id=${user_id}"
		method="post">
		<input type="hidden" name="notice_bno" value="${param.notice_bno }">

		<input type="submit" value="예" class="btn"> <input
			type="button" value="아니요" class="btn" onclick="window.close();">
	</form>
	
	<!-- 하단바 고정  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
<!-- 하단바 고정  -->

</body>
</html>