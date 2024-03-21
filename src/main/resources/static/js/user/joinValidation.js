// 페이지 로드될 때 실행
window.onload = function() {
    // 이름과 닉네임 입력 필드 가져오기
    var nameInput = document.getElementById("memberName");
    var nicknameInput = document.getElementById("memberNickname");

    // 입력 필드가 변경될 때마다 체크 함수 호출
    nameInput.addEventListener("input", checkInput);
    nicknameInput.addEventListener("input", checkInput);
   
};

// 입력 필드 체크 함수
function checkInput() {
    var nameInput = document.getElementById("memberName");
    var nicknameInput = document.getElementById("memberNickname");

    // 이름과 닉네임 입력값에서 공백과 띄어쓰기 제거
    nameInput.value = nameInput.value.replace(/[\s]/g, '');
    nicknameInput.value = nicknameInput.value.replace(/[\s]/g, '');
}
