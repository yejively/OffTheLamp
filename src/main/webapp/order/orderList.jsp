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
<link href="./css/orderList.css" rel="stylesheet">
<!-- <link href="./css/footer.css" rel=stylesheet> -->
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		var id = "${sessionScope.id}";
		console.log(id);
		
		
		$('.openModalBtn').click(function(){
			var state = $('#idNum7').text();
			console.log(state);
			if(state == "결제상태 : 취소완료"){
				$('#cancelbtn').hide();
			}else{
				$('#cancelbtn').show();
			}
		});
	});
	
	<%
		String id = (String)session.getAttribute("id");
		session.setAttribute("id", id);
	%>
</script>
</head>

<body>
	<header>
		<jsp:include page="/inc/topBar.jsp"></jsp:include>
	</header>
	<main>
		<div id="body-content">
		<section id="section">
				<h1>${sessionScope.user_id } 님 환영합니다</h1>
	<div id="container">
		<input id="btn1" type="button" value="예매/취소내역" style="background: gray;"	onclick="location.href='./MyPageMain.or'">
		<input id="btn2" type="button" value="회원정보수정"	onclick="location.href='./UserInfoCheck.me'">
		
	</div>
			<div id="orderBoard">
				<table class="table" id="table">
					<tr>
						<td class="td1">NO.</td>
						<td>예매번호</td>
						<td>예매날짜</td>
						<td>영화제목</td>
						<td>차 번호</td>
						<td>예매상태</td>
						<td>상세내역</td>
					</tr>

					<c:set var="bno" value="${startRow -1 }" />
					<c:set var="listNum" value="-1" />
					<c:forEach var="i" items="${requestScope.list}">
						<tr>
							<td class="td1">${bno=bno+1 }</td>
							<td>${i.order_id }</td>
							<td>${i.order_date }</td>
							<td>${i.movie_name }</td>
							<td>${i.car_num }</td>
							<c:choose>
								<c:when test="${i.order_state eq 0 }">
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
						<div>
							<a href="./MyPageMain.or?pageNum=${startPage-pageBlock }">Prev</a>
						 </div>
					</c:if>

					<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
						<div>
							<a href="./MyPageMain.or?pageNum=${i}">${i }</a>
						</div>
					</c:forEach>

					<c:if test="${endPage < pageCount }">
						<div>
							<a href="./MyPageMain.or?pageNum=${startPage+pageBlock }">Next</a>
						</div>
					</c:if>

				</div>
			</div>
		</section>
		</div>
	</main>
	<style>
		
.modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.7);
}

.modal-content {
	position: relative;
	margin: 10% auto;
	padding: 40px;
	background-color: gray;
	width: 400px;
	text-align: center;
	font-weight: bolder;
}

.close {
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
}

#cancelbtn{
	background-color: #202020;
    border: none;
    font-weight: bolder;
    font-size: x-large;
}

#cancelbtn:hover{
	color: red;
}

.openModalBtn:hover{
	text-shadow: 3px 3px 3px grey;
}
		
	</style>
</head>
<body>
	<div id="myModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span> 
			<p id="idNum1"></p>
			<p id="idNum2"></p>
			<p id="idNum3"></p>
			<p id="idNum4"></p>
			<p id="idNum5"></p>
			<p id="idNum6"></p>				
			<p id="idNum7"></p>				
			<p id="idNum8"></p>				
			<input type="button" class="close" value="X">			
			<input type="button" id="cancelbtn" value="예매취소">
		</div>
	</div>
	<script>
		var choice = 0;
		var jlist = JSON.parse('${requestScope.jlist}');
		var jplist = JSON.parse('${requestScope.jplist}');

		$('#openModalBtn0').click(function() {
			choice = 0
		});
		$('#openModalBtn1').click(function() {
			choice = 1
		});
		$('#openModalBtn2').click(function() {
			choice = 2
		});
		$('#openModalBtn3').click(function() {
			choice = 3
		});
		$('#openModalBtn4').click(function() {
			choice = 4
		});
		
		
		$('td[id^="openModalBtn"]').click(function() {
			var order_state;
			if(jlist[choice].order_state == 0){
				order_state = "결제완료";
			}
			if(jlist[choice].order_state == 1){
				order_state = "취소완료";
			}

			$('#idNum1').text("예매번호 : "+jlist[choice].order_id);
			$('#idNum2').text("예매날짜 : "+jlist[choice].order_date);
			$('#idNum3').text("성명 : "+jplist[choice].name);
			$('#idNum4').text("영화이름 : "+jlist[choice].movie_name);
			$('#idNum5').text("차량번호 : "+jlist[choice].car_num);
			$('#idNum6').text("결제금액 : "+jplist[choice].price);
			$('#idNum7').text("결제상태 : "+order_state);
			$('#idNum8').text("좌석 : "+jlist[choice].seat);
		});

		$(document).ready(function() {
			// 모달과 닫기 버튼의 객체를 가져옵니다.
			var modal = $("#myModal");
			var closeBtn = $(".close");

			// 'Open Modal' 버튼을 클릭하면 모달을 표시합니다.
			$(".openModalBtn").click(function() {
				modal.css("display", "block");
			});

			// 닫기 버튼을 클릭하면 모달을 숨깁니다.
			closeBtn.click(function() {
				modal.css("display", "none");
			});

			// 모달 외부를 클릭하면 모달을 숨깁니다.
			$(window).click(function(event) {
				if (event.target === modal[0]) {
					modal.css("display", "none");
				}
			});
			
			$('#cancelbtn').click(function(){
				if(confirm("정말 취소하시겠습니까??") == true){
// 					document.removefrm.submit();
					modal.css("display","none");
					alert("취소완료");
					
					$.ajax({
						url:"./cancelOrder.or",
						data:{
							"order_id":jlist[choice].order_id
							},
						success:function(){
// 							alert("갓다옴");
						},error:function(){
							alert("에러");
						}
					});
					
				}else{
					return false;
				}
			});
		});
	</script>
	<footer>
		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
	</footer>
</body>
</html>