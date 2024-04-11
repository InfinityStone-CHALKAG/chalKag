$(document).ready(function() {
    $("#recommendBtn").click(function(){
        // data- 속성에서 데이터 읽어오기
        var postId = $(this).data('postid');
        var memberId = $(this).data('memberid');

        console.log("postId : " + postId);
        console.log("memberId : " + memberId);

        // AJAX 요청을 통해 서버에 요청
        $.ajax({
            type: "GET",
            url: "/recommendUpDown",
            data: {'postId': postId, 'memberId': memberId},
            success: function(data){
				history.go(0);
            },
            error: function(){
                console.log("에러발생!");
            }
        });
    });
});


	function message() {
	    swal({
	        title: "fail",
	        text: "로그인 후 이용해주세요.",
	        type: "error",
	        showCancelButton: false,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "OK",
	        closeOnConfirm: true
	    }, function() {
	        // "OK" 버튼 누르면 실행될 코드
	        window.location.href = "/signIn"; // 로그인 페이지로 이동
	    });
	}