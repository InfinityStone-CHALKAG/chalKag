function signIn() {
    var memberId = $("#memberId").val();
    var memberPw = $("#memberPw").val();
    var idRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    if (!idRegex.test(memberId)) {
        $("#IDErrMsg").text('옳바른 이메일 형식이 아닙니다.');
        $("#IDErrMsg").css('color', 'red');
        return false;
    }
    $("#IDErrMsg").text('');

    $.ajax({
        url: "/signIn",
        type: "POST",
        /*dataType: "text",*/
        data: {
            "memberId": memberId,
            "memberPw": memberPw
        },
        success: function (data) {
            if (data == '1') {
                location.href = '/main';
                /*s
                swal("success", "메인페이지로 이동합니다.", "success", {
                    button: "OK",
                }).then(function(){
                    location.href='/main';
                });
                */
            } else if (data == '2') {
                location.href = '/adminMain'
            } else {

                swal("fail", "ID or PW 를 확인해주세요.", "error", {
                    button: "OK",
                });
            }
        },
        error: function (error) {
            console.log('에러' + error);
        }
    });
}