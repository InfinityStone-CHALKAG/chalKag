<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<!DOCTYPE html>
<html>
<head>

<chalKagTags:webCss />

<style>
#jobHuntPostWorkDate {
	border-radius: 5px;
	border: solid 1px rgb(179, 179, 179);
}

#jobHuntPostContent {
	height: 300px;
}

</style>
<link rel="stylesheet" href="/css/user/css/postImg.css">
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
								style="display: flex; justify-content: center; margin-bottom: 4%;">
								<input type="text" class="form-control rounded"
									id="jobHuntPostTitle" name="jobHuntPostTitle"
									placeholder="Title" />
							</div>
							
							<div class="form-group" style="display: flex; margin-bottom: 4%;">
								<select class="selectTags" id="jobHuntPostRole" name="jobHuntPostRole"
								style="width: 32%; height: 43px; margin-right: 2%;">
									<option value="" disabled selected>Role</option>
									<option value="모델">모델</option>
									<option value="사진작가">사진작가</option>
								</select>

								<select class="selectTags" id="jobHuntPostConcept" 
								style="width: 32%; height: 43px; margin-right: 2%;"
								name="jobHuntPostConcept" >
									<option value="" disabled selected>WorkConcept</option>
									<option value="snap">스냅사진</option>
									<option value="pictorial">화보</option>
									<option value="studio">내부</option>
									<option value="outdoor">외부</option>
									<option value="etc">기타</option>
								</select> 
								
								<select class="selectTags" id="jobHuntPostRegion" style="width: 32%; 
								height: 43px;" name="jobHuntPostRegion">
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
							
							
							<div class="form-group" style="display: flex; margin-bottom: 4%;">
								<input class="selectTags" type="date" 
								id="jobHuntPostWorkDate" name="jobHuntPostWorkDate" 
								style="width:49%; height:43px; padding-left: 8px; margin-right: 2%" >
							
								<input type="text" id="jobHuntPostPay" name="jobHuntPostPay" class="form-control rounded"
									style="width: 49%; height:43px; padding-left: 8px;"
									oninput="trimInput(this); validatePay(this);" placeholder="Pay" />
							</div>
							
							<p>※ 이미지 사이즈는 30MB 이하만 올릴 수 있습니다.</p>
							<div class="form-group" style="margin-bottom: 4%;">
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
						
							<div class="big-image-container">
							    <img id="big-image" src="" alt="큰 이미지 미리보기" />
							</div>

							<div class="form-group"
								style="display: flex; justify-content: center; margin-bottom: 4%;">
								<textarea class="form-control rounded" id="jobHuntPostContent"
									name="jobHuntPostContent"></textarea>
							</div>
							<button class="btn btn-primary btn-rounded btn-block">Composite</button>
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
			var cleanInput = input.value.replace(/,/g, '');
			
			const priceRegex = /^[0-9]*$/; // 숫자만 입력하는 정규표현식
			if (!priceRegex.test(cleanInput)) {
				input.value = cleanInput.replace(/[^0-9]/g, '');
				if(input.value) {
					swal("fail", "가격은 숫자만 입력해주세요.", "error", {
						button : "OK",
					});
				}
			}  else {
		        // 숫자만 입력된 경우, 천 단위로 구분하여 표시.
		        // 쉼표를 제거하고 숫자로 변환한 후, 다시 포맷하여 입력값을 업데이트.
		        formatNumber(input);
		    }
		}
		
		function formatNumber(input) {
		    var num = input.value.replace(/,/g, '');

		    // 숫자로 변환 가능한지 확인하고, 가능하다면 포맷을 적용.
		    if (!isNaN(num)) {
		        // 정수로 변환한 후 로케일 문자열로 변환.
		        input.value = parseInt(num, 10).toLocaleString();
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


			// 여기서 pay의 값을 쉼표 없는 숫자로 변환
		    var cleanPay = pay.replace(/,/g, '');
		    document.getElementById('jobHuntPostPay').value = cleanPay;
			
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