<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Off The Lamp</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/global.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap"
	rel="stylesheet">
<script src="./js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/loginPage.css" />
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript">
    Kakao.init('de2279459191574f31f380ed2e0961df');
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                    	var resp = response.kakao_account;
//                     	console.log(resp);
                    	if(resp.is_email_verified == true){
                    	   $.ajax({
	                    	   type: "POST",
	                    	   url: "./KakaoLoginAction.me",
	                    	   data: {kakaoEmail:resp.email,
	                    		   		kakaoNickname:resp.profile.nickname,
	                    		   		kakaoLogin:true
	                    	   			},
	                       	   success:function(result){
	                       		   console.log(result);
	                       		   if(result.trim() == "true"){
	                       			   location.href="./Main.me";
	                       		   }else{
	                       			   alert('오류');
	                       		   }
	                       	   },
	                       	   error: function(){
	                       		   console.log("ajax 오류");
	                       	   }
                       	   });//ajax
                       }else{
                    	   
                       }//if
                    },//success
                    fail: function (error) {
                        alert(JSON.stringify(error))
                    },
                })
            },
            fail: function (error) {
                alert(JSON.stringify(error))
            },
        })
    }
</script>
<head>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>

	<!--center -----------------------------------------------------------  -->
<section id="center" class="center_o pt-2 pb-2">
	<div class=pp>
		<fieldset class="id_pw_wrap">
			<legend >로그인</legend><hr>
			<p id='hidden2'></p>
			<form id="f1" action="./UserLoginAction.me" method="post">
			
					<input type="text" name="id" placeholder="이메일형식" class="id_pw"> <br>
					<p id='hiddenMsgId'></p>
					<input type="password" name="pw" placeholder="비밀번호" class="id_pw"> 
				<br>
				<button type="submit" class="btn_login1" >
					<span >로그인</span>
				</button> 
				<p id='hiddenLogin'></p>
			</form>
	
		<div class="panel_item2">
			<a href="./UserFindId.me">아이디찾기</a> |
			<a href="./UserFindPw.me">비밀번호찾기</a> |
			<a href="./UserJoin.me">회원가입</a> <br>
			<hr>
			<p id='hidden'></p>
			<a id="kakao" href="javascript:kakaoLogin()"><img src="./img/kakaobutton.png" width="250px;"></a>
			
		</div>
		</fieldset>
		
		<fieldset class="id_pw_wrap">
			<legend >비회원예매</legend><hr>
			<p id='hidden3'></p>
			<form id="f2" action="./NonUserLoginAction.me" method="post">
			
					<input id="F" type="text" name="nonuser_name" placeholder="이름" class="id_pw" required="required"> <br>
					<p id='hidden1'></p>
					<input id="G" type="text" name="nonuser_phone" placeholder="휴대폰번호'-'빼고 입력" class="id_pw" required="required"> <br>
					<p id='hidden1'></p>
					<input id="C" type="password" name="nonuser_pass" placeholder="비밀번호" class="id_pw" required="required"> <br>
					<p id='hidden1'></p>
					<input id="D" type="password" name="pwCheck" placeholder="비밀번호확인" class="id_pw" required="required"> <hr>
				
				<button type="submit" class="btn_login" onclick="return check()">
					<span class="A">비회원예매바로가기</span>
				</button>
			</form>
		</fieldset>
		<input type="hidden" class="btn_login">
		<script>
		
		function check(){
		
		 if(	$("#C").val()==$("#D").val() ){
			 return true;
			 
		 }else{
			 alert("비밀번호 확인을 다시 입력해주세요");
			 
			 return false;
		 }
		}
		</script>
	</div>
		<hr>
 </section>

<!--center end-------------------------------------------------------------  -->

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