<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
    
    <style type="text/css">
	 .postInfo {
	 	display: flex;
	 	font-style: 'Malgun Gothic';
	 	margin-bottom: 5px;
	 }
    	.postInfoTitle { 	
    	margin-right: 20px; 
    	width: 100px;
    	}
    	
    </style>
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
								<h1>${headHuntPostSingle.headHuntPostTitle}</h1>
								<ul class="details" >
									<li>${headHuntPostSingle.headHuntPostDate}</li>
									<li>
										<a>Film</a>
									</li>
									<li>By 
										<c:if test="${member == null || member != headHuntPostSingle.memberId}">
											<a href="/memberPage?memberId=${headHuntPostSingle.memberId}">${headHuntPostSingle.memberNickname}</a>
										</c:if> 
										
										<c:if test="${member == headHuntPostSingle.memberId}">
											<a href="/myPage?memberId=${headHuntPostSingle.memberId}">${headHuntPostSingle.memberNickname}</a>
										</c:if>
										
									</li>	
								</ul>
							</header>
						<div class="main">
								<!-- 게시글 이미지 출력 추후 추가 -->
								<div class="featured" style="margin-bottom: 100px; display: flex;">
									<div class="owl-carousel owl-theme" id="mainImg">
										<c:forEach var="images" items="${headHuntPostSingleImages}">
									 		<div class="item">
												<figure>
													<img src="images/news/img01.jpg">	
												</figure>
											</div>
										</c:forEach>
									</div>
										<div>
											<div class="postInfo">
												<div class="postInfoTitle">Role</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostRole} </div>
											</div>
											<div  class="postInfo">
												<div class="postInfoTitle">Region</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostRegion}</div>
											</div>
											<div  class="postInfo">
												<div class="postInfoTitle">Pay</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostPay} </div>
											</div>
											<div  class="postInfo">
												<div class="postInfoTitle">Work Date</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostWorkDate}</div>
											</div>
											<div  class="postInfo">
												<div class="postInfoTitle">Concept</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostConcept}</div>
											</div>
										</div>
								</div>
								<!-- 게시글 내용 출력 -->
								<p>${headHuntPostSingle.headHuntPostContent}</p>
							</div>
							<footer>
								<div class="col">
									<a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${headHuntPostList.recommendCnt}</div></a>
								</div>
							</footer>
						</article>
						<div  style="display: flex; justify-content: center;">
							<c:if test="${member != null || member == headHuntPostSingle.memberId}">
								<a class="btn btn-primary" style="margin-right: 10px" href="/updateHeadHuntPost?headHuntPostId=${headHuntPostSingle.headHuntPostId}">Post Update</a>
								<a class="btn btn-primary" href="/deleteHeadHuntPost?headHuntPostId=${headHuntPostSingle.headHuntPostId}">Post Delete</a>
							</c:if>
						</div>
						<div class="line thin"></div>
						<div class="author">
							<figure>
								<img src="images/img01.jpg">
							</figure>
							<div class="details">
								<h3 class="name">${headHuntPostSingle.memberNickname}</h3>
								<%-- <p>${headHuntPostSingle.memberIntroduction}</p> --%>
							</div>
						</div>
						<div class="line thin"></div>
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