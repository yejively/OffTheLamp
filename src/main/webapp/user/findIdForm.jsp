<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/findId.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
</head>
<body>
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>

	<!--center -------------------------------------------------------  -->
<section id="center" class="center_o pt-2 pb-2">
   <section id="join_box">
   
   <h1>아이디찾기</h1> 
   </section>
   <form action="./UserFindIdAction.me" method="post">
   <fieldset id="join_wrap">
   <label>이름</label>
   <br>
   <input id="A" type="text" name="user_name" placeholder="성명">
   <br>
   <p id="hiddenMsgName"></p>
   <label>전화번호</label>
   <br>
   <input id="B" type="text" name="user_phone" placeholder="- 빼고 입력하세요">
   <br>
   <p id="hiddenMsgPhone"></p>
   <input id="button" type="submit" value="아이디찾기" onclick="return check()">
   </fieldset>
   
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