<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/faqMain.css" rel="stylesheet">
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>
</head>

<body>
	<!-- 상단 바 고정 -->
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<!-- 상단 바 고정 -->
	<div class="container">
		<form action="./enfBoardUpdatePro.bo?&&category=2&&user_id=${user_id}"
			method="post">
			<input type="hidden" name="faq_bno" value="${dto.faq_bno }">
			<table class="boardContent">
				<tr>
					<th class="ttitle" colspan="3">FAQ 수정</th>
				</tr>
				<tr>
					<td class="column">제 목 </td>
					<td colspan="2"><input type="text" class="cntSubject" name="subject" required="required"
						value="${dto.subject }"></td>
				</tr>
				<tr>
					<td class="column">내 용 </td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content" required="required">${dto.content }</textarea></td>
				</tr>
			</table>

			<div class="CRUD">
				<input type="submit" class="btn" value="수정하기"> <input type="button"
					value="취소" class="btn" onclick="window.close();">
			</div>
		</form>
	</div>



</body>
</html>