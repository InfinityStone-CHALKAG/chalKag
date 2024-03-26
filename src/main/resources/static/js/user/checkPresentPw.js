var checkPresentPwFlag = false;
function checkPresentPw() {
	console.log("[로그] checkPw 진입")
	var memberPw = $('#memberPw').val();
	if (memberPw == '') {
		$("#PresentPwErrMsg").text('입력된 비밀번호가 없습니다.');
		$("#PresentPwErrMsg").css('color', 'red');
		return checkPresentPwFlag;
	}
	
	$.ajax({
		type: "POST",
		url: "/checkPw",
		data: { 'memberPw': memberPw },
		dataType: 'text',
		success: function(data) {
			if (data == '1') {
				console.log("[로그] ajax SUCCESS")
				$("#PresentPwErrMsg").text("본인확인 완료");
				$("#PresentPwErrMsg").css("color", "green");
				checkPresentPwFlag = true;
				return checkPresentPwFlag;
			}
			else {
				console.log("[로그] 중복")
				$("#PresentPwErrMsg").text("비밀번호 불일치 다시 입력해주세요.");
				$("#PresentPwErrMsg").css("color", "red");
				checkPresentPwFlag = false;
				return checkPresentPwFlag;
			}
		},
		error: function(error) {
			console.log('에러' + error);
		}
	});
}
document.getElementById("memberPw").addEventListener("input", checkPresentPw);
	/*document.getElementById("memberPw").addEventListener("input", function() {
    // input 내용이 바뀔 때마다 checkPresentPwflag를 false로 설정 다시인증하기
    checkPresentPwFlag  = false;
});*/