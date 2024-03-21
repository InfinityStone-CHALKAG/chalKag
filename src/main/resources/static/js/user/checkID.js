var checkIdFlag = false;
function checkId() {
	// @ . 포함 .뒤는 2개에서 4개의 소문자 알파벳
	var idRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	var memberId = $('#memberId').val();

	if (memberId == '') {
		$("#IDErrMsg").text('입력된 이메일이 없습니다.');
		$("#IDErrMsg").css('color', 'red');
		return checkIdFlag;
	}
	
	if (!idRegex.test(memberId)) {
		$("#IDErrMsg").text('옳바른 이메일 형식이 아닙니다.');
		$("#IDErrMsg").css('color', 'red');
		return checkIdFlag;
	}

	$.ajax({
		type: "POST",
		url: "/checkId",
		data: { 'memberId': memberId },
		dataType: 'text',
		success: function(data) {
			if (data == '1') {
				$("#IDErrMsg").text("사용가능한 이메일 입니다.");
				$("#IDErrMsg").css("color", "green");
				checkIdFlag = true;
				return checkIdFlag;
			}
			else {
				$("#IDErrMsg").text("중복된 이메일입니다. 다시 입력해주세요");
				$("#IDErrMsg").css("color", "red");
				checkIdflag = false;
				return checkIdFlag;
			}
		},
		error: function(error) {
			console.log('에러' + error);
		}
	});
	}
	document.getElementById("memberId").addEventListener("input", function() {
    // input 내용이 바뀔 때마다 checkIDflag를 false로 설정 다시인증하기
    checkIdFlag = false;
});
