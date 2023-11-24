<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
	function boardList() {
		location.href = "qnaBoardList.bo?user_id=${user_id}";
	}
</script>
</head>
<body>
	<!-- 상단 바 고정 -->
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>

	<h1>1:1문의</h1>

	<fieldset>
		<form action="qnaBoardAction.bo" method="post">
			<input type="hidden" name="user_id" value="${param.user_id }">
			<table class="boardContent">
				<tr>
					<td>제목 :</td>
					<td colspan="2"><input type="text" class="cntSubject" name="subject">
					</td>
				</tr>
				<tr>
					<td>내용 :</td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content"></textarea></td>
				</tr>

			</table>

			<div class="CRUD">
				<input type="submit" value="완료" class="btn"> <input type="button"
					value="취소" class="btn" onclick="window.close();">
			</div>
		</form>
	</fieldset>

	<!-- 하단바 고정  -->
	<footer>
		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
	</footer>
	<!-- 하단바 고정  -->
</body>
</html>