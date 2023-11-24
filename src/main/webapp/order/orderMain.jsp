<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Off The Lamp</title>
<link href="./css/cinema.css" rel="stylesheet">
<link href="./css/pay_v2.css" rel="stylesheet">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">

	// 비회원 회원을 나눠서 구현하면 - 둘 중 하나는 null값이기때문에 오류
	// 세션에서 가져오는 아이디값을 하나로 통일 - 비회원(아이디 : autoincrement 숫자)
	//											- 회원(아이디 : 이메일형식 문자가반드시 포함돼있어야함.)
	<%
		String id = (String) session.getAttribute("user_id");
		session.setAttribute("user_id", id);
		System.out.println("user_id : "+id );
	%>
	
	// jquery 시작
	$(document).ready(function() {
	
	// 날짜가 자동으로 변경
	var now = new Date();
	var month = now.getMonth()+1;	// 월
	var date = now.getDate();	// 일
		
	$('#month').text(month+"월");
	$('#1').text(date + "일");
	$('#2').text((date+1) +"일");
	$('#3').text((date+2)+"일");
	
	// 세션아이디 null, 지역, 날짜, 극장, 영화, 차번, 차종 선택안했을시 차량등록버튼 제어	
	$('#btn1').click(function(){
		var id = "${sessionScope.user_id}";
		if(id == ""){
			alert("로그인이 필요한 서비스입니다");
			return false;
		}else if($('#text1').val() == ""){
			alert("차량정보를 기입해주세요");
			$( "#text1" ).trigger( "focus" );
		}else if($('#choiceDate').val()==""){
			alert("날짜를 선택해주세요");
			return false;
		}else if($('#cinema').val()==""){
			alert("극장을 선택해주세요");
			return false;
		}else if($('#movie').val()==""){
			alert("영화를 선택해주세요");
			return false;
		}else if($('#time').val()==""){
			alert("시간을 선택해주세요");
			return false;
		}
	});
	

	// 세션아이디 null, 지역, 날짜, 극장, 영화, 차번, 차종, 차량등록 선택안했을시 좌석선택버튼 제어
	$('#seatSubmit').click(function(){
		var id = "${sessionScope.user_id}";
		if(id == ""){
			alert("로그인이 필요한 서비스입니다");
			return false;
		}else if($('#choiceDate').val()==""){
			alert("날짜를 선택해주세요");
			return false;
		}else if($('#cinema').val()==""){
			alert("극장을 선택해주세요");
			return false;
		}else if($('#movie').val()==""){
			alert("영화를 선택해주세요");
			return false;
		}else if($('#time').val()==""){
			alert("시간을 선택해주세요");
			return false;
		}else if($('#car_num').val()=="" && $('#car_type').val()==""){
			alert("차량등록을 해주세요");
			return false;
		}
	});
	
			
	// 날짜선택없이 지역선택할시 제어하기
	
	$('.choiceDate').css("cursor","pointer").click(function(){
		// 선택날짜	
		var choiceDate = $(this).text();
		$('#choiceDate').val(choiceDate);
		console.log("choiceDate : "+choiceDate);
		$('.choiceDate').css('color','white');
		$(this).css('color','red');
		
	
		
		$('.region').css("cursor","pointer").click(function() {
			
		var region = $(this).text();

		var region1 = region.substr(0, 2);
		var region2 = region.substr(3, 2);
		
		$('.region').not(this).css('color','white');
		$(this).css('color','red');
		
		// cinemaInfo -> 지역선택시 지역에맞는 극장들의 데이터를 가져옴.
		$.ajax({
			url : "./cinemaInfo.or",
			data : {
				"region1" : region1,
				"region2" : region2
			},
				success : function(data) {
					console.log(data);
					$('#theater_list').empty();
					$.each(data,function(idx,item){					
						var cinema_name = item.cinema_name;
						console.log(cinema_name);
						$('#theater_list').append("<a class='showMovie'>"+cinema_name+"</a> <br>")			
					});
					
					   $('.showMovie').css("cursor","pointer").click(function(){
							var theater = $(this).text();
							$('#cinema').val(theater);
							$('.showMovie').not(this).css('color','white');
							$(this).css('color','red')
							
							// theaterInfo -> 극장선택시 날짜에 해당하는 상영중인 영화의 데이터를 가져옴
							$.ajax({
								url : "./theaterInfo.or",
								data : {
									"theater":theater,
									"date":choiceDate
									},
								success : function(data){
									console.log(data);
									$('#movieName').empty();
									$.each(data,function(idx,item){
										// movie_name : 극장에서 상영중인 영화들
										var movie_name = item.movie_name;
										$('#movieName').append("<a class='showTime'>"+movie_name+"</a> <br>")
									});
				
									$('.showTime').css("cursor","pointer").click(function(){
										var movie = $(this).text();
										$('#movie').val(movie);
										$('.showTime').not(this).css('color','white');
										$(this).css('color','red')
											
											// 영화선택시 극장에서 그영화를 상영하는 상영시간의 데이터를 가져옴
											$.ajax({
											url : "./movieInfo.or",
											data : {"movie":movie,"theater":theater},
											success : function(data){
												console.log(data);									
												$('#movieTime').empty();
												$.each(data,function(idx,item){
													// 상영중인 영화의 상영시간
													$('#movieTime').append("<a class='time'>"+item.movieTime+"</a> <br>");
													// 영화 가격
													$('#price').val(item.price);
													console.log($('#price').val());
												});	
												
												$('.time').css("cursor","pointer").click(function(){
													var time = $(this).text();
													$('#time').val(time);
													$('.time').not(this).css('color','white');
													$(this).css('color','red');												
													
													
													$('#btn1').css("cursor","pointer").click(function(){
														var car_num = $('#text1').val();
														var car_type = $('#option').val();
														
														alert("차량이 등록되었습니다");														
																												
														
														// seatPayment.or - 차량선택시 결제예매에필요한 지역/좌석을 가져옴.
														$.ajax({
															url:"./seatPayment.or",
															data:
															{
																"theater":theater,
																"movie":movie,
																"time":time,
															},
															success:function(data){
																console.log(data);
																console.log(data[5]);
																var seat = [];					
																$.each(data,function(idx,item){
																	// 가져온 데이터를 orderMainPro페이지로 form을 통해 전달.
																	// input hidden타입 각각의 아이디에 ajax로 받아온 데이터를 추가															
																	var region = item.region;
																	seat.push(item.seat);																
																	$('#region').val(region);
																	$('#car_num').val(car_num);
																	$('#car_type').val(car_type);
																});
																console.log(seat);
																$('#seat').val(seat);
															},
															error:function(){
																alert("전달오류");
															}
														});
														
													});
													
												});															
											},
											error : function(){
												alert("오류!");
											}									
										});	// movie click
							
									});
									
								},
								error : function(){
									alert("오류!");
								}
							});
						}); // cinema click

				},
				error : function() {
					alert("오류!");
				}
			});

		}); // region click
	}); // date click
	
	
		$('.noCinema').css("cursor","pointer").click(function(){
			alert("지역을 선택해주세요");
		});
		
		$('.noMovie').css("cursor","pointer").click(function(){
			alert("극장을 선택해주세요");
		});		
		
		$('.region').css("cursor","pointer").click(function(){
			if($('#choiceDate').val()==""){
				alert("날짜를 선택해주세요");
				return false;
			}

		});

		

	}); // jquery끝	


	function check() {
		var id = "${sessionScope.user_id}";
		if (id == "") {
			alert("로그인이 필요한서비스입니다.");
			return false;
		} else {
			var car_num = document.fr.car.value();
			if (car_num == "") {
				alert("차량정보를 등록해주세요");
				document.fr.car.focus();
				return false;
			} else {
				console.log("아이디 : " + id);
				$('#id').val(id);
			}
		}		
	};

</script>
<style type="text/css">
.title{
	text-align: center;
}
.introhr{
background-color: black;
}
</style>
</head>
<body>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
	<!-- 여기 예매 페이지 꾸며아함. -->
	<main>
		<div id="body-wrapper">
			<div id="body-content" class="mainBody">
				<section id="section">
					<div id="orderMain">
					<hr class ="introhr">
						<h1 class = "title"> 영화 예매 </h1>
						<div id="date">
							<p id="month"></p>
							<div id="day">
							<a id="1" class="choiceDate"></a> <a id="2" class="choiceDate"></a> <a id="3" class="choiceDate"></a>
							</div>
						</div>
						
						<div id="movie_res" class="cdiv">영화예매</div>
						<div id="theater" class="cdiv">영화관</div>
						<div id="theater" class="cdiv">영화/상영시간</div>
						<div id="car_number" class="cdiv">차량등록</div>

						<form action="./seatPaymentPro.or" method="post" name="fr"
							onsubmit="return check(); ">
							<div id="text" class="cdiv">
								<a class="region">서울/경기</a><br> <a class="region">부산/경상</a><br>
								<a class="region">대구/울산</a><br> <a class="region">대전/충청</a><br>
								<a class="region">광주/전라</a><br> <a class="region">강원/제주</a><br>
							</div>
							<div class="cdiv" id="theater_list">
								<a class="noCinema">자유로자동차극장</a><br> <a class="noCinema">장흥자동차극장</a><br>
								<a class="noCinema">초이자동차극장</a><br> <a class="noCinema">퍼스트가든자동차극장</a><br>
								<a class="noCinema">평택호자동차극장</a><br> <a class="noCinema">포천자동차극장</a><br>
								<a class="noCinema">한국민속촌자동차극장</a><br>
							</div>
							<div id="movieName" class="cdiv">
								<a class="noMovie"></a><br>
								<a class="noMovie"></a><br>
								<a class="noMovie"></a><br> 
								<a class="noMovie"></a><br> 
								<a class="noMovie"></a><br> 
								<a class="noMovie"></a><br> 
								<a class="noMovie"></a><br>
								<a class="noMovie"></a>
							</div>
							<div id="movieTime" class="cdiv"></div>
							<div class="cdiv" id="otc">
								<div>
									차량번호<input type="text" id="text1" name="car"><br>
									<br>
									<!-- 옵션 차종 -->
								</div>
								<div id="carnum">
									차량종류 <select id="option">
										<option class="text" value="소형차">소형차</option>
										<option class="text" value="중형차">중형차</option>
										<option class="text" value="대형차">대형차</option>
								</div>
								</select> <input type="hidden" name="region" id="region">
								<input type="hidden" name="movie" id="movie">
								<input type="hidden" name="cinema" id="cinema">
								<input type="hidden" name="time" id="time">
								<input type="hidden" name="car_type" id="car_type">
								<input type="hidden" name="car_num" id="car_num">
								<input type="hidden" name="price" id="price">
								<input type="hidden" name="id" id="user_id">
								<input type="hidden" name="seat" id="seat">
								<input type="hidden" name="choiceDate" id="choiceDate">
								<input type="button" id="btn1" value="차량등록">
								<input type="submit" value="좌석선택" id="seatSubmit">
								<div id="seatDiv">
								<a id="seatImg">>>></a>
								</div>
							</div>
						</form>
					</div>
				</section>
			</div>
	</main>

	<!-- footer아래로는 코드 금지 -->
	
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
	<footer>
		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
	</footer>
	
</body>
</html>