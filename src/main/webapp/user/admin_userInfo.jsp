<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Off The Lamp</title>
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<link rel="stylesheet" href="./css/userInfo.css">
<!-- <link rel="stylesheet" href="./css/listPage.css"> -->
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.user_id}">
	<script>
 		alert('로그인 해주세요.'); 
		location.href='./UserLogin.me';
	</script>
</c:if>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!--center -------------------------------------------------------------  -->
<main>
	<h1>${sessionScope.user_id } 님 환영합니다</h1>
	<div id="container">
		<input class="btn" type="button" value="예매관리"	onclick="location.href='./managerList.or'">
		<input class="btn" type="button" style="background: gray;" value="회원정보수정"	onclick="location.href='.AdminUserInfoBoardAction.me'">
		<input class="btn" type="button" value="대관문의"	onclick="location.href='./AdminRentInfoBoardAction.me'">
	</div>
	<div id="userInfo_box">
		<div id="table_search">
			<form action="AdminUserInfoBoardAction.me" method="post">
				<input type="text" name="search" class="input_box" placeholder="회원 아이디를 입력해주세요.">
				<input type="submit" value="검색" class="search" >
			</form>
		</div>
		<c:set var="bno" value="${startRow-1 }"/>
		<c:set var="bno1" value="-1"/>
		<table id="table">
			<tr>
				<td class="column">No.</td>
				<td class="column">성명</td>
				<td class="column">아이디</td>
				<td class="column">휴대폰 번호</td>
				<td class="column">가입날짜</td>
				<td class="column">예매내역</td>
				<td class="column">관리</td>
			</tr>
			<c:choose>
				<c:when test="${!empty list }">
				<c:forEach items="${list }" var="list">
				<tr>
					<td>${bno=bno+1 } </td>
					<td>${list.user_name }</td>
					<td>${list.user_id }</td>
					<td>${list.user_phone}</td>
					<td><fmt:formatDate value="${list.user_regdate }"/> </td>
					<td>${list.user_orderCount }건</td>
					<td id="openModalBtn${bno1=bno1+1}" class="openModalBtn">관리</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise><tr><td>일치하는 정보 없음</td></tr></c:otherwise>
			</c:choose>
		</table>
	
		<div class="clear"></div>
		<div id="page_control">
			<c:if test="${startPage > pageBlock }">
				<div class="pageButtonBox">
					<a href="./AdminUserInfoBoardAction.me?pageNum=${startPage-pageBlock }" class="pageButton">Prev</a>
				</div>
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
				<div class="pageButtonBox">
					<a href="./AdminUserInfoBoardAction.me?pageNum=${i }" class="pageButton">${i}</a>
				</div>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
				<div class="pageButtonBox">
					<a href="./AdminUserInfoBoardAction.me?pageNum=${startPage+pageBlock }" class="pageButton">Next</a>
				</div>
			</c:if>
		</div>
	</div>

</main>
<!--center end-------------------------------------------------------------  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>

	<!-- 모달 출력필드 -->
	<div id="MyModal" class="modal">
	    <div class="modal-contents">
	    	<table id="table">
	    		<tr>
	    			<th class="ttitle" colspan="4" id="p0"></th>
	    		</tr>
	    		<tr>
					<td class="column">성 명</td>
					<td id="p1"></td>

					<td class="column2">예매 내역</td>
					<td id="p7"></td>

				</tr>
				<tr>
					<td class="column2">휴대폰 번호</td>
					<td id="p3"></td>

					<td class="column">차 번호</td>
					<td id="p4"></td>

				</tr>
				<tr>
					<td class="column2">가입 날짜</td>
					<td id="p5" colspan="3"></td>
				</tr>
				<tr>
					<td class="column2">접속 시간</td>
					<td id="p6" colspan="3"></td>
				</tr>
				<tr>
					<td class="column"> 회원등급</td>
					<td id="p2" colspan="2"></td>
					<td><input id="user_typeChange" type="button" value="등급변경"></td>
				</tr>

		      </table>
<!-- 	      <h2 id="p0"></h2> -->
<!-- 	      <p id="p1"></p> -->
<!-- 	      <p id="p2"></p> -->
<!--      	  <p id="p3"></p> -->
<!-- 	      <input id="user_typeChange" type="button" value="등급변경"> -->
<!-- 	      <p id="p4"></p> -->
<!-- 	      <p id="p6"></p> -->
<!-- 	      <p id="p5"></p> -->
<!-- 	      <p id="p7"></p> -->
<!-- 	      <p id="p8"></p> -->
	      <input type="button" value="닫기" class="close">
	    </div>
	 </div>
	 
  <script>
  	var choice =0;
  	var jList = JSON.parse('${requestScope.jList}');
  	
  	$('#openModalBtn0').click(function(){
  		choice=0
  	});
  	$('#openModalBtn1').click(function(){
  		choice=1
  	});
  	$('#openModalBtn2').click(function(){
  		choice=2
  	});
  	$('#openModalBtn3').click(function(){
  		choice=3
  	});
  	$('#openModalBtn4').click(function(){
  		choice=4
  	});
  	
  	$('td[id^="openModalBtn"]').css("cursor","pointer").click(function(){
		$('#p0').text(jList[choice].user_id+'님의 회원정보입니다.');
		$('#p1').text(+jList[choice].user_name);
// 		$('#p2').text(jList[choice].user_id);
		if(jList[choice].user_type==0)
			$('#p2').text('일반회원');
		if(jList[choice].user_type==1)
			$('#p2').text('이트관리자');
		if(jList[choice].user_type==2)
			$('#p2').text('극장관리자');
		$('#p3').text(jList[choice].user_phone);
		$('#p4').text(jList[choice].user_car_num);
		$('#p5').text(jList[choice].user_regdate);
		$('#p6').text(jList[choice].last_access);
		$('#p7').text(jList[choice].user_orderCount+'건');
  	});

  	//유저 등급변경 버튼
  	$("input[value='등급변경']").click(function(){
  		
  		var tNum = prompt('변경할 등급을 선택해주세요."\n 0 - 일반회원\n 1 - 사이트관리자\n 2 - 극장관리자"');
  		if(tNum==0 || tNum==1 || tNum==2){
	  		$.ajax({
				url : './UserTypeChangeAction.me',
				type : 'POST',
				data : {"choice" : choice,
						"startRow" : ${startRow},
						"pageSize" : ${pageSize},
						"user_type" :tNum
						},
				success : function(response) {
					if (response.trim() != "-1") {
						alert('등급변경 성공');
						location.reload();
					} else {
						alert('등급변경 실패');
					}
				}
			});
  		}else{
  			alert('등급을 다시 입력해주세요.');
  		}
  	})
  	
  
    $(document).ready(function(){
      // 모달과 닫기 버튼의 객체를 가져옵니다.
      var modal = $("#MyModal");
      var closeBtn = $(".close");

      // 'Open Modal' 버튼을 클릭하면 모달을 표시합니다.
      $(".openModalBtn").click(function(){
        modal.css("display", "block");
      });
      // 마우스 올릴때 이벤트
      $(".openModalBtn").mouseover(function () {
			$(this).css("color","red");
	  });
      
      $(".pageButton").mouseover(function () {
			$(this).css("color","red");
	  });
      
      $(".search").mouseover(function () {
			$(this).css("color","red");
	  });
      
      $(".btn").mouseover(function () {
			$(this).css("color","red");
	  });
      $(".close").mouseover(function () {
			$(this).css("color","red");
	  });
      $(".pageButton").mouseover(function () {
			$(this).css("color","red");
	  });
      $(".btn").mouseover(function () {
			$(this).css("color","red");
	  });
      
      // 내릴때
      $(".openModalBtn").mouseleave(function () {
			$(this).css("color","white");
	  });
      $(".pageButton").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".search").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".btn").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".close").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".pageButton").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".btn").mouseleave(function () {
			$(this).css("color","black");
	  });
      // 닫기 버튼을 클릭하면 모달을 숨깁니다.
      closeBtn.click(function(){
        modal.css("display", "none");
      });

      // 모달 외부를 클릭하면 모달을 숨깁니다.
      $(window).click(function(event){
        if (event.target === modal[0]) {
          modal.css("display", "none");
        }
      });
    });
  </script>


</body>
</html>