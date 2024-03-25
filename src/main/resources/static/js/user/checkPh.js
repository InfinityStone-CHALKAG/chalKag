var checkPhFlag = false;
var phRegex = /^010\d{8}$/i;
function checkPh(){
	var memberPh=$('#memberPh').val();
	
		if (!phRegex.test(memberPh)) {
		$("#phErrMsg").text('올바른 번호 형식이 아닙니다.');
		$("#phErrMsg").css('color', 'red');
		return checkPhFlag;
	}
	
	$.ajax({
		type : "POST",
		url : "/checkPh",
		data : {'memberPh': memberPh},
		dataType: 'text',
		success : function(data){
			if(data=='1'){
				
				sendAuthenticationSMS();
				
			}
			else{
				swal("fail", "이미 가입되어있는 회원입니다.", "error", {
                button: "OK",
            });
				
		}
	},
		error: function(error){
			console.log('에러'+error);
		}
	});
}
	document.getElementById("memberPh").addEventListener("input", function() {
    // input 내용이 바뀔 때마다 checkPhflag를 false로 설정 다시인증하기
    checkPhFlag = false;
});