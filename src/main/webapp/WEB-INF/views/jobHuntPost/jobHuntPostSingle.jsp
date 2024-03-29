<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<chalKagTags:webCss />

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
							<h1>${jobHuntPostSingle.jobHuntPostTitle}</h1>
							<ul class="details">
								<li>${jobHuntPostSingle.jobHuntPostDate}</li>
								<li><a>Film</a></li>
								<li>By <c:if
										test="${member == null || member != jobHuntPostSingle.memberId}">
										<a href="/memberPage?memberId=${jobHuntPostSingle.memberId}">${jobHuntPostSingle.memberNickname}</a>
									</c:if> <c:if test="${member == jobHuntPostSingle.memberId}">
										<a href="/myPage?memberId=${member}">${jobHuntPostSingle.memberNickname}</a>
									</c:if>
								</li>
							</ul>
						</header>
						<div class="main">
							<div class="featured" style="margin-bottom: 100px;">
									<figure>
										<img src="images/news/img01.jpg">
									</figure>
										<div class="postInfo">
											<div class="postInfoTitle">Role</div>
											<div class="postInfoContents">${jobHuntPostSingle.jobHuntPostRole} </div>
										</div>
										<div  class="postInfo">
											<div class="postInfoTitle">Region</div>
											<div class="postInfoContents">${jobHuntPostSingle.jobHuntPostRegion}</div>
										</div>
										<div  class="postInfo">
											<div class="postInfoTitle">Pay</div>
											<div class="postInfoContents">${jobHuntPostSingle.jobHuntPostPay} </div>
										</div>
										<div  class="postInfo">
											<div class="postInfoTitle">Work Date</div>
											<div class="postInfoContents">${jobHuntPostSingle.jobHuntPostWorkDate}</div>
										</div>
										<div  class="postInfo">
											<div class="postInfoTitle">Concept</div>
											<div class="postInfoContents">${jobHuntPostSingle.jobHuntPostConcept}</div>
										</div>
								</div>

							<!-- 게시글 내용 출력 -->
							<p>${jobHuntPostSingle.jobHuntPostContent}</p>
						</div>
						<footer>
							<div class="col">
								<a href="#" class="love"><i
									class="ion-android-favorite-outline"></i>
									<div>${jobHuntPostList.recommendCnt}</div></a>
							</div>
						</footer>
					</article>

					<div class="line thin"></div>
					<!-- 댓글 출력 -->
					<chalKagTags:webComments />
				</div>
			</div>
		</div>
	</section>

	<!-- Start footer tag로 출력 -->
	<chalKagTags:webFooter />
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
	<script
		src="css/user/scripts/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
	<script src="css/user/scripts/easescroll/jquery.easeScroll.js"></script>
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="css/user/js/demo.js"></script>
	<script>
		$("input").iCheck({
			checkboxClass : 'icheckbox_square-red',
			radioClass : 'iradio_square-red',
			cursor : true
		});
	</script>
	<script src="css/user/js/e-magz.js"></script>
</body>
</html>