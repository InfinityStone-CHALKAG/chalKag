<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<!DOCTYPE html>
<html>
<head>

<chalKagTags:webCss />

<style>
#writerForm {
	width: 70%;
}

.selectTags {
	border-color: rgb(179, 179, 179);
	padding-left: 8px;;
	border-radius: 5px;
	width: 18.4%;
	margin-right: 2%;
}

#jobHuntPostWorkDate {
	border-radius: 5px;
	border: solid 1px rgb(179, 179, 179);
}

#preview {
	display: flex;
	overflow: hidden;
	width: 100%; /* 슬라이드 컨테이너의 너비를 설정 */
}

.image-container {
	display: flex;
	transition: transform 0.5s ease;
}

img {
	transform: translate(50, 50);
	max-width: 400px;
	max-height: 400px;
	margin-right: 10px;
	margin-left: 10px;
	object-fit: cover;
	object-position: center;
}

.imgSlidebtn {
	border: 0;
	height: 150px;
	color: white;
	background-color: rgb(245, 63, 82);
}

#jobHuntPostTitle {
	margin-bottom: 1%;
}

#jobHuntPostContent {
	height: 300px;
}

#inputCol {
	margin-bottom: 2%;
}

#customButton {
	width: 100%;
	height: 50px;
	background-color: rgb(255, 255, 255);
	border: solid 1px rgb(245, 63, 82);
	color: rgb(245, 63, 82);;
	margin: 8px 0;
	border-radius: 5px;
	font-weight: bold;
}

#customButton:hover {
	background-color: rgb(245, 63, 82);
	border: none;
	color: rgb(255, 255, 255);
}

.img-wrapper {
	position: relative;
	width: 400px;
	height: 400px;
	display: inline-block; /* 이미지를 옆으로 나열 */
}

.delete-btn, .move-up-btn, .move-down-btn {
	position: absolute;
	cursor: pointer;
}

.delete-btn {
	top: 0;
	right: 9%;
	z-index: 50; /* 확실히 이미지 위에 오도록 설정 */
	background-color: transparent;
	border: 0;
	color: white;
	-webkit-text-stroke-width: 1px;
	-webkit-text-stroke-color: rgb(245, 63, 82);
	text-shadow: 1px 0px rgb(245, 63, 82);
}

.move-up-btn, .move-down-btn {
	top: 50%;
	transform: translateY(-50%);
	border: 0;
	color: white;
	-webkit-text-stroke-width: 1px;
	-webkit-text-stroke-color: rgb(245, 63, 82);
	background-color: transparent;
}

.move-up-btn {
	left: 9%; /* 이미지 왼쪽으로 이동 버튼 위치 조정 */
}

.move-down-btn {
	right: 9%; /* 이미지 오른쪽으로 이동 버튼 위치 조정 */
}
</style>
</head>
<body>
	<!-- Start header tag로 출력 -->
	<chalKagTags:webHeader />
	<!-- End header tag로 출력 -->

	<section class="single">
		<div class="container">
			<div class="box-wrapper" id="writerForm">
				<div class="box box-border">
					<div class="box-body">
						<form action="/writeJobHuntPost" method="post"
							onsubmit="return validateForm(event)"
							enctype="multipart/form-data">
							<div class="form-group"
								style="display: flex; justify-content: center;">
								<input type="text" class="form-control rounded"
									id="jobHuntPostTitle" name="jobHuntPostTitle"
									placeholder="Title" />
							</div>
							<div class="form-group" style="display: flex;">

								<input type="text" id="jobHuntPostPay" name="jobHuntPostPay"
									class="form-control rounded"
									style="width: 18.4%; margin-right: 2%;"
									oninput="trimInput(this); validatePay(this);" placeholder="Pay" />
								<select class="selectTags" id="jobHuntPostRole"
									name="jobHuntPostRole">
									<option value="" disabled selected>Role</option>
									<option value="모델">모델</option>
									<option value="사진작가">사진작가</option>
								</select> <input class="selectTags" type="date" id="jobHuntPostWorkDate"
									name="jobHuntPostWorkDate" style="padding-left: 8px;">

								<select class="selectTags" id="jobHuntPostConcept"
									name="jobHuntPostConcept">
									<option value="" disabled selected>WorkConcept</option>
									<option value="snap">스냅사진</option>
									<option value="pictorial">화보</option>
									<option value="studio">내부</option>
									<option value="outdoor">외부</option>
									<option value="etc">기타</option>
								</select> 
								
								<select style="width: 18.4%" id="jobHuntPostRegion"
									name="jobHuntPostRegion">
									<option value="" disabled selected>선택</option>
									<option value="SEOUL">SEOUL</option>
									<option value="GYEONGGI">GYEONGGI</option>
									<option value="GANGWON">GANGWON</option>
									<option value="CHUNGCHEONG">CHUNGCHEONG</option>
									<option value="JEOLLA">JEOLLA</option>
									<option value="GYEONGSANG">GYEONGSANG</option>
									<option value="JEJU">JEJU</option>
								</select>
							</div>

							<div class="form-group">
								<input type="file" name="file" id="fileInput" accept="image/*"
									style="display: none;" multiple> <input type="button"
									id="customButton" value="Upload">
							</div>
							<div id="preview" style="display: flex; justify-content: center;">
								<button class="imgSlidebtn" id="slideLeft">◀</button>
								<div style="overflow: hidden; flex-grow: 1;">
									<div class="image-container"></div>
								</div>
								<button class="imgSlidebtn" id="slideRight">▶</button>
							</div>

							<div class="form-group"
								style="display: flex; justify-content: center;">
								<textarea class="form-control rounded" id="jobHuntPostContent"
									name="jobHuntPostContent"></textarea>
							</div>
							<button class="btn btn-primary btn-block">Composite</button>
						</form>
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
	<script src="css/user/js/demo.js"></script>
	<script src="js/user/previewImg.js"></script>
	<script>
		$("input").iCheck({
			checkboxClass : 'icheckbox_square-red',
			radioClass : 'iradio_square-red',
			cursor : true
		});
	</script>
	<script src="css/user/js/e-magz.js"></script>
	<script>
		// 공백 및 null 입력 막는 함수
		function trimInput(element) {
			element.value = element.value.trim();
		}

		function validatePay(input) {
			const priceRegex = /^[0-9]*$/; // 숫자만 입력하는 정규표현식
			if (!priceRegex.test(input.value)) {
				input.value = input.value.replace(/[^0-9]/g, '');
				swal("fail", "가격은 숫자만 입력해주세요.", "error", {
					button : "OK",
				});
			}
		}

		function validateForm(event) {
			var title = document.getElementById('jobHuntPostTitle').value;
			var pay = document.getElementById('jobHuntPostPay').value;
			var role = document.getElementById('jobHuntPostRole').value;
			var workDate = document.getElementById('jobHuntPostWorkDate').value;
			var concept = document.getElementById('jobHuntPostConcept').value;
			var content = document.getElementById('jobHuntPostContent').value;
			var imageInput = document.getElementById('fileInput');

			if (!title.trim() || !pay.trim() || !role || !workDate || !concept
					|| !content.trim()) {
				swal("fail", "모든 필드를 채워주세요.", "error", {
					button : "OK",
				});
				event.preventDefault(); // 폼 제출을 막는다.
				return false; // 폼 제출을 막는다.
			} else if (!imageInput.files.length) { // 이미지가 업로드되었는지 확인
				swal("fail", "이미지를 업로드해주세요.", "error", {
					button : "OK",
				});
				event.preventDefault(); // 폼 제출을 막는다.
				return false; // 폼 제출을 막는다.
			}

			return true; // 모든 검사를 통과하면 폼 제출을 진행.
		}
	</script>
</body>
</html>