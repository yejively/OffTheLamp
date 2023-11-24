<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./css/findPw.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<title>Off The Lamp</title>
<head>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
	<!--center -------------------------------------------------------------  -->
<section id="center" class="center_o pt-2 pb-2">
   <section id="join_box">
   
    <h1>비밀번호찾기</h1> 
   </section>
   <form action="./UserFindPwAction.me" method="post">
   <fieldset id="join_wrap">
   <label>아이디</label>
   <br>
   <input id="userEmail" type="text" name="user_id" placeholder="이메일형식">
   
   
   <input id="checkEmail" type="button" value="이메일인증"><br>
   <p id="A">이메일이 도착하는데 1~2분 소요됩니다</p>
   <label>인증번호</label>
   <br>
   <input id="usernum" type="text" name="user_mailnum" placeholder="4자리">
   <br>
   <p id="hiddenemailnum"></p>
   <input id="button" type="submit" value="비밀번호찾기" onclick="return check()">
   
   
   </fieldset>
   <input type="hidden" id="random">
   
    <script>
    $("#checkEmail").click(function () {
        const userEmail = $("#userEmail").val();
        const sendEmail = document.forms["sendEmail"];
        $.ajax({
            type: 'post',
            url: './UserEmailAction.me', 
            data: { 
                'user_id': userEmail
            },
            dataType: "text",
            success: function (result) {
            	if(result == ""){
            		alert("정보를 다시 입력해주세요");
            	}else{
            		  alert("인증번호가 전송되었습니다");
          			$("#random").val(result);
            	}
           
            },error: function () {
            	alert("정보를 다시 입력해주세요");
               
            }
        })
    });
    
    function check(){
    	var user_mailnum = $("input[name='user_mailnum']").val();
    	var random = $("#random").val();
    	if(user_mailnum == random ){
    		return true;
    	}else{
    		alert("인증번호가 틀렸습니다");
    		return false;
    	}
    }
    
</script>




</form>
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