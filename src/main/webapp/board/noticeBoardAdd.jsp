<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<fieldset>
		<form action="enfBoardAdd.bo?category=1&&user_id=${user_id}"
			method="post">
			<table class="boardContent">
				<tr>
					<th class="ttitle" colspan="3">공지 추가</th>
				</tr>
				<tr>
					<td class="column">제목</td>
					<td colspan="2"><input type="text" class="cntSubject" name="subject" required="required"></td>
				</tr>
				<tr>
					<td class="column">내용</td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content" required="required"></textarea></td>
				</tr>

			</table>

			<div class="CRUD">
				<input type="submit" value="완료" class="btn"> <input type="button"
					value="취소" class="btn" onclick="window.close();">
			</div>
		</form>
	</fieldset>
</body>
</html>