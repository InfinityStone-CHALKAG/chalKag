var checkNicknameFlag = false;
function checkNickname() {
	console.log("[로그] checkNickname 진입")
	var memberNickname = $('#memberNickname').val();
	if (memberNickname == '') {
		$("#nicknameErrMsg").text('입력된 닉네임이 없습니다.');
		$("#nicknameErrMsg").css('color', 'red');
		return checkNicknameFlag;
	}
	
	$.ajax({
		type: "POST",
		url: "/checkNickname",
		data: { 'memberNickname': memberNickname },
		dataType: 'text',
		success: function(data) {
			if (data == '1') {
				console.log("[로그] ajax SUCCESS")
				$("#nicknameErrMsg").text("사용가능한 닉네임 입니다.");
				$("#nicknameErrMsg").css("color", "green");
				checkNicknameFlag = true;
				return checkNicknameFlag;
			}
			else {
				console.log("[로그] 중복")
				$("#nicknameErrMsg").text("중복된 닉네임입니다. 다시 입력해주세요");
				$("#nicknameErrMsg").css("color", "red");
				checkNicknameFlag = false;
				return checkNicknameFlag;
			}
		},
		error: function(error) {
			console.log('에러' + error);
		}
	});
}
	document.getElementById("memberNickname").addEventListener("input", function() {
    // input 내용이 바뀔 때마다 checkNicknameflag를 false로 설정 다시인증하기
    checkNicknameFlag = false;
});