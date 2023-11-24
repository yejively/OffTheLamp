<%@page
	import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@page import="com.team2.payment.db.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Off The Lamp</title>
<link href="./css/userInfo.css" rel="stylesheet">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var id = "${sessionScope.user_id}";
		
		if(id != "admin"){
			alert("관리자 접근페이지입니다")
			location.href='./Main.or';
		};
		
		$('.openModalBtn').click(function(){
			var state = $('#idNum9').text();
			console.log(state);
			if(state == "결제상태 : 취소완료"){
				$('#cancelbtn').hide();
			}else{
				$('#cancelbtn').show();
			}
		});
						
		
	});
</script>

</head>
<body>
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<main>
		<h1>${sessionScope.user_id } 님 환영합니다</h1>
	<div id="container">
		<input class="btn" type="button" style="background: gray;" value="예매관리"	onclick="location.href='./managerList.or'">
		<input class="btn" type="button" value="회원정보수정"	onclick="location.href='./AdminUserInfoBoardAction.me'">
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
						<td class="column">NO.</td>
						<td class="column">예매번호</td>	
						<td class="column">아이디</td>	
						<td class="column">예매날짜</td>	
						<td class="column">결제상태</td>	
						<td class="column">상세내역</td>				
					</tr>
			
			<c:set var ="listNum" value="-1" />
			<c:set var ="bno" value="${startRow -1 }" />
			<c:forEach var="i" begin="1" end="${olist.size() }" step="1">
				<tr>
					<td>${bno=bno+1 }</td>
					<td>${olist.get(i-1).order_id }</td>
					<td>${ulist.get(i-1).user_id }</td>
					<td>${olist.get(i-1).order_date }</td>
					<c:choose>
						<c:when test="${olist.get(i-1).order_state eq 0 }">
							<td class="state">결제완료</td>
						</c:when>
						<c:otherwise>
							<td class="state">취소완료</td>
						</c:otherwise>
					</c:choose>
					<td id="openModalBtn${listNum=listNum+1 }" class="openModalBtn">상세내역</td>
				</tr>
			</c:forEach>
		</table>
		
					<div id="page_control">
							<c:if test="${startPage > pageBlock }">
								<div class="pageButtonBox">
									<a href="./managerList.or?pageNum=${startPage-pageBlock }&idcheck=${idcheck}" class="pageButton">Prev</a>
								</div>
							</c:if>
							
							<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
								<div class="pageButtonBox">
									<a href="./managerList.or?pageNum=${i}&idcheck=${idcheck}" class="pageButton">${i }</a>
								</div>
							</c:forEach>
							
							<c:if test="${endPage < pageCount }">
								<div class="pageButtonBox">
									<a href="./managerList.or?pageNum=${startPage+pageBlock }&idcheck=${idcheck}" class="pageButton">Next</a>
								</div>
							</c:if>
						</div>
					</div>
				
			</main>

</head>
<body>
  <div id="myModal" class="modal-order">
    <div class="modal-content">
       <table id="table">
	        <tr>
	        	<td class="column" colspan="2">예매번호</td>
				<td id="p0" colspan="2"></td>
			</tr>
			<tr>
				<td class="column" colspan="2">예매날짜</td>
				<td id="p1" colspan="2"></td>
			</tr>
			<tr>
				<td class="column" colspan="2">성명</td>
				<td id="p2" colspan="2"></td>

			</tr>
			<tr>
				<td class="column" colspan="2">영화이름</td>
				<td id="p3" colspan="2"></td>
			</tr>
			<tr>
				<td class="column" colspan="2">휴대폰 번호</td>
				<td id="p4" colspan="2"></td>

			</tr>
			<tr>
				<td class="column" colspan="2">차번호</td>
				<td id="p5" colspan="2"></td>
			</tr>
			<tr>
				<td class="column" colspan="2">상영일</td>
				<td id="p6" colspan="2"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td class="column" colspan="2">결제금액</td> -->
<!-- 				<td id="p7" colspan="2"></td> -->
<!-- 			</tr> -->
			<tr>
				<td class="column2">결제상태</td>
				<td id="p8" colspan="2"></td>
				<td> <input type="button" id="cancelbtn"  value="결제취소" ></td>
			</tr>
			<tr>
				<td colspan="3">닫기</td>
				<td><input type="button" value="X" class="close"></td>
			</tr>

	      </table>
	     
	      
    </div>
  </div>
  <script>
  var choice =0;
  var ojlist = JSON.parse('${requestScope.ojlist}');
  var ujlist = JSON.parse('${requestScope.ujlist}');
  var pjlist = JSON.parse('${requestScope.pjlist}');
  
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
		var order_state;
		if(ojlist[choice].order_state == 0){
			order_state = "결제완료";
		}
		if(ojlist[choice].order_state == 1){
			order_state = "취소완료";
		}
	  
   $('#p0').text(ojlist[choice].order_id);
   $('#p1').text(ojlist[choice].order_date);
   $('#p2').text(ujlist[choice].user_name);
   $('#p3').text(ojlist[choice].movie_name);
   $('#p4').text(ujlist[choice].user_phone);
   $('#p5').text(ojlist[choice].car_num);
   $('#p6').text(ojlist[choice].screening_time);
//    $('#p7').text(pjlist[choice].price);
   $('#p8').text(order_state);
  });
  

    $(document).ready(function(){
      // 모달과 닫기 버튼의 객체를 가져옵니다.
      var modal = $("#myModal");
      var closeBtn = $(".close");

      // 'Open Modal' 버튼을 클릭하면 모달을 표시합니다.
      $(".openModalBtn").click(function(){
        modal.css("display", "block");
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
		$('#cancelbtn').click(function(){
			if(confirm("정말 취소하시겠습니까??") == true){
//					document.removefrm.submit();
				modal.css("display","none");
				alert("취소완료");
				
				$.ajax({
					url:"./cancelOrder.or",
					data:{
						"order_id":ojlist[choice].order_id
						},
					success:function(){
						alert("갓다옴");
					},error:function(){
						alert("에러");
					}
				});
				
			}else{
				return false;
			}
		});
      // 마우스 올릴때
      $(".pageButton").mouseover(function () {
			$(this).css("color","red");
	  });
      $(".search").mouseover(function () {
			$(this).css("color","red");
	  });
    
   	  $(".btn").mouseover(function () {
			$(this).css("color","red");
	  });
      
      
      // 내릴때
      $(".pageButton").mouseleave(function () {
			$(this).css("color","black");
	  });
      $(".search").mouseleave(function () {
			$(this).css("color","black");
	  });
  	  $(".btn").mouseleave(function () {
			$(this).css("color","black");
	  });
    });

    
  </script>
  	<footer>
  		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
  	</footer>

</body>
</html>