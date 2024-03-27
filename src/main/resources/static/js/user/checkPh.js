

function checkPh() {
    var memberPh = $('#memberPh').val();

    if (!phRegex.test(memberPh)) {
        $("#phErrMsg").text('올바른 번호 형식이 아닙니다.');
        $("#phErrMsg").css('color', 'red');
        return checkPhFlag;
    }

    $.ajax({
        type: "POST",
        url: "/checkPh",
        data: { 'memberPh': memberPh },
        dataType: 'text',
        success: function (data) {
            if (data == '1') {
				 $(".successPhCheck").text("");
                sendAuthenticationSMS();
            }
            else {
				
				 swal({
      title: 'fail',
      text: '이미 가입되어 있는 회원입니다.\n로그인 페이지로 이동하시겠습니까?',
      type: 'error',
      showCancelButton: true, // 확인, 취소 버튼 표시
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel'
    }, function(isConfirmed) {
      // 확인 버튼을 클릭했을 때의 동작
      if (isConfirmed) {
        // location.href를 사용하여 로그인 페이지로 이동
        window.location.href = 'signIn';
      }
    }); 
            }
        },
        error: function (error) {
            console.log('에러' + error);
        }
    });
}
document.getElementById("memberPh").addEventListener("input", function () {
    // input 내용이 바뀔 때마다 checkPhflag를 false로 설정 다시인증하기
    checkPhFlag = false;
});
