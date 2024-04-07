var checkNicknameFlag = false;
var memberGradeElement = document.getElementById("memberGrade");
if (memberGradeElement) {
	 var memberGrade = memberGradeElement.getAttribute("value");
}

console.log(memberGrade);
function checkNickname() {
	console.log("[로그] checkNickname 진입")
	var memberNickname = $('#memberNickname').val();
	if (memberNickname == '') {
		$("#nicknameErrMsg").text('입력된 닉네임이 없습니다.');
		$("#nicknameErrMsg").css('color', 'red');
		return checkNicknameFlag;
	}
	if (memberGrade == 'USER') {
		swal({
      title: 'fail',
      text: '프리미엄 회원이 아닙니다. \n프리미엄 결제 페이지로 이동하시겠습니까?',
      type: 'error',
      showCancelButton: true, // 확인, 취소 버튼 표시
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel'
    }, function(isConfirmed) {
      // 확인 버튼을 클릭했을 때의 동작
      if (isConfirmed) {
        // location.href를 사용하여 로그인 페이지로 이동
        window.location.href = 'payment';
      }
    });
    return false;
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