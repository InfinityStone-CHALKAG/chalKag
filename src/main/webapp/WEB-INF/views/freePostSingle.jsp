<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
</head>
	<body>
		<!-- Start header tag로 출력 -->
		<chalKagTags:webHeader />
		<!-- End header tag로 출력 -->

		<section class="single">
			<div class="container">
				<div class="row">

					<chalKagTags:webSider />

					<div class="col-md-8">
						<article class="article main-article">
							<header>
								<h1>${freePostSingle.freePostTitle}</h1>
								<ul class="details">
									<li>${freePostSingle.freePostDate}</li>
									<li><a>Film</a></li>
									<li>By 
										<c:if test="${member == null || member != freePostSingle.memberId}">
											<a href="/memberPage/memberId=${freePostSingle.memberId}">${freePostSingle.memberNickname}</a>
										</c:if>
										<c:if test="${member == freePostSingle.memberId}">
											<a href="/myPage/memberId=${member}">${freePostSingle.memberNickname}</a>
										</c:if>
								</li>
								</ul>
							</header>
							<div class="main">
							
								<!-- 게시글 이미지 출력 추후 추가 -->
								<div class="featured">
									<figure>
										<img src="images/news/img01.jpg">
										<figcaption>Image by pexels.com</figcaption>
									</figure>
								</div>

								<!-- 게시글 내용 출력 -->
								<p>${freePostSingle.freePostContent}</p>
							</div>
							<footer>
								<div class="col">
									<a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${freePostList.recommendCnt}</div></a>
								</div>
							</footer>
						</article>
						<div class="line"></div>
						<!-- 댓글 출력 -->
						<chalKagTags:webComments />
					</div>
				</div>
			</div>
		</section>

		<!-- Start footer tag로 출력 -->
		<chalKagTags:webFooter/>
		<!-- End Footer -->
		<!-- JS -->
		<!-- 페이지 이동 및 필터검색 Js 파일 링크 달기 & Jsp로 작성할때 링크 프로젝트내 링크와 맞추기 -->
	<script src="css/user/js/jquery.js"></script>
	<script src="css/user/js/jquery.migrate.js"></script>
	<script src="css/user/scripts/bootstrap/bootstrap.min.js"></script>
	<script>
		var $target_end = $(".best-of-the-week");
	</script>
	<script src="css/user/scripts/jquery-number/jquery.number.min.js"></script>
	<script src="css/user/scripts/owlcarousel/dist/owl.carousel.min.js"></script>
	<script src="css/user/scripts/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
	<script src="css/user/scripts/easescroll/jquery.easeScroll.js"></script>
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="css/user/js/demo.js"></script>
		<script>$("input").iCheck({
      checkboxClass: 'icheckbox_square-red',
      radioClass: 'iradio_square-red',
      cursor: true
		});</script>
	<script src="css/user/js/e-magz.js"></script>
	</body>
</html>