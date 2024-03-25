// 이 함수는 서버에서 전송한 인증코드와 사용자가 입력한 
// 코드를 비교하고 결과를 처리합니다.
$(document).ready(function() {
    $("#smsCheck").on('click',  function() {
		console.log("smsCheck 동작함");
		console.log(serverGeneratedCode);
        if ($("#memberPhCheck").val() ==serverGeneratedCode) {
            $(".successPhCheck").text("인증번호가 일치합니다.");
            $(".successPhCheck").css("color", "green");
            checkPhFlag = true;
			return checkPhFlag;
        } else {
            $(".successPhCheck").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
            $(".successPhCheck").css("color", "red");
            $(this).attr("autofocus", true);
            checkPhFlag = false;
			return checkPhFlag;
            
        }
    });
});

