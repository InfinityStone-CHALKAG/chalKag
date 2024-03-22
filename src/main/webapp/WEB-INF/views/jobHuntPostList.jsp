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
[type="radio"], span {
	vertical-align: middle;
}

[type="radio"] {
	appearance: none;
	border: max(2px, 0.1em) solid gray;
	border-radius: 50%;
	width: 1.25em;
	height: 1.25em;
	transition: border 0.5s ease-in-out;
}

[type="radio"]:checked {
	border: 0.4em solid #F73F52;
}

[type="radio"]:focus-visible {
	outline-offset: max(2px, 0.1em);
	outline: max(2px, 0.1em) dotted #F73F52;
}

[type="radio"]:hover {
	box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
	cursor: pointer;
}

[type="checkbox"] {
	accent-color: red;
}

[type="range"] {
	accent-color: red;
}

div .inner p {
	margin-left: 5%;
}
</style>
</head>

<body>
	<!-- Start header tag로 출력 -->
	<chalKagTags:webHeader />
	<!-- End header tag로 출력 -->

	<!-- 필터 검색 용 메뉴 -->
	<section class="search">
		<div class="container">
			<div class="row">
				<!-- 열 -->
				<div class="col-md-3">
					<aside>
						<!-- 검색 창 필터검색과 연동되어 있기에 필터검색을 처리하는 Js파일로 검색 처리 -->
						<h2 class="aside-title">Search</h2>
						<div class="aside-body">
							<!-- 검색창 옵션(제목, 내용, 작성자, 제목 + 작성자) -->
							<select id="searchField" name="searchField" class="searchOption"
								style="margin-bottom: 5%; padding-left: 10px; border-color: gray; border-radius: 5px; height: 40px; width: 100%;">
								<option value="title">title</option>
								<option value="contents">contents</option>
								<option value="writer">writer</option>
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
								style="font-weight: bold; margin-bottom: 2%;">Date</div>
							<div class="form-group">
								<!-- 모든 시간 selectAll -->
								<label><input type="radio" name="date" checked>&nbsp;
									Anytime</label>
							</div>
							<div class="form-group">
								<!-- 오늘 하루 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="date">&nbsp;
									Today</label>
							</div>
							<div class="form-group">
								<!-- 지난주 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="date">&nbsp;
									Last Week</label>
							</div>
							<div class="form-group">
								<!-- 전 달동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="date">&nbsp;
									Last Month</label>
							</div>

							<!-- 구직 정보 필터 검색 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">Head Hunt</div>
							<!-- 모든 글 -->
							<div class="form-group">
								<label><input type="checkbox" id="jobHunt"
									name="jobHunt" checked> &nbsp; All Job Hunt</label>
							</div>

							<!-- 직업 필터 -->
							<div class="form-group">
								<label for="role">직업 :</label> <select
									style="width: 100%; height: 40px;" id="role" name="role">
									<option value="" disabled selected>선택</option>
									<option value="모델">모델</option>
									<option value="사진작가">사진작가</option>
								</select>
							</div>

							<!-- 지역 필터 -->
							<div class="form-group">
								<label for="region">작업 지역:</label> <select
									style="width: 100%; height: 40px;" id="region" name="region">
									<option value="" disabled selected>선택</option>
									<option value="SEOUL">서울</option>
									<option value="GYEONGGI">경기</option>
									<option value="GANGWON">강원</option>
									<option value="CHUNGCHEONG">충천</option>
									<option value="JEOLLA">전라</option>
									<option value="GYEONGSANG">경상</option>
									<option value="JEJU">제주</option>
								</select>
							</div>

							<!-- Pay 필터 -->
							<div class="form-group">
								<label for="minPrice" style="margin-bottom: 2%;">최소 Pay:</label>
								<input type="range" style="margin-bottom: 2%;" id="minPay"
									name="minPay" min="0" max="1000000" value="1"> <label
									for="maxPrice" style="margin-bottom: 2%;">최대 Pay:</label> <input
									type="range" style="margin-bottom: 2%;" id="maxPay"
									name="maxPay" min="0" max="1000000" value="1">
							</div>

							<!--  작업 날짜 필터 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">Work Date</div>
							<div class="form-group">
								<label for="workdate" style="margin-bottom: 2%;">시작일</label> <input
									style="width: 100%; height: 40px; margin-bottom: 2%;"
									type="date" id="startDate" name="startDate"> <label
									for="workdate" style="margin-bottom: 2%;">종료일</label> <input
									style="width: 100%; height: 40px; margin-bottom: 2%;"
									type="date" id="endDate" name="endDate">
							</div>

							<!-- 촬영 컨셉 필터 -->
							<div class="form-group">
								<label for="concept">촬영 컨셉:</label> <select id="concept"
									name="concept" style="width: 100%; height: 40px;">
									<option value="" disabled selected>선택</option>
									<option value="snap">스냅사진</option>
									<option value="pictorial">화보</option>
									<option value="studio">내부</option>
									<option value="outdoor">외부</option>
									<option value="etc">기타</option>
								</select>
							</div>
						</div>
					</aside>
				</div>

				<div class="col-md-9"
					style="text-align: right; margin-bottom: 10px;">
					<!-- 글 작성 -->
					<c:if test="${member != null }">
						<a class="btn btn-primary btn-sm" href="/writeJobHuntPost">Writing</a>
					</c:if>
					<c:if test="${member == null }">
						<a class="btn btn-primary btn-sm" onclick="message()">Writing</a>
					</c:if>
				</div>
				<div class="col-md-9">
					<div class="nav-tabs-group">
						<ul class="nav-tabs-list">
							<li onclick="sortItems('all')">All</li>
							<li onclick="sortItems('recommendCnt')">Popular</li>
							<li onclick="sortItems('jobHuntPostViewCnt')">Trending</li>
						</ul>
						<div class="nav-tabs-right">
							<!-- 오름차순, 내림차순 정렬 -->
							<select class="form-control" id="sortOrder">
								<option value="desc">Descending</option>
								<option value="asc">Ascending</option>
							</select>
						</div>
					</div>

					<!-- 게시글 전체 갯수가 출력되게 처리 -->
					<div class="row">
						<!-- 게시글 목록 출력 장소 시작 게시판마다 명이 다르다. -->
						<div id="postDatasContainer"
							displayReviewData='${jobHuntPostList}'></div>
						<!-- 게시글 목록 출력 장소 종료-->

						<!-- 페이지 이동 버튼 -->
						<!-- 페이징처리 시 1~10 까지 출력 글이 적으면 1~a 만 출력할 수 있게 처리  -->
						<div class="col-md-12 text-center" id="paginationContainer"></div>
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
	<script src="css/user/scripts/easescroll/jquery.easeScroll.js"></script>
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="js/user/jobHuntPostPagination.js"></script>
	<script src="js/user/jobHuntPostFilterSearch.js"></script>
	<script src="css/user/js/e-magz.js"></script>
	<script src="css/user/js/demo.js"></script>
	<script>
		$("input").iCheck({
			checkboxClass : 'icheckbox_square-red',
			radioClass : 'iradio_square-red',
			cursor : true
		});
	</script>
	<script>
		function message() {
			swal("fail", "로그인 후 이용해주세요.", "error", {
				button : "OK",
			});
		}
	</script>
</body>
</html>