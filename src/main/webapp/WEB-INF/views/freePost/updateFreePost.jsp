<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<!DOCTYPE html>
<html>
<head>
<chalKagTags:webCss />
<style>
/* 작업일 작성 css */
#freePostWorkDate {
	border-radius: 5px;
	border: solid 1px rgb(179, 179, 179);
}

/* 내용 입력칸 css */
#freePostContent {
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
						<form action="/updateFreePost" method="post"
							onsubmit="return validateForm(event)"
							enctype="multipart/form-data">
							<!-- 글 번호 -->
							<input type="hidden" name="freePostId" value="${updateFreePost.freePostId}" />

							<div class="form-group"
								style="display: flex; justify-content: center; margin-bottom: 4%;">
								<!-- 글 제목 -->
								<input type="text" class="form-control rounded"
									id="freePostTitle" name="freePostTitle"
								placeholder="Title" value="${updateFreePost.freePostTitle}" />
							</div>
							<!-- 이미지 업로드 안내 문구 -->
							<p>※ The size. There is a maximum file size of '1GB' for all images.</p>
							<div id="imageData"></div>
							<div class="form-group" style="margin-bottom: 4%;">
								<input type="file" name="file" id="fileInput" accept="image/*"
									style="display: none;" multiple> <input type="button"
									id="customButton" value="Upload">
							</div>
							<!-- 이미지 미리보기 -->
							<div id="preview" style="display: flex; justify-content: center;">
								<!-- 이미지 슬라이드 버튼 -->
								<button class="imgSlidebtn" id="slideLeft">◀</button>
								<!-- 이미지 미리보기 출력 -->
								<div style="overflow: hidden; flex-grow: 1;">
									<div class="image-container"></div>
								</div>
								<!-- 이미지 슬라이드 버튼 -->
								<button class="imgSlidebtn" id="slideRight">▶</button>
							</div>
							<!-- 이미지 큰 화면으로 보는 태그 -->
							<div class="big-image-container">
							    <img id="big-image" src="" alt="큰 이미지 미리보기" />
							</div>
							<!-- 내용 작성 -->
							<div class="form-group"
								style="display: flex; justify-content: center;  margin-bottom: 4%;">
								<textarea class="form-control rounded" id="freePostContent"
									name="freePostContent">${updateFreePost.freePostContent}</textarea>
							</div>
							<!-- 글 수정 완료 버튼 -->
							<button class="btn btn-primary btn-rounded btn-block ">Composite</button>
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
	<script src="css/user/scripts/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
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
		
		// 이미지 데이터가 포함된 요소를 선택
		const imageDataElement = document.getElementById('imageData');
		// data-images 속성에서 이미지 데이터를 읽어옴
		const imagesData = imageDataElement.getAttribute('data-images');
		
		// JSON 형태의 문자열 데이터를 JavaScript 객체(배열)로 변환
		const imagesArray = JSON.parse(imagesData);
		
		// imagesArray 배열을 사용하여 필요한 작업 수행 (이미 배열, 별도 할당 필요 없음)
		imagesArray.forEach(function(imageName) {
		    console.log(imageName);
		});
		
		// 이미지를 미리보기 영역에 추가하는 함수
		function addImagesToPreview(imagePaths) {
		    var imageContainer = document.querySelector('.image-container');
		    
		    // 기존의 이미지들을 제거
		    imageContainer.innerHTML = '';

		    // 이미지 경로 배열을 순회하며 이미지 요소를 생성하고, 미리보기 영역에 추가
		    imagePaths.forEach(function(path) {
		        var img = document.createElement('img');
		        img.src = path; // 이미지 경로 설정
		        img.style.width = '100%'; // 미리보기 영역에 맞게 이미지 크기 조정
		        imageContainer.appendChild(img);
		    });
		}

		// 이미지 미리보기 로직 초기화
		// addImagesToPreview(imagePaths);
		// 이미지를 미리보기 영역에 추가하는 함수 호출 시, imagesArray를 직접 전달
		addImagesToPreview(imagesArray);
		
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
		
		document.getElementById('paymentForm').addEventListener('submit', function(event) {
		    // 폼 제출 이벤트를 잡아서 처리
		    var input = document.getElementById('payInput');
		  

		    // 이 시점에서 폼 데이터는 쉼표가 제거된 숫자로 제출.
		    // 필요한 경우 여기에서 event.preventDefault()를 호출하여 폼의 기본 제출을 방지,
		    // AJAX 등을 사용하여 데이터를 처리.
		});
		
		function formatNumber(input) {
		    var num = input.value.replace(/,/g, '');

		    // 숫자로 변환 가능한지 확인하고, 가능하다면 포맷을 적용.
		    if (!isNaN(num)) {
		        // 정수로 변환한 후 로케일 문자열로 변환.
		        input.value = parseInt(num, 10).toLocaleString();
		    }
		}

		function validateForm(event) {
			var title = document.getElementById('freePostTitle').value;
			var pay = document.getElementById('freePostPay').value;
			var role = document.getElementById('freePostRole').value;
			var workDate = document.getElementById('freePostWorkDate').value;
			var concept = document.getElementById('freePostConcept').value;
			var content = document.getElementById('freePostContent').value;
			var imageInput = document.getElementById('fileInput');

			// 여기서 pay의 값을 쉼표 없는 숫자로 변환
		    var cleanPay = pay.replace(/,/g, '');
		    document.getElementById('freePostPay').value = cleanPay;

			
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