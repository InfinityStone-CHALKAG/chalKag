<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- 페이지 이동 및 필터검색 CSS 파일 링크 달기 & Jsp로 작성할때 링크 프로젝트내 링크와 맞추기 -->
<chalKagTags:webCss />
<style>
/* Min Max 스타일 */
input[type='range']::-webkit-slider-thumb {
	background-color: #F73F52;
}
/* 범위 설정 css */
input[type="range"] {
	accent-color: #F73F52;
}
</style>
</head>

<body>
	<!-- Start header tag로 출력 -->
	<chalKagTags:webHeader />
	<!-- End header tag로 출력 -->

<input type="hidden" id="sessionId" value="${member}">

	<!-- 필터 검색 용 메뉴 -->
	<section class="search">
		<div class="container">
			<div class="row">
				<!-- 열 -->
				<div class="col-md-3">
					<aside>
						<!-- 검색 창 필터검색과 연동되어 있기에 필터검색을 처리하는 Js파일로 검색 처리 -->

						<!-- 글 작성 -->
						<c:if test="${member != null }">
							<a class="btn btn-primary btn-block" style="margin-top: 5%"
								href="/writeFreePost"><i class="ion-android-create"
								style="font-size: 15px;"></i> Write</a>
						</c:if>
						<c:if test="${member == null }">
							<a class="btn btn-primary btn-block" style="margin-top: 5%"
								onclick="message()"><i class="ion-android-create"
								style="font-size: 15px;"></i> Write</a>
						</c:if>
						<br>
						<h2 class="aside-title">Search</h2>
						<div class="aside-body">
							<!-- 검색창 옵션(제목, 내용, 작성자, 제목 + 작성자) -->
							<select id="searchField" name="searchField" class="searchOption"
								style="margin-bottom: 5%; padding-left: 10px; border-color: gray; border-radius: 5px; height: 40px; width: 100%;">
								<option value="freePostTitle">title</option>
								<option value="freePostContent">contents</option>
								<option value="memberNickname">writer</option>
								<option value="titleAndContents">title + contents</option>
							</select>
							<div class="form-group">
								<div class="input-group">
									<!-- 검색어 입력 -->
									<input type="text" id="searchInput" name="searchInput"
										class="form-control" placeholder="Type something ...">
									<div class="input-group-btn">
										<button class="btn btn-primary">
											<i class="ion-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</aside>
					<aside>
						<!-- 날짜 필터검색 -->
						<h2 class="aside-title">Filter</h2>
						<div class="aside-body">
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">DATE</div>
							<div class="form-group">
								<!-- 모든 시간 selectAll -->
								<label><input type="radio" name="freePostDate"
									id="Anytime" value="Anytime" checked> Anytime</label>
							</div>
							<div class="form-group">
								<!-- 오늘 하루 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="freePostDate"
									id="Today" value="Today"> Today</label>
							</div>
							<div class="form-group">
								<!-- 지난주 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="freePostDate"
									id="LastWeek" value="Last Week"> Last Week</label>
							</div>
							<div class="form-group">
								<!-- 전 달동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="freePostDate"
									id="LastMonth" value="Last Month"> Last Month</label>
							</div>
		
							<br>
							<button class="btn btn-primary btn-sm" id="filterReset"
								style="font-size: 100%;">
								<i class="ion-ios-refresh-empty" style="font-size: 15px;"></i>
								FILTER RESET
							</button>

						</div>
					</aside>
				</div>
				
				<div class="col-md-9">
					<div class="nav-tabs-group">
						<ul class="nav-tabs-list">
							<li class="active" onclick="sortItems('all')"><a href="#">All</a></li>
							<li><a href="#">Latest</a></li>
							<li><a href="#">Popular</a></li>
							<li><a href="#">Trending</a></li>
							<li><a href="#">Videos</a></li>
						</ul>
						<div class="nav-tabs-right">
							<!-- 오름차순, 내림차순 정렬 -->
							<select class="form-control" id="sortOrder">
								<option value="desc">Descending</option>
								<option value="asc">Ascending</option>
							</select>
						</div>
					</div>

					<div class="search-result">Define style with your exceptional
						models.</div>
			
					<!-- 게시글 전체 갯수가 출력되게 처리 -->
					<div class="row">
						<!-- 게시글 목록 출력 장소 시작 게시판마다 명이 다르다. -->
						<div id="postDatasContainer"
							data-displayreviewdata='${freePostList}'></div>
						<!-- 게시글 목록 출력 장소 종료-->
						<!-- 페이지 이동 버튼 -->
						<!-- 페이징처리 시 1~10 까지 출력 글이 적으면 1~a 만 출력할 수 있게 처리  -->
						<div class="col-md-12 text-center">
							<ul class='pagination' id="paginationContainer">
							</ul>
						</div>
						<!-- 페이징 끝 -->
					</div>
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
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="js/user/freePostPagination.js"></script>
	<script src="js/user/freePostFilterSearch.js"></script>
	
	
	<script src="css/user/js/e-magz.js"></script>
	<script src="css/user/js/demo.js"></script>
	<script src="css/user/scripts/icheck/icheck.min.js"></script>
	<script>
		$("input").iCheck({
			checkboxClass : 'icheckbox_square-red',
			radioClass : 'iradio_square-red',
			cursor : true
		});
	</script>
	<script>
		function message() {
			swal({
				title : "fail",
				text : "로그인 후 이용해주세요.",
				type : "error",
				showCancelButton : false,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "OK",
				closeOnConfirm : true
			}, function() {
				// "OK" 버튼 누르면 실행될 코드
				window.location.href = "/signIn"; // 로그인 페이지로 이동
			});
		}
	</script>




<script>
	$('input[type=radio][name=freePostDate]').change(function() {
		console.log("aaaaaaaaaaaaaaaaaaa")
	});

</script>
</body>

</html>