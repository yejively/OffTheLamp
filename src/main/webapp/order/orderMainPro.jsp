<%@page
	import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<link href="./css/cinema.css" rel="stylesheet">
<link href="./css/pay_v2.css" rel="stylesheet">
<link href="./css/pay_v1.css" rel="stylesheet">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
<%-- 		<% --%>
// 			String id = (String)session.getAttribute("user_id");
// 			session.setAttribute("user_id", id);
<%-- 		%> --%>
		
		$(function(){		
		console.log("main -> pro 세션아이디 : "+"${sessionScope.user_id}");
					
		function showSeat(){
			
			var getSeat = "${param.seat}";
			console.log("seatList : "+getSeat);
			
 			var seatList = getSeat.split(',');	// getSeat를 ,로구분해 배열로 저장
 			console.log(seatList.length);
			
	 		for(var i=0; i<seatList.length-1; i++){
	 			for(var j=0; j<40; j++){
	 		
 	 					if($("#"+j).text()==seatList[i]){
	 						console.log("seatList["+i+"]");
	 						$('#'+j).css('backgroundColor','black');
	 						$('#'+j).addClass("xxseat");
	 						$('#'+j).off('click');	// 팔린좌석은 선택안됨.
	 					}
	 			}
	 		}
		}
		
		$("button[type='button']").click(function(){	
			$("button[type='button']").not('.xxseat').css('backgroundColor','#C8C8C8');
			$(this).css('backgroundColor','grey');	
			
			var seat = $(this).text();
			console.log(seat);
			$('#seat').val(seat);
		});	
		
		showSeat();
		
	 });

</script>
</head>
<body>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
	<!-- 여기 예매 페이지 꾸며아함. -->
	<main>
		<div id="body-wrapper">
		<div id="body-content">
		<section id="section">
			<div class="divbox1">
				<section>
					<table>
						<div class="divbox2">관람존 안내</div>
						<div class="carInfo">
							<tr>
								<td class="td">차량번호</td>
								<td class="td">차량종류</td>
							</tr>
							<tr>
								<td><input type="text" value="${param.car_num }" readonly></td>
								<td><input type="text" value="${param.car_type }" readonly></td>
							</tr>
						</div>					
					</table>
					
						<div class="origin">
							<div id="divXY2">SCREEN</div>
						</div>

					<div class="seatMap" id="seatMap"
						style="width: 432px; height: 360px;">

		<style>
			.origin{
			    border: 1px solid black;
			    float: left;
			    perspective: 150px;
			    margin-top: 80px;
			    margin-left: 100px;
			}
			#divXY2{
				font-size: 30px;
			    transform: rotateX(-30deg);
			    color: black;
			    text-align: center;
			    line-height: 70px;
			    font-weight: bold;
			}
			.origin > div{
  				width: 300px;
   				height: 70px;
    			background-color: white;
    			/* transition : all 3s; */
			}
			
		</style>

						
						<button type="button" id="0" class="seat soldout1"
							style="top: 36px; left: 36px; background-color: #C8C8C8">A1</button>
						<button type="button" id="1" class="seat soldout"
							style="top: 36px; left: 72px; background-color: #C8C8C8">A2</button>
						<button type="button" id="2" class="seat soldout"
							style="top: 36px; left: 108px; background-color: #C8C8C8">A3</button>
						<button type="button" id="3" class="seat soldout"
							style="top: 36px; left: 144px; background-color: #C8C8C8">A4</button>
						<button type="button" id="4" class="seat soldout"
							style="top: 36px; left: 180px; background-color: #C8C8C8">A5</button>
						<button type="button" id="5" class="seat soldout"
							style="top: 36px; left: 216px; background-color: #C8C8C8">A6</button>
						<button type="button" id="6" class="seat soldout"
							style="top: 36px; left: 252px; background-color: #C8C8C8">A7</button>
						<button type="button" id="7" class="seat soldout"
							style="top: 36px; left: 288px; background-color: #C8C8C8">A8</button>
						<button type="button" id="8" class="seat soldout"
							style="top: 36px; left: 324px; background-color: #C8C8C8">A9</button>
						<button type="button" id="9" class="seat soldout"
							style="top: 36px; left: 360px; background-color: #C8C8C8">A10</button>
						<button type="button" id="10" class="seat available"
							style="top: 72px; left: 36px; background-color: #C8C8C8">B1</button>
						<button type="button" id="11" class="seat available"
							style="top: 72px; left: 72px; background-color: #C8C8C8">B2</button>
						<button type="button" id="12" class="seat available"
							style="top: 72px; left: 108px; background-color: #C8C8C8">B3</button>
						<button type="button" id="13" class="seat available"
							style="top: 72px; left: 144px; background-color: #C8C8C8">B4</button>
						<button type="button" id="14" class="seat available"
							style="top: 72px; left: 180px; background-color: #C8C8C8">B5</button>
						<button type="button" id="15" class="seat available"
							style="top: 72px; left: 216px; background-color: #C8C8C8">B6</button>
						<button type="button" id="16" class="seat available"
							style="top: 72px; left: 252px; background-color: #C8C8C8">B7</button>
						<button type="button" id="17" class="seat available"
							style="top: 72px; left: 288px; background-color: #C8C8C8">B8</button>
						<button type="button" id="18" class="seat available"
							style="top: 72px; left: 324px; background-color: #C8C8C8">B9</button>
						<button type="button" id="19" class="seat available"
							style="top: 72px; left: 360px; background-color: #C8C8C8">B10</button>
						<button type="button" id="20" class="seat available"
							style="top: 108px; left: 36px; background-color: #C8C8C8">C1</button>
						<button type="button" id="21" class="seat available"
							style="top: 108px; left: 72px; background-color: #C8C8C8">C2</button>
						<button type="button" id="22" class="seat available"
							style="top: 108px; left: 108px; background-color: #C8C8C8">C3</button>
						<button type="button" id="23" class="seat available"
							style="top: 108px; left: 144px; background-color: #C8C8C8">C4</button>
						<button type="button" id="24" class="seat available"
							style="top: 108px; left: 180px; background-color: #C8C8C8">C5</button>
						<button type="button" id="25" class="seat available"
							style="top: 108px; left: 216px; background-color: #C8C8C8">C6</button>
						<button type="button" id="26" class="seat available"
							style="top: 108px; left: 252px; background-color: #C8C8C8">C7</button>
						<button type="button" id="27" class="seat available"
							style="top: 108px; left: 288px; background-color: #C8C8C8">C8</button>
						<button type="button" id="28" class="seat available"
							style="top: 108px; left: 324px; background-color: #C8C8C8">C9</button>
						<button type="button" id="29" class="seat available"
							style="top: 108px; left: 360px; background-color: #C8C8C8">C10</button>
						<button type="button" id="30" class="seat available"
							style="top: 144px; left: 36px; background-color: #C8C8C8">D1</button>
						<button type="button" id="31" class="seat available "
							style="top: 144px; left: 72px; background-color: #C8C8C8">D2</button>
						<button type="button" id="32" class="seat available"
							style="top: 144px; left: 108px; background-color: #C8C8C8">D3</button>
						<button type="button" id="33" class="seat available"
							style="top: 144px; left: 144px; background-color: #C8C8C8">D4</button>
						<button type="button" id="34" class="seat available"
							style="top: 144px; left: 180px; background-color: #C8C8C8">D5</button>
						<button type="button" id="35" class="seat available"
							style="top: 144px; left: 216px; background-color: #C8C8C8">D6</button>
						<button type="button" id="36" class="seat available"
							style="top: 144px; left: 252px; background-color: #C8C8C8">D7</button>
						<button type="button" id="37" class="seat available"
							style="top: 144px; left: 288px; background-color: #C8C8C8">D8</button>
						<button type="button" id="38" class="seat available"
							style="top: 144px; left: 324px; background-color: #C8C8C8">D9</button>
						<button type="button" id="39" class="seat available"
							style="top: 144px; left: 360px; background-color: #C8C8C8">D10</button>
					</div>


				</section>
			</div>

			<div class="divbox3">
				<section>
					<div class="divbox4">결제</div>				
					<p>영화</p>
					<input type="text" id="movieName" value="${param.movie }" readonly>
					<p>극장</p>
					<input type="text" value="${param.cinema }" readonly>
					<p>상영시간</p>
					<input type="text" value="${param.time }" readonly>
					<p>영화가격</p>
					<input type="text" id="price" value="${param.price }" readonly>
					<p>좌석</p>
					<input type="text" id="seat" readonly>
					<input class="btn1" type="button" id="re2" value="결제"
					onclick="requestPay()">
				</section>
			</div>

		</section>
		</div>
	</main>

	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<script type="text/javascript">


		IMP.init('imp73450751');

		function requestPay() {
			console.log("${sessionScope.user_id}");
			var id = "${sessionScope.user_id}";
			var userInfo;
			$.ajax({
				// 비회원일때 회원일때 구분해야함.
				url : "./getMemberInfo.or",
				data : {
					"user_id":id,	// 
					}, // 로그인을 하면 들어오게 해주는 값이라 합칠때 수정 필수!!!
				success : function(data){
					// 회원일때 name, phone, num | 비회원일때 name, id
					info = data.split(",");
					console.log(info[0]);
					console.log(info[1]);
					console.log(info[2]);	
					
					var uniqeNum = info[1].substr(8,4);		// 주문번호는 개인의 휴대번호뒷자리에 + 랜덤숫자 2개
					var a = Math.floor(Math.random()*100);	// 0.4897456136 -> 40.78411326 ->ㅡMath.floor(소수점부터제거)
					
			// 결제 api 실행
				IMP.request_pay({
					pg : "html5_inicis.INIpayTest",
					pay_method : "card",
					merchant_uid : uniqeNum+a,
					name : $('#movieName').val(),
					amount : 100,  	//  $('#price').val()
					buyer_tel : info[1],
					buyer_name : info[0],
				}, function(rsp) { // callback
					//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
					if (rsp.success) {
						// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
						// jQuery로 HTTP 요청
						console.log("주문번호 : "+rsp.merchant_uid);
						console.log(rsp.imp_uid);
						console.log(rsp.pay_method);
						var time = "${param.time}";
						console.log("pro time : "+time);
						jQuery.ajax({
							url : "./paymentSuccess.or",
							method : "POST",
							data : {
								// 결제 + 예매테이블에 필요한 정보를 담아갈거임.
								"imp_uid" : rsp.imp_uid, // 결제 고유번호
								"merchant_uid" : rsp.merchant_uid, // 주문번호
								"pg" : "inicis",
								"payment_method" : rsp.pay_method,
								"movie_name" : "${param.movie}",
								"price" : ${param.price},
 								"name" : info[0],
 								"phone": info[1],	
 								"nonphone":id,	
 								"region" : "${param.region}",
 								"user_num" : info[2],
 								"id" : "${sessionScope.id}",		
 								"cinema" : "${param.cinema}",
 								"car_type" : "${param.car_type}",
 								"car_num" : "${param.car_num}",
 								"time" : "${param.time}",
 								"seat" : $('#seat').val()	// 바꿔야함.
							},success:function(){
 								var id = "${sessionScope.user_id}";
 								if(id.indexOf('@') != -1){
 									alert("결제완료 마이페이지로 이동하겠습니다.");	
 									location.href="./MyPageMain.or";
 								}else{
 									alert("결제완료 비회원 예매취소/환불은 고객센터로 문의해주세요")
 									location.href='./Main.or';
 								}							
							}						
						}).done(function() {

						})
					} else {
						alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
					} 
				});
				} //success

			}); // ajax
		} //requestPay()

	</script>
	
	<input type="hidden" id="seathi">

	<!-- footer아래로는 코드 금지 -->

	<footer>
		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
	</footer>

	<script>
		window.onscroll = function() {
			myFunction()
		};

		var navbar_sticky = document.getElementById("navbar_sticky");
		var sticky = navbar_sticky.offsetTop;
		var navbar_height = document.querySelector('.navbar').offsetHeight;

		function myFunction() {
			if (window.pageYOffset >= sticky + navbar_height) {
				navbar_sticky.classList.add("sticky")
				document.body.style.paddingTop = navbar_height + 'px';
			} else {
				navbar_sticky.classList.remove("sticky");
				document.body.style.paddingTop = '0'
			}
		}
	</script>

</body>
</html>