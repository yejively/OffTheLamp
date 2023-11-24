<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/faqMain.css" rel="stylesheet">
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/global.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap"	rel="stylesheet">
<script src="./js/bootstrap.bundle.min.js"></script>
<style type="text/css">

.hrhr{
background-color: black;
}
</style>
</head>
<body>
	<fieldset>
		<form action="enfBoardAdd.bo?category=2&&user_id=${user_id}"
			method="post">
			<table class="boardContent">
				<hr class = "hrhr">
				<tr>
					<th class="ttitle" colspan="3">FAQ 추가</th>
				</tr>
				<tr>
					<td class="column">질문</td>
					<td colspan="2"><input type="text" name="subject" class="cntSubject" required></td>
				</tr>
				<tr>
					<td class="column">답변</td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content" required="required"></textarea></td>
				</tr>

			</table>

			<div class="CRUD">
				<input type="submit" value="완료" class="btn">
				<input type="button" value="취소" class="btn"
					onclick="window.close();">
			</div>
		</form>
	</fieldset>
</body>
</html>