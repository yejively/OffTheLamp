<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/introduceMain.css">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ur2rm9e363"></script>
<title>Off The Lamp</title>
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
			$.ajax({
				url : "./CinemaSelectAction.bo",
				dataType : "json",
				success : function(data){
						// 첫 화면 서울/경기 극장 출력 
						$.each(data,function(idx,item){
							if(item.region == "경기"){
								var list = item.cinema_list.split(",");
								for(var i=0 ; i < list.length ; i++){
									$('.cinema').append("<h2 class='h1"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='h1"+i+"' class = 'btn_region'> <hr>");
								}//for
							}//if
						});//each
						
						// 서울/경기 버튼 클릭시 해당 극장 출력
						$("#s").click(data,function(){
							$(".cinema").empty();
							$.each(data,function(idx,item){
								if(item.region == "경기"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='h1"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='h1"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
							});//each
						});//click
						  
						// 극장이름 클릭시 정보 표시 
						$(document).on("click", ".h10",function(){ // 한국 민속촌 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기도 용인시 기흥구 민속촌로 93-1<br> 연락처 : 031-288-0000</p>");
						  });//click 
						$(document).on("click",".h11",function(){ // 포천 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기 포천시 소흘읍 광릉수목원로 967<br> 연락처 : 031-541-5442</p>"); 
						  });//click
						$(document).on("click",".h12",function(){ // 평택호 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기 평택시 현덕면 권관리 351-1<br> 연락처 : 031-682-0410</p>"); 
						  });//click
						$(document).on("click",".h13",function(){ // 퍼스트가든 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기 파주시 상지석동 197-8<br> 연락처 : 031-957-6861 </p>"); 
						  });//click
						$(document).on("click",".h14",function(){ // 초이 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>세종특별자치시 장군면 대교리 557-5<br> 연락처 : 044-272-7933</p>"); 
						  });//click
						$(document).on("click",".h15",function(){  // 장흥 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기 양주시 장흥면 일영리 56-42<br> 연락처 : 031-855-6050</p>"); 
						  });//click
						$(document).on("click",".h16",function(){ // 자유로 자동차 극장 
							$(".add").empty();
						    $(this).append("<p class = 'add'>경기 파주시 탄현면 낙하리 265-11<br>연락처 : 031-945-0609</p>"); 
						  });//click
						
						// 부산/대구 버튼 클릭시 해당 극장 출력 
						$("#b").click(data,function(){
							$(".cinema").empty();
							$.each(data,function(idx,item){
								if(item.region == "부산"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										var arr = [list];
										console.log(arr);
										$('.cinema').append("<h2 class='Busan"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='Busan"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
								if(item.region == "대구"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										var arr = [list];
										console.log(arr);
										$('.cinema').append("<h2 class='Deagu"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='Deagu"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
								if(item.region == "경상"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										var arr = [list];
										console.log(arr);
										$('.cinema').append("<h2 class='kyng"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='kyng"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
								if(item.region == "울산"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										var arr = [list];
										console.log(arr);
										$('.cinema').append("<h2 class='San"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='San"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
							});//each
						});//click
						
						$(document).on("click",".kyng0",function(){ // 엠씨네마
							$(".add").empty();
						    $(this).append("<p class = 'add'>경북 울진군 후포면 후포리 산 6-14<br> 연락처 : 054-788-7800</p>");						
						    });
						$(document).on("click",".Deagu0",function(){ // 팔공산
							$(".add").empty();
						    $(this).append("<p class = 'add'>대구 동구 용수동 산 51<br> 연락처 : 053-984-8008</p>");						
						    });
						$(document).on("click",".Busan0",function(){ // 오시리아
							$(".add").empty();
						    $(this).append("<p class = 'add'>부산 기장군 기장읍 시랑리 732<br> 연락처 : 051-714-4110</p>");						
						    });
						$(document).on("click",".San0",function(){ // 블루마씨네
							$(".add").empty();
						    $(this).append("<p class = 'add'>울산 북구 산하동 산 24<br> 연락처 : 052-298-6622</p>");						
						    });
						$(document).on("click",".San1",function(){ // 범서온천
							$(".add").empty();
						    $(this).append("<p class = 'add'>울산 울주군 두동면 은편리 산 18-1<br> 연락처 : 051-244-4041</p>");						
						    });
						
						//대전/충청 버튼 클릭시 해당 극장 출력
						$("#d").click(data,function(){
							$(".cinema").empty();
							$.each(data,function(idx,item){
								if(item.region == "대전"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='Deaj"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='Deaj"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
								if(item.region == "충청"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='Chung"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='Chung"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
							});//each
						});//click
						$(document).on("click",".Deaj0",function(){ // 대전 
							$(".add").empty();
						    $(this).append("<p class = 'add'>대전 서구 평촌동 945<br> 연락처 : 042-863-0104</p>");						
						    });
						$(document).on("click",".Chung0",function(){ // 용봉산
							$(".add").empty();
						    $(this).append("<p class = 'add'>충남 홍성군 홍북읍 상하리 104-58<br> 연락처 : 041-631-8123</p>");						
						    });
						$(document).on("click",".Chung1",function(){ // 천안
							$(".add").empty();
						    $(this).append("<p class = 'add'>충남 천안시 동남구 성남면 화성리 산 30-3<br> 연락처 : 041-555-4895</p>");						
						    });
						$(document).on("click",".Chung2",function(){ // 오송
							$(".add").empty();
						    $(this).append("<p class = 'add'>충북 청주시 흥덕구 오송읍 서평리 583-8<br> 연락처 : 043-238-0590</p>");						
						    });
						
						
						//광주/전라 버튼 클릭시 해당 극장 출력 
						$("#g").click(data,function(){
							$(".cinema").empty();
							$.each(data,function(idx,item){
								if(item.region == "광주"| item.region == "전라"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='h4"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='h4"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
							});//each
						});//click
						$(document).on("click",".h40",function(){ // 여수
						$(".add").empty();
					    $(this).append("<p class = 'add'>전남 여수시 소호동 산 134<br> 연락처 : 061-691-6263</p>");						
					    });
						
						//강원도/제주 버튼 클릭시 해당 극장 출력 
						$("#j").click(data,function(){
							$(".cinema").empty();
							$.each(data,function(idx,item){
								if(item.region == "제주"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='h5"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='h5"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
								if(item.region == "강원"){
									var list = item.cinema_list.split(",");
									for(var i=0 ; i < list.length ; i++){
										$('.cinema').append("<h2 class='h6"+i+"'>"+list[i]+"</h2><input type = 'button' value = '위치보기' id ='h6"+i+"' class = 'btn_region'><hr>");
									}//for
								}//if
							});//each
						});//click
						$(document).on("click",".h50",function(){ // 제주
							$(".add").empty();
						    $(this).append("<p class = 'add'>제주특별자치도 제주시 이호일동 1961<br> 연락처 : 064-748-8058</p>");						
						    });
						$(document).on("click",".h60",function(){ // 강릉
							$(".add").empty();
						    $(this).append("<p class = 'add'>강원특별자치도 강릉시 구정면 어단리 954-1<br> 연락처 : 033-645-7200</p>");						
						    });
						
						// 위치보기 버튼 클릭시 지도에 위치 표시 
						$(document).on("click","#h10", function(e){ // 한국 민속촌 자동차 극장
							e.preventDefault();
						    map.setCenter(minsok);
						    map.panToBounds(minsok2);
						    marker.setPosition(minsok);
						});
						$(document).on("click","#h11", function(e){ // 포천 자동차 극장 
							e.preventDefault();
						    map.setCenter(pochen);
						    map.panToBounds(pochen2);
						    marker.setPosition(pochen);
						});
						$(document).on("click","#h12", function(e){ // 평택호 자동차 극장 
							e.preventDefault();
						    map.setCenter(pyeongtaek);
						    map.panToBounds(pyeongtaek2);
						    marker.setPosition(pyeongtaek);
						});
						$(document).on("click","#h13", function(e){ // 퍼스트가든 자동차 극장 
							e.preventDefault();
						    map.setCenter(first);
						    map.panToBounds(first2);
						    marker.setPosition(first);
						});
						$(document).on("click","#h14", function(e){ // 초이 자동차 극장 
							e.preventDefault();
						    map.setCenter(choi);
						    map.panToBounds(choi2);
						    marker.setPosition(choi);
						});
						$(document).on("click","#h15", function(e){ // 장흥 자동차 극장 
							e.preventDefault();
						    map.setCenter(janghung);
						    map.panToBounds(janghung2);
						    marker.setPosition(janghung);
						});
						$(document).on("click","#h16", function(e){ // 자유로 자동차 극장 
							e.preventDefault();
						    map.setCenter(jayuro);
						    map.panToBounds(jayuro2);
						    marker.setPosition(jayuro);
						});
						$(document).on("click","#kyng0", function(e){ // 엠씨네마 자동차 극장 
							e.preventDefault();
						    map.setCenter(mcinema);
						    map.panToBounds(mcinema2);
						    marker.setPosition(mcinema);
						});
						$(document).on("click","#Deagu0", function(e){ // 씨네팔공산 자동차 극장 
							e.preventDefault();
						    map.setCenter(palgong);
						    map.panToBounds(palgong2);
						    marker.setPosition(palgong);
						});
						$(document).on("click","#Busan0", function(e){ // 오시리아 자동차 극장 
							e.preventDefault();
						    map.setCenter(osiria);
						    map.panToBounds(osiria2);
						    marker.setPosition(osiria);
						});
						$(document).on("click","#San0", function(e){ // 블루씨네자동차 극장 
							e.preventDefault();
						    map.setCenter(blue);
						    map.panToBounds(blue2);
						    marker.setPosition(blue);
						});
						$(document).on("click","#San1", function(e){ // 범서온천 자동차 극장 
							e.preventDefault();
						    map.setCenter(bumseo);
						    map.panToBounds(bumseo2);
						    marker.setPosition(bumseo);
						});
						$(document).on("click","#Deaj0", function(e){ // 대전 자동차 극장 
							e.preventDefault();
						    map.setCenter(dejoun);
						    map.panToBounds(dejoun2);
						    marker.setPosition(dejoun);
						});
						$(document).on("click","#Chung0", function(e){ // 용봉산 자동차 극장 
							e.preventDefault();
						    map.setCenter(yongbong);
						    map.panToBounds(yongbong2);
						    marker.setPosition(yongbong);
						});
						$(document).on("click","#Chung1", function(e){ // 천안 자동차 극장 
							e.preventDefault();
						    map.setCenter(chunan);
						    map.panToBounds(chunan2);
						    marker.setPosition(chunan);
						});
						$(document).on("click","#Chung2", function(e){ // 오송 자동차 극장 
							e.preventDefault();
						    map.setCenter(osong);
						    map.panToBounds(osong2);
						    marker.setPosition(osong);
						});
						$(document).on("click","#h40", function(e){ // 여수 자동차 극장 
							e.preventDefault();
						    map.setCenter(yeosu);
						    map.panToBounds(yeosu2);
						    marker.setPosition(yeosu);
						});
						$(document).on("click","#h60", function(e){ // 강릉 자동차 극장 
							e.preventDefault();
						    map.setCenter(kangrung);
						    map.panToBounds(kangrung2);
						    marker.setPosition(kangrung);
						});
						$(document).on("click","#h50", function(e){ // 제주 자동차 극장 
							e.preventDefault();
						    map.setCenter(jeju);
						    map.panToBounds(jeju2);
						    marker.setPosition(jeju);
						});
						
				}//success
			});//ajax 끝
		}); //JQuery 끝
	</script>
	<style type="text/css">
	.introhr{
	background-color: black;
	}
	.title{
	text-align: center;
	}
	</style>

</head>
<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
<!-- 상단 바 고정 -->

	<!-- 여기 소개 게시판 꾸며아함. -->

	<div class="container">
		<hr class ="introhr">
		<h1 class = "title">소개게시판</h1>
		<hr>
		<div class = "region_list">
		<input type="button" value = "서울/경기" id="s" class = "btn_region1">
		<input type="button" value = "부산/대구/경상" id="b" class = "btn_region1">
		<input type="button" value = "대전/충청" id="d" class = "btn_region1">
		<input type="button" value = "광주/전라" id="g" class = "btn_region1">
		<input type="button" value = "강원도/제주" id="j" class = "btn_region1">
		<hr>
		</div>
		
		<div id="map" style="width:100%; height:400px;"></div>
		
		<hr>
		<div class="cinema"></div>
		
		
		

	</div>
	<!-- footer아래로는 코드 금지 -->

	<!-- 하단바 고정  -->
	<footer>
		<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
	</footer>
	<!-- 하단바 고정  -->
	
	<script>
		var map = new naver.maps.Map('map', {
		    center: new naver.maps.LatLng(37.257607, 127.114500), // 잠실 롯데월드를 중심으로 하는 지도
		    zoom: 15
		});
		
		var minsok = new naver.maps.LatLng(37.257607, 127.114500),
			minsok2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(37.258190, 127.111978),
	               	  new naver.maps.LatLng(37.256512, 127.117096)),

			pochen = new naver.maps.LatLng(37.776108, 127.138853),
			pochen2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(37.777116, 127.136669),
	               	  new naver.maps.LatLng(37.775704, 127.140830)),
			
			pyeongtaek = new naver.maps.LatLng(36.921438, 126.922246),
			pyeongtaek2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(36.921716, 126.921026),
	               	  new naver.maps.LatLng(36.921067, 126.923359)),
	               	  
			first = new naver.maps.LatLng(37.731704, 126.794693),
			first2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(37.732289, 126.792386),
	               	  new naver.maps.LatLng(37.730574, 126.797383)),
			
			choi = new naver.maps.LatLng(36.502412, 127.212446),
			choi2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(36.502926, 127.211257),
	               	  new naver.maps.LatLng(36.502262, 127.214157)),
	               	  
			janghung = new naver.maps.LatLng(37.724606, 126.950117),
			janghung2 = new naver.maps.LatLngBounds(
	              	  new naver.maps.LatLng(37.724772, 126.949379),
	               	  new naver.maps.LatLng(37.724588, 126.950909)),
			
			jayuro = new naver.maps.LatLng(37.834700, 126.735073),
			jayuro2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(37.834638, 126.734458),
             	 	  new naver.maps.LatLng(37.834237, 126.736119)),

			mcinema = new naver.maps.LatLng(36.692562, 129.443704),
			mcinema2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(36.693174, 129.441537),
             	 	  new naver.maps.LatLng(36.691885, 129.444665)),

			palgong = new naver.maps.LatLng(35.987833, 128.694085),
			palgong2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(35.988597, 128.692274),
             	 	  new naver.maps.LatLng(35.987173, 128.695256)),

			osiria = new naver.maps.LatLng(35.202130, 129.224879),
			osiria2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(35.202606, 129.223439),
             	 	  new naver.maps.LatLng(35.201309, 129.227047)),

			blue = new naver.maps.LatLng(35.640205, 129.431459),
			blue2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(35.640468, 129.430707),
             	 	  new naver.maps.LatLng(35.640028, 129.432054)),

			bumseo = new naver.maps.LatLng(35.625375, 129.221525),
			bumseo2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(35.625555, 129.221101),
             	 	  new naver.maps.LatLng(35.625126, 129.222104)),

			dejoun = new naver.maps.LatLng(36.231677, 127.317531),
			dejoun2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(36.232108, 127.316687),
             	 	  new naver.maps.LatLng(36.231265, 127.318194)),

			yongbong = new naver.maps.LatLng(36.643399, 126.662124),
			yongbong2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(36.643838, 126.660710),
             	 	  new naver.maps.LatLng(36.642908, 126.663719)),

			chunan = new naver.maps.LatLng(36.756569, 127.262282),
			chunan2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(36.757420, 127.260210),
             	 	  new naver.maps.LatLng(36.756270, 127.264708)),

			osong = new naver.maps.LatLng(36.585216, 127.314924),
			osong2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(36.586430, 127.312349),
             	 	  new naver.maps.LatLng(36.584130, 127.319022)),

			yeosu = new naver.maps.LatLng(34.730446, 127.642867),
			yeosu2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(34.730977, 127.641492),
             	 	  new naver.maps.LatLng(34.730289, 127.644210)),

			kangrung = new naver.maps.LatLng(37.693739, 128.896172),
			kangrung2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(37.694216, 128.894990),
             	 	  new naver.maps.LatLng(37.693113, 128.897801)),

			jeju = new naver.maps.LatLng(33.495772, 126.466836),
			jeju2 = new naver.maps.LatLngBounds(
            		  new naver.maps.LatLng(33.496326, 126.466098),
             	 	  new naver.maps.LatLng(33.495665, 126.467917));
		
		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(37.257607, 127.114500),
		    map: map
		});
	</script>
	


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