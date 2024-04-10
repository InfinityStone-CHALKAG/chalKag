<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
    
    <style type="text/css">
	/* 글 상세보기 글씨체 설정 css */
	 .postInfo {
	 	display: flex;
	 	font-style: 'Malgun Gothic';
	 	margin-bottom: 5px;
		font-size:20px;
		margin-left: 26%;
	 }
	 /* 글 상세보기 제목 css */
    	.postInfoTitle { 	
    	margin-right: 100px; 
    	width: 100px;
		margin-left: 4%;
    	}
	/* 캐러셀 효과 */
		.owl-carousel {
            display: block;
            width: 100%;
            margin: 0 auto;
        }
		/* 캐러셀 이미지 css */
        .owl-item img {
            display: block;
            width: 750px; /* 이미지의 너비를 750px로 강제 설정 */
            height: 575px; /* 이미지의 높이를 575px로 강제 설정 */
            object-fit: cover; /* 이미지의 비율을 유지하면서 요소에 맞게 잘리지 않도록 설정 */
        }
    	/* 원본 이미지 출력 효과 css */
		#fullScreenImageContainer {
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background-color: rgba(0, 0, 0, 0.9);
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    z-index: 1000; /* 확실히 다른 요소 위에 표시되도록 z-index 지정 */
		}
		
		/* 이미지 스타일 */
		#fullScreenImageContainer img {
		    max-width: 90%; /* 화면 너비의 90%를 넘지 않도록 */
		    max-height: 90%; /* 화면 높이의 90%를 넘지 않도록 */
		}    	
    	/* 글 내용 css */
    	.main pre {
    white-space: pre-wrap; /* CSS3의 white-space 속성을 사용해 텍스트가 영역을 벗어나지 않도록 함 */
    word-wrap: break-word; /* 긴 단어도 줄바꿈될 수 있도록 함 */
    overflow-x: hidden; /* 가로 스크롤바를 숨김 */
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
					<!-- 사이드 배너 태그 파일 호출 -->
					<chalKagTags:webSider />

					<div class="col-md-8">
						<article class="article main-article">
							<header>
								<!-- 글 제목 -->
								<h3>${headHuntPostSingle.headHuntPostTitle}</h3>
								<ul class="details" >
									<!-- 글 작성일 -->
									<li>${headHuntPostSingle.headHuntPostDate}</li>
									<li>
										<!-- 글 카테고리 -->
										<a href="/headHuntPostList">Head Hunt Post List</a>
									</li>
									<li>By 
										<!-- 작성자 본인일 경우 myPage 아닐경우 작성자의 memberPage 로 이동 -->
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
							<div class="featured">
								<!-- 이미지 목록 캐러셀로 출력 -->
							<div class="owl-carousel">
								<c:forEach var="postImgList" items="${postImgList}">
										<figure>
											<img src="/postImg/${postImgList.postImgName}">
										</figure>
								</c:forEach>
								</div>
							</div>
							
							<!-- 클릭한 이미지를 보여줄 컨테이너 -->
							<div id="fullScreenImageContainer" style="display:none;">
							    <img id="fullScreenImage">
							</div>
							<!-- 작성한 구인 내용 출력 -->
								<div class="featured" style="margin-bottom: 3%; margin-top: 2%; display: block; justify-content: center;">
										<div>
											<div class="postInfo">
												<i class="ion-ios-body"
													style="font-size:20px;"></i><div class="postInfoTitle">Role</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostRole} </div>
											</div>
											<div  class="postInfo">
												<i class="ion-android-home"
													style="font-size:20px;"></i><div class="postInfoTitle">Region</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostRegion}</div>
											</div>
											<div  class="postInfo">
												<i class="ion-cash"
													style="font-size:20px;"></i><div class="postInfoTitle" style="margin-left:3.1%;">Pay</div>
												<div class="postInfoContents" id="headHuntPostPay">${headHuntPostSingle.headHuntPostPay} ₩</div>
											</div>
											<div  class="postInfo">
												<i class="ion-android-calendar"
													style="font-size:20px;"></i><div class="postInfoTitle">Work Date</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostWorkDate}</div>
											</div>
											<div  class="postInfo">
												<i class="ion-ios-camera"
													style="font-size:20px;"></i><div class="postInfoTitle">Concept</div>
												<div class="postInfoContents">${headHuntPostSingle.headHuntPostConcept}</div>
											</div>
										</div>
								</div>
								<!-- 게시글 내용 출력 -->
								<pre style="background: none; border: none; font-size: 15px; font-style: 'Malgun Gothic'; line-height: 210%;">${headHuntPostSingle.headHuntPostContent}</pre>
							</div>
							<footer>
								<div class="col" style="width:54.4%;">
									<a href="#" class="love" style="margin-top:0%"><i class="ion-android-favorite-outline"></i><div>${headHuntPostList.recommendCnt}</div></a>
								</div>
							</footer>
						</article>
						<div  style="display: flex; justify-content: center;">
							<c:if test="${member != null && member == headHuntPostSingle.memberId}">
								<a class="btn btn-primary" style="margin-right: 10px" href="/updateHeadHuntPost?headHuntPostId=${headHuntPostSingle.headHuntPostId}">Post Update</a>
								<a class="btn btn-primary" href="/deleteHeadHuntPost?headHuntPostId=${headHuntPostSingle.headHuntPostId}">Post Delete</a>
							</c:if>
						</div>
						<div class="line">
							<div>Author</div>
						</div>
						<div class="author">
							<figure>
								<img src="profileImg/${headHuntPostSingle.profileImgName}"style="width: 100%; height: 100%; object-fit: cover;">
							</figure>
							<div class="details">
								<!-- 작성자 정보 출력 -->
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
		<div postImgList="${postImgList}" ></div>

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
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="css/user/js/demo.js"></script>
	<script src="css/user/js/e-magz.js"></script>
	<script>
		
		  
	document.addEventListener('DOMContentLoaded', function() {
    // div 요소를 가져옵니다.
    const postPayElement = document.getElementById('headHuntPostPay');

    // 요소의 텍스트 내용을 가져옵니다.
    const postPayText = postPayElement.textContent;

    // 쉼표로 숫자를 구분하여 출력하기 위한 함수를 정의합니다.
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // 텍스트 내용을 숫자로 파싱하고 쉼표로 구분하여 다시 설정합니다.
    postPayElement.textContent = numberWithCommas(parseFloat(postPayText));
});


	$(document).ready(function(){
	    // Owl Carousel의 이미지 개수 확인
	    var numPhotos = $(".owl-carousel").find("img").length;
	    var loopValue = (numPhotos > 1) ? true : false;
	    console.log(numPhotos); // 이미지 개수를 로그에 출력하여 확인

	    // Owl Carousel 초기화
	    $(".owl-carousel").owlCarousel({
	        items: 1,
	        loop: loopValue,
	        autoplay: true,
	        autoplayTimeout: 3000,
	        autoplayHoverPause: true,
	        margin: 10
	    });

	    // 이미지 클릭 이벤트를 위한 이벤트 위임
	    $(".owl-carousel").on("click", ".owl-item", function(){
	        var src = $(this).find("img").attr("src"); // 클릭한 div 내부의 이미지 src를 가져옵니다.
	        $("#fullScreenImage").attr("src", src); // 전체 화면에 표시할 이미지 src 설정
	        $("#fullScreenImageContainer").fadeIn(); // 이미지 컨테이너를 페이드인으로 보여줍니다.
	    });

	    // 전체 화면 이미지 컨테이너 클릭 시 닫기 기능
	    $("#fullScreenImageContainer").click(function(){
	        $(this).fadeOut(); // 컨테이너를 페이드아웃으로 숨깁니다.
	    });
	});

	</script>
	</body>
</html>