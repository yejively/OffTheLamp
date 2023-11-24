<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Off The Lamp</title>
</head>
<body>
<!-- 상단 바 고정 -->
<header>
	<jsp:include page="/inc/topBar.jsp"></jsp:include>
</header>
	<section id="center" class="center_home">
		<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
					aria-label="Slide 2" class="" aria-current="true"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
					aria-label="Slide 3"></button>
			</div>


			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="img/CinemaMain.jpeg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-md-block">

					</div>
				</div>
				<div class="carousel-item">
					<img src="img/CinemaMain2.jpeg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-md-block">

					</div>
				</div>
				<div class="carousel-item">
					<img src="img/CinemaMain3.jpeg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-md-block">

					</div>
				</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden"></span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden"></span>
			</button>
		</div>
	</section>



	<section id="choice" class="pt-4 pb-5">
		<div class="container">
			<h2 class="mb-0">현재 상영 영화</h2><br>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<div class="trend_2i row">

						<div class="col-md-4">
							<div class="trend_2im clearfix position-relative">
								<div class="trend_2im1 clearfix">
									<div class="grid">
										<figure class="effect-jazz mb-0">
											<a href="#"><img src="img/오펜하이머3.jpg" class="w-100" alt="img25"></a>
										</figure>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="trend_2im clearfix position-relative">
								<div class="trend_2im1 clearfix">
									<div class="grid">
										<figure class="effect-jazz mb-0">
											<a href="#"><img src="img/헤어질결심3.jpg" class="w-100" alt="img25"></a>
										</figure>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="trend_2im clearfix position-relative">
								<div class="trend_2im1 clearfix">
									<div class="grid">
										<figure class="effect-jazz mb-0">
											<a href="#"><img src="img/엘리멘탈3.jpg" class="w-100" alt="img25"></a>
										</figure>
									</div>
								</div>
							</div>
						</div>

						<div class="carousel-item">
							<div class="trend_2i row">

								<div class="col-md-4">
									<div class="trend_2im clearfix position-relative">
										<div class="trend_2im1 clearfix">
											<div class="grid">
												<figure class="effect-jazz mb-0">
													<a href="#"><img src="img/15.jpg" class="w-100" alt="img25"></a>
												</figure>
											</div>
										</div>
										<div class="trend_2im2 clearfix  position-absolute w-100 top-0">
											<h5><a class="col_red" href="#">Porta</a></h5>
											<span class="col_red">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
											</span>
											<p class="mb-0">4 Views</p>
										</div>
									</div>

								</div>
								<div class="col-md-4">
									<div class="trend_2im clearfix position-relative">
										<div class="trend_2im1 clearfix">
											<div class="grid">
												<figure class="effect-jazz mb-0">
													<a href="#"><img src="img/16.jpg" class="w-100" alt="img25"></a>
												</figure>
											</div>
										</div>
										<div class="trend_2im2 clearfix  position-absolute w-100 top-0">
											<h5><a class="col_red" href="#">Dapibus</a></h5>
											<span class="col_red">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
											</span>
											<p class="mb-0">6 Views</p>
										</div>
									</div>

								</div>
								<div class="col-md-4">
									<div class="trend_2im clearfix position-relative">
										<div class="trend_2im1 clearfix">
											<div class="grid">
												<figure class="effect-jazz mb-0">
													<a href="#"><img src="img/17.jpg" class="w-100" alt="img25"></a>
												</figure>
											</div>
										</div>
										<div class="trend_2im2 clearfix  position-absolute w-100 top-0">
											<h5><a class="col_red" href="#">Nulla</a></h5>
											<span class="col_red">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
											</span>
											<p class="mb-0">5 Views</p>
										</div>
									</div>


								</div>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
	</section>

<!-- 하단바 고정  -->
<footer>
	<jsp:include page="/inc/bottomBar.jsp"></jsp:include>
</footer>
<!-- 하단바 고정  -->

	<script>
		window.onscroll = function () {myFunction()};

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