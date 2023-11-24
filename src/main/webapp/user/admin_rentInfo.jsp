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
		<input class="btn" type="button" value="예매관리" onclick="location.href='./managerList.or'">
		<input class="btn" type="button" value="회원정보" onclick="location.href='./AdminUserInfoBoardAction.me'">
		<input class="btn" type="button" style="background: gray;" value="대관문의" onclick="location.href='./AdminRentInfoBoardAction.me'">
	</div>
	<div id="userInfo_box">
		<div id="table_search">
			<form action="AdminRentInfoBoardAction.me" method="post">
				<input type="text" name="search" class="input_box" placeholder="회원 아이디를 입력해주세요.">
				<input type="submit" value="검색" class="search">
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
				<td class="column">문의일자</td>
				<td class="column">답변</td>
				<td class="column">관리</td>
			</tr>
			<c:choose>
				<c:when test="${!empty boardList }">
				<c:forEach items="${boardList }" var="list">
				<tr>
					<td>${list.rent_bno }</td>
					<td>${list.rent_name }</td>
					<td>${list.user_id }</td>
					<td>${list.rent_phone} </td>
					<td><fmt:formatDate value="${list.regdate }"/> </td>
					<td>
						<c:choose>
							<c:when test="${list.answer == 0}">
								N							
							</c:when>
							<c:otherwise>
								Y
							</c:otherwise>
						</c:choose>
					</td>
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
					<a href="./AdminRentInfoBoardAction.me?pageNum=${startPage-pageBlock }" class="pageButton">Prev</a>
				</div>
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
				<div class="pageButtonBox">
					<a href="./AdminRentInfoBoardAction.me?pageNum=${i }" class="pageButton">${i}</a>
				</div>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
				<div class="pageButtonBox">
					<a href="./AdminRentInfoBoardAction.me?pageNum=${startPage+pageBlock }" class="pageButton">Next</a>
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
	    <div class="modal-contents-rent">
	      <table id="table">
	      <tr>
				<th class="ttitle" colspan="4" id="p0"></th>
			</tr>
			<tr>
				<td class="column">성 명</td>
				<td class="cntBno" id="p1"></td>

				<td class="column">작성일</td>
				<td class="cntDate" id="p2"></td>

			</tr>
			<tr>
				<td class="column2">이메일</td>
				<td class="cntDate" id="p4"></td>

				<td class="column2">휴대폰 번호</td>
				<td class="cntBno" id="p3"></td>

			</tr>
			<tr>
				<td class="column">제 목</td>
				<td class="cntSubject" colspan="3" id="p5"></td>
			</tr>
			<tr>
				<td class="column">내 용</td>
				<td class="cntContent" colspan="3" id="p6"></td>
			</tr>
			<tr>
				<td class="column">답 변</td>
				<td class="cntContent" colspan="3" id="p7"></td>
			</tr>
			<tr>
				<td class="column2">시네마 이름</td>
				<td class="cntContent" colspan="3" id="p8"></td>
			</tr>

	      </table>
	      
	      <input type="button" value="닫기" class="close">
	   	 </div>
	 </div>
	 
  <script>
  	var choice =0;
  	var jListStr = '${jList}';
//   	console.log(jListTest);

	// JSON.parse 에러 일으키는 단어들 치환
	var jListRep = jListStr.replace(/\r/gi, '\\r').replace(/\n/gi, '\\n').replace(/\t/gi, '\\t').replace(/\f/gi, '\\f');
	var jList = JSON.parse(jListRep);
  	console.log(jList);
  	
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
		$('#p0').text(jList[choice].user_id+'님의 대관문의 정보입니다.');
		$('#p1').text(jList[choice].rent_name);
		$('#p2').text(jList[choice].regdate);
		$('#p3').text(jList[choice].rent_phone);
		$('#p4').text(jList[choice].rent_email);
		$('#p5').text(jList[choice].subject);
		$('#p6').text(jList[choice].content);
		if(jList[choice].answer == 0){
			$('#p7').text('N');
		}else{
			$('#p7').text('Y');
			
		}
		 $('#p7').css("cursor","pointer").click(function () {
			  	console.log(jList[choice].answer);
			  	console.log(jList[choice].rent_bno);
			  	var rent_bno = jList[choice].rent_bno;
			  	var answer = jList[choice].answer;
				location.href="rentAnswer.bo?rent_bno="+rent_bno+"&&answer="+answer;
				
		  });
		$('#p8').text(jList[choice].cinema_name);
  	});
  	
  
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
      $("#p7").mouseover(function () {
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
      $("#p7").mouseleave(function () {
			$(this).css("color","white");
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