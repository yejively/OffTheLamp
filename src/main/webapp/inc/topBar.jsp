<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap"	rel="stylesheet">
<script src="./js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<section id="top">
		<div class="container">
			<div class="row top_1">
				<div class="col-md-3">
					<div class="top_1l pt-1">
						<h3 class="mb-0">
							<a class="text-white" href="./Main.me">
							<i class="fa fa-video-camera col_red me-1"></i>Off The Lamp</a>
						</h3>
					</div>
				</div>
				<div class="col-md-5">
					<div class="top_1m"></div>
				</div>
				<div class="col-md-4">
					<div class="top_1r text-end">
						<ul class="social-network social-circle mb-0">
							<c:choose>
								<c:when test="${ sessionScope.user_id eq 'admin'}">
									<li><a href="./UserLogoutAction.me">로그아웃</a></li>
									<li><a href="./managerList.or">관리자페이지</a></li>
								</c:when>
								<c:when test="${empty sessionScope.user_id }">
									<li><a href="./UserLogin.me">로그인</a></li>
									<li><a href="./UserJoin.me">회원가입</a></li>
								</c:when>
								<c:when test="${!empty sessionScope.user_id }">
									<li><a href="./UserLogoutAction.me">로그아웃</a></li>
									<li><a href="./MyPageMain.or">마이페이지</a></li>
								</c:when>
							</c:choose>						
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="header">
		<nav class="navbar navbar-expand-md navbar-light" id="navbar_sticky">
			<div class="container">
				<a class="navbar-brand text-white fw-bold" href="./main.me"><i
					class="fa fa-video-camera col_red me-1"></i>Off The Lamp</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mb-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="./orderMain.or">예매안내</a></li>
						<li class="nav-item"><a class="nav-link"
							href="./eventMain.bo">이벤트</a></li>
						<li class="nav-item"><a class="nav-link"
							href="./introduceMain.bo">소개게시판</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								고객문의 </a>
							<ul class="dropdown-menu drop_1" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="./noticeMain.bo">공지사항</a></li>
								<li><a class="dropdown-item" href="./faqMain.bo">자주묻는질문</a></li>
								<li><a class="dropdown-item border-0 "href="./rentMain.bo">대관문의</a></li>
							</ul><li>
				</div>
			</div>
		</nav>
	</section>
	
</body>
</html>