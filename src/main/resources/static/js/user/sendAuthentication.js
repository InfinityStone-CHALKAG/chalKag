var serverGeneratedCode = "";


function sendAuthenticationSMS() {
	// 사용자가 입력한 전화번호 가져오기
	var memberPh = $("#memberPh").val();

	// AJAX를 사용하여 서버에 전화번호 전송
	$.ajax({
		url: "/sendAuthenticationSMS",
		type: "POST",
		dataType: "text",
		data: { memberPh: memberPh },
		success: function(data) {
			// 서버 응답에 따른 처리
			if (data !== "fail") {
				console.log("data " + data);
				$("#phErrMsg").text('');
				swal("success", "인증번호 발송이 완료되었습니다.", "success", {
					button: "OK",
				});
				console.log("[로그] serverGeneratedCode :" + data)
				// 성공적으로 SMS를 보낸 경우 추가 동작을 수행할 수 있습니다.
				serverGeneratedCode = data;
			} else {
				swal("fail", "인증번호 발송 실패", "error", {
					button: "OK",
				});
				// SMS 전송 실패 시 사용자에게 알림을 표시할 수 있습니다.
			}
		},
		error: function(error) {
			console.log(error);
			alert("ajax 요청오류");
			// AJAX 요청 중 오류가 발생한 경우에 대한 처리
		}
	});
}

document.getElementById("memberPh").addEventListener("input", function() {
	// input 내용이 바뀔 때마다 checkPhFlag를 false로 설정 다시인증하기
	checkPhFlag = false;
});
