// 비밀번호 확인 함수
var checkPasswordFlag = false;
function checkPassword() {
	var password = document.getElementById("memberPw").value;
	var confirmPassword = document.getElementById("passwordCheck").value;
	var errorElement = document.getElementById("pwError");

	// 비밀번호 형식 검사
	var pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;



	if (!pwRegex.test(password)) {
		errorElement.textContent = "비밀번호는 최소 8자 이상이며 영문, 숫자, 특수문자를 포함해야 합니다."; // 에러 메시지 표시
		errorElement.style.color = "red";
		checkPasswordFlag = false;
		return checkPasswordFlag;
	}


	// 비밀번호와 비밀번호 확인이 일치하는지 확인
	if (password === confirmPassword) {
		errorElement.textContent = "비밀번호가 일치합니다."; // 에러 메시지를 초기화
		errorElement.style.color = "green";
		checkPasswordFlag = true;
		return checkPasswordFlag;
	}
	else {
		errorElement.textContent = "비밀번호가 일치하지 않습니다."; // 에러 메시지 표시
		errorElement.style.color = "red";
		checkPasswordFlag = false;
		return checkPasswordFlag;
	}


}

// 비밀번호 확인 필드 입력 시 체크 함수 호출
document.getElementById("memberPw").addEventListener("input", checkPassword);
document.getElementById("passwordCheck").addEventListener("input", checkPassword);