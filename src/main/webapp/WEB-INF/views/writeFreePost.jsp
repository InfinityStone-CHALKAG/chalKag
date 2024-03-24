<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags"%>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
<style>
			#writerForm {
				width: 70%;
			}

			#freePostTitle {
				margin-bottom: 1%;
			}

    .ck-editor .ck-rounded-corners {
			width: 100%;
      height: 500px; /* 원하는 높이 설정 */
			margin-bottom: 3%;
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
							<form action="/writeFreePost" method="post" onsubmit="return validateForm()">
								<div class="form-group" style="display: flex; justify-content: center;">
									<input type="text" id="freePostTitle" name="freePostTitle" class="form-control rounded" placeholder="Title" />
								</div>
										<p>※ 이미지 사이즈는 30MB 이하만 올릴 수 있습니다.</p>
									<textarea id="freePostContent" name="freePostContent" class="form-control rounded" ></textarea>
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
	<script
		src="https://cdn.ckeditor.com/ckeditor5/41.2.0/classic/ckeditor.js"></script>
	<script>$("input").iCheck({
      checkboxClass: 'icheckbox_square-red',
      radioClass: 'iradio_square-red',
      cursor: true
		});</script>
			<script src="css/user/js/e-magz.js"></script>
	<script>
			// 에디터에 입력한 값을 받아서 유효성을 처리할 수 있는 변수
			let editorInstance;
			ClassicEditor
        .create(document.querySelector('#freePostContent'), {
          // CKEditor 설정
          ckfinder: {
                // 이미지 업로드를 위한 설정
                uploadUrl: '/your-upload-url'	
            }
        }).then(editor => {
        	editorInstance = editor;

					// 파일 업로드 직전에 실행될 리스너 추가
				editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
				return {
					upload: () => {
						return loader.file.then(file => {
								// 이미지 크기 및 형식 검사 로직
								const maxSize = 1024 * 1024 * 30; // 30MB
								const validTypes = ['image/jpeg', 'image/png', 'image/gif'];
									
								if (file.size > maxSize) {
									return Promise.reject(swal("fail", "파일 크기가 너무 큽니다.", "error", {
                        button: "OK",
                    }));
								}
									
								if (!validTypes.includes(file.type)) {

									return Promise.reject(swal("fail", "지원하지 않는 파일 형식입니다.", "error", {
                        button: "OK",
                    }));
								}

								// 유효성 검사를 통과한 경우, 실제 업로드 로직 구현
								const data = new FormData();
								data.append('upload', file); // 'upload'는 서버에서 파일을 참조하는 키 값'

								return new Promise((resolve, reject) => {
            		const xhr = new XMLHttpRequest();
            		xhr.open('POST', '/your-upload-url', true);
            		xhr.responseType = 'json';

            		xhr.onload = () => {
                	if (xhr.status === 200) {
                    resolve({
                        default: xhr.response.url // 서버에서 반환하는 파일의 URL.
                    });
                	} else {
                    reject(swal("fail", "업로드 실패", "error", {
                        button: "OK",
                    }) + xhr.status + ' ' + xhr.statusText);
                	}
            		};

            		xhr.onerror = () => {
                	reject(swal("fail", "업로드 중 네트워크 오류가 발생했습니다.", "error", {
                        button: "OK",
                    }));
           		 	};

            			xhr.send(data);
        				});
									
							});
					}
				};
			};

				}).catch(error => {
          // 여기에서 SweetAlert로 에러 메시지를 표시.
					swal("fail", error, "error", {
                    button: "OK",
                });
        });
			
			// 입력 필드에서 공백만 있는 경우를 처리하기 위한 함수
			function trimInput(element) {
				element.value = element.value.trim();
			}
			
			// 폼 제출 시 유효성 검사를 실행하는 함수
			function validateForm() {
				var title = document.getElementById('freePostTitle').value;
				var content = editorInstance.getData();
			
				// 제목과 내용이 비어있는지 확인.
				if (!title.trim() || !content.trim()) {
					swal("fail", "모든 필드를 채워주세요.", "error", {
     				button: "OK",
  				});
					return false; // 폼 제출을 막는다.
				}
			
				return true; // 모든 검사를 통과하면 폼 제출을 진행
			}
			</script>
</body>
</html>