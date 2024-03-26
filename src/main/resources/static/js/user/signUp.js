document.addEventListener('DOMContentLoaded', function () {
	
    var uploadButton = document.getElementById('uploadButton');
    var fileInput = document.getElementById('fileInput');

// uploadButton이 존재할 때만 코드 실행
if (uploadButton) {
    uploadButton.addEventListener('click', function (event) {
        event.preventDefault(); // 폼 제출 방지
        fileInput.click();
    });
}
if (fileInput) {
    fileInput.addEventListener('change', function () {
        var file = this.files[0];
        if (file) {
            var fileName = file.name;
            var fileSize = file.size;
            var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|webp)$/;
            var maxSize = 30 * 1024 * 1024; // 30MB

            if (!fileForm.test(fileName)) {
                swal('fail', 'jpg, jpeg, png, gif, bmp, webp 만 업로드 가능합니다.', 'error', {
                    button: 'OK',
                });
                this.value = '';
                return;
            } else if (fileSize > maxSize) {
                swal('fail', '30MB 이하로 등록 가능합니다.', 'error', {
                    button: 'OK',
                });
                this.value = '';
                return;
            } else {
                var reader = new FileReader();
                reader.onload = function (e) {
                    document.getElementById('preview').src = e.target.result;
                    document.getElementById('previewDiv').style.display = 'block';
                };
                reader.readAsDataURL(file);
            }
        }
    });
}


var signUpForm = document.getElementById('yourFormId');

if (signUpForm) {
    signUpForm.onsubmit = function () {
        return validateForm();
    };
}




    function validateForm() {
		 var memberName = document.getElementById("memberName").value;
        if (checkIdFlag == false) {
            $("#IDErrMsg").text("");
            swal("fail", "이메일을 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
        if (checkPasswordFlag == false) {
            swal("fail", "비밀번호를 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
		if (memberName.trim() === "") {
			console.log(memberName);
			console.log(memberName.trim());
	
            // SweetAlert으로 실패 메시지 표시
            swal("fail", "이름을 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
        if (checkNicknameFlag == false) {
			console.log("checkNicknameFlag 진입");
            $("#nicknameErrMsg").text("");
            swal("fail", "닉네임을 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
        if (checkPhFlag == false) {
            $(".successPhCheck").text("");
            swal("fail", "핸드폰인증을 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
        if (document.querySelectorAll('input[type="radio"][name="memberGender"]:checked').length < 1) {
            swal("fail", "성별을 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }
        if (!document.getElementById("agreeTerms1").checked) {
            swal("fail", "약관동의를 확인해주세요.", "error", {
                button: "OK",
            });
            return false;
        }

        // 모든 검증이 통과했을 경우
        return true;
    }
});