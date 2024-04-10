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
								href="/writeHeadHuntPost"><i class="ion-android-create"
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
								<option value="headHuntPostTitle">title</option>
								<option value="headHuntPostContent">contents</option>
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
								<label><input type="radio" name="headHuntPostDate"
									id="Anytime" value="Anytime" checked> Anytime</label>
							</div>
							<div class="form-group">
								<!-- 오늘 하루 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="headHuntPostDate"
									id="Today" value="Today"> Today</label>
							</div>
							<div class="form-group">
								<!-- 지난주 동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="headHuntPostDate"
									id="LastWeek" value="Last Week"> Last Week</label>
							</div>
							<div class="form-group">
								<!-- 전 달동안 작성한 글들 selectAll_today -->
								<label><input type="radio" name="headHuntPostDate"
									id="LastMonth" value="Last Month"> Last Month</label>
							</div>
							<br>

							<!-- 직업 필터 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">ROLE</div>
							<div class="form-group">
								<select style="width: 100%; height: 40px;" id="role"
									name="headHuntPostRole">
									<option value="" disabled selected>Select</option>
									<option value="Model">Model</option>
									<option value="Photographer">Photographer</option>
								</select>
							</div>
							<br>

							<!-- 지역 필터 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">REGION</div>
							<div class="form-group">
								<select style="width: 100%; height: 40px;" id="region"
									name="headHuntPostRegion">
									<option value="" disabled selected>Select</option>
									<option value="SEOUL">SEOUL</option>
									<option value="GYEONGGI">GYEONGGI</option>
									<option value="GANGWON">GANGWON</option>
									<option value="CHUNGCHEONG">CHUNGCHEONG</option>
									<option value="JEOLLA">JEOLLA</option>
									<option value="GYEONGSANG">GYEONGSANG</option>
									<option value="JEJU">JEJU</option>
								</select>
							</div>
							<br>

							<!-- Pay 필터 -->
							<div class="group-title" style="font-weight: bold; margin-bottom: 2%;">PAY</div>
<div class="form-group">
    <label for="minPrice">Min</label>
    <input type="range" id="minPay" name="minPay" min="0" max="1000000" value="0" oninput="updateTextInput('minPay', 'minPayText')">
    <input type="text" id="minPayText" readonly style="border:0; color:#f6931f; font-weight:bold;">
	<br>
    <label for="maxPrice">Max</label>
    <input type="range" id="maxPay" name="maxPay" min="0" max="1000000" value="0" oninput="updateTextInput('maxPay', 'maxPayText')">
    <input type="text" id="maxPayText" readonly style="border:0; color:#f6931f; font-weight:bold;">
</div>
							<br>

							<!--  작업 날짜 필터 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">WORK DATE</div>
							<div class="form-group">
								<label for="workdate" style="margin-bottom: 2%;">Start</label> <input
									style="width: 100%; height: 40px; margin-bottom: 2%;"
									type="date" id="startDate" name="startWorkDate" value=""> <label
									for="workdate" style="margin-bottom: 2%;">End</label> <input
									style="width: 100%; height: 40px; margin-bottom: 2%;"
									type="date" id="endDate" name="endWorkDate">
							</div>
							<br>

							<!-- 촬영 컨셉 필터 -->
							<div class="group-title"
								style="font-weight: bold; margin-bottom: 2%;">WORK CONCEPT
							</div>
							<div class="form-group">
								<select id="concept" name="headHuntPostConcept"
									style="width: 100%; height: 40px;">
									<option value="" disabled selected>Select</option>
									<option value="snap">Snap</option>
									<option value="pictorial">Pictorial</option>
									<option value="studio">Studio</option>
									<option value="outdoor">Outdoor</option>
									<option value="etc">Etc</option>
								</select>
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
							data-displayreviewdata='${headHuntPostList}'></div>
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
	<script src="js/user/headHuntPostPagination.js"></script>
	<script src="js/user/headHuntPostFilterSearch.js"></script>
	
	
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
    function updateTextInput(rangeId, inputId) {
        var range = document.getElementById(rangeId);
        var input = document.getElementById(inputId);
        var step = 1000; // 1000 단위로 움직이도록 설정
        var newValue = Math.floor(range.value / step) * step;
        range.value = newValue;
        input.value = newValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 콤마 추가

        // MIN 값이 MAX값을 넘어가지 않도록 확인
        var minPay = document.getElementById("minPay").value;
        var maxPay = document.getElementById("maxPay").value;
        if (parseInt(minPay) > parseInt(maxPay)) {
            document.getElementById(rangeId).value = maxPay;
            document.getElementById(inputId).value = maxPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }

        // MAX 값이 MIN 값보다 작지 않도록 확인
        if (parseInt(maxPay) < parseInt(minPay)) {
            document.getElementById("maxPay").value = minPay;
            document.getElementById("maxPayText").value = minPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    }
</script>


<script>
    // Get references to the input fields
    var startDateInput = document.getElementById('startDate');
    var endDateInput = document.getElementById('endDate');

    // Add event listener to the endDate input field
    endDateInput.addEventListener('change', function() {
        // Parse the dates
        var startDate = new Date(startDateInput.value);
        var endDate = new Date(endDateInput.value);

        // If endDate is before startDate, reset the value of endDate
        if (endDate < startDate) {
            endDateInput.value = '';
        }
    });

    // Add event listener to the startDate input field
    startDateInput.addEventListener('change', function() {
        // Parse the dates
        var startDate = new Date(startDateInput.value);
        var endDate = new Date(endDateInput.value);

        // If startDate is after endDate, reset the value of startDate
        if (startDate > endDate) {
            startDateInput.value = '';
        }
    });
</script>


<script>
	$('input[type=radio][name=headHuntPostDate]').change(function() {
		console.log("aaaaaaaaaaaaaaaaaaa")
	});

</script>
</body>

</html>