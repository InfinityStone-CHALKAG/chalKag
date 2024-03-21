
	// 이벤트: 탭 클릭
	$(".tab__item").click(function(e) {
		e.preventDefault(); // 기본 이벤트 방지
		var href = $(this).find("a").attr("href"); // 클릭된 탭의 href 값 (#tabN)
		

		$(".tab__item").removeClass("active");
		$(".tab__content").removeClass("active");

		$(this).addClass("active");
		$(href).addClass("active");

	});
