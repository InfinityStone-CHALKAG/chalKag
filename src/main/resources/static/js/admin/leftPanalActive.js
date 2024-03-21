$(document).ready(function() {
	// 현재 페이지의 URL을 가져옵니다.
	var current_page_URL = location.href;

	// 내비게이션 바의 모든 링크를 순회합니다.
	$(".nav.navbar-nav a").each(function() {
		// 링크의 href 값을 가져옵니다.
		var target_URL = $(this).attr("href");
		// console.log("[로그] target_URL : " + target_URL)
		// 현재 페이지의 URL과 링크의 href가 일치하는 경우,
		// 해당 링크의 부모 요소(li)에 'active' 클래스를 추가합니다.
		if (current_page_URL.indexOf(target_URL) != -1) {
			$(this).parent().addClass('active').siblings().removeClass('active');
		}
	});
});