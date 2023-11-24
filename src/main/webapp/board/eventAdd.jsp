<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/event.css" rel="stylesheet">
<link href="./css/listPage.css" rel="stylesheet">
<title>Off The Lamp</title>
<!-- 상단 바 고정 -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/global.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap"
	rel="stylesheet">
<script src="./js/bootstrap.bundle.min.js"></script>
<!-- 상단 바 고정 -->
<style type="text/css">
.btn{
  padding: 0.5em;
  margin: 0.5em 1em;
  width: 10em;
  border-radius: 0.5em;
}
input{
	padding: 0.5em;
  	margin: 0.5em 0;
	width: 23em;
	border-radius: 0.5em;
}
textarea{
	padding: 0.5em;
  	margin: 0.5em 0;
	width: 23em;
	height: 8em;
	border-radius: 0.5em;
}
fieldset{
padding: 15px ;
margin: 15px auto;
}


</style>
</head>
<body>
	<!-- 상단 바 고정 -->
<!-- 상단 바 고정 -->
	 <form action="enfBoardAdd.bo?category=0&&user_id=${user_id}" method="post" enctype="multipart/form-data">
	 	<fieldset>
	 		<table class="boardContent">
				<tr>
					<th class="ttitle" colspan="3">이벤트 추가</th>
				</tr>
				<tr>
					<td class="column">제목</td>
					<td colspan="2"><input type="text" name="subject" class="cntSubject" required></td>
				</tr>
				<tr>
					<td class="column">내용</td>
					<td colspan="2"><textarea rows="" cols="" class="cntContent"
							name="content" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="column">파일</td>
					<td colspan="2"><input type="file" name="file" required></td>
				</tr>
				
			</table>
	 		
			<div class="CRUD">
				<input type="submit" class="btn" value="추가">
				<input type="button" class="btn" value="취소" onclick="window.close();">
			</div>
	 	</fieldset>
	 </form>
</body>
</html>