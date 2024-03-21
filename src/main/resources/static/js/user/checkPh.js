
function checkPh(){
	var memberPh=$('#memberPh').val();
	
	$.ajax({
		type : "POST",
		url : "/checkPh",
		data : {'memberPh': memberPh},
		dataType: 'text',
		success : function(data){
			if(data=='1'){
				$("#phErrMsg").text("사용가능한 번호 입니다.");
				checkPhflag = true;
				return checkPhflag;
			}
			else{
				$("#phErrMsg").text("중복된 번호입니다. 다시 입력해주세요");
				checkPhflag = false;
				return checkPhflag;
			}
		},
		error: function(error){
			console.log('에러'+error);
		}
	});
}
	document.getElementById("#memberPh").addEventListener("input", function() {
    // input 내용이 바뀔 때마다 checkPhflag를 false로 설정 다시인증하기
    checkPhflag = false;
});