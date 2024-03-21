(function($) {
	"use strict";
	// 차트 인스턴스를 저장할 객체
	window.myCharts = {};
	// 함수: 라인 차트 그리기
	function drawLineChart(chartId) {
		var ctx = document.getElementById(chartId);
		// 기존 차트가 있으면 파괴
		if (window.myCharts && window.myCharts[chartId]) {
			window.myCharts[chartId].destroy();
		}
		// 빈 배열 생성
		var data = [];

		// 1에서 50까지의 샘플 데이터 생성 및 배열에 추가
		// 31개의 랜덤 숫자 생성하여 배열에 추가
		for (var i = 0; i < 31; i++) {
			var randomNumber = Math.floor(Math.random() * 20) + 1; // 1에서 50 사이의 랜덤 정수 생성
			data.push(randomNumber.toString());
		}

		// 새 차트 생성
		window.myCharts = window.myCharts || {};
		window.myCharts[chartId] = new Chart(ctx, {
			type: 'line',
			data: {
				labels: [["17", "2월"], "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", ["1", "3월"], "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", ["17", "오늘"]],
				datasets: [{
					label: "Foods",
					data: data,
					backgroundColor: 'transparent',
					borderColor: 'rgba(247, 63, 82, 1)',
					borderWidth: 3,
					pointStyle: 'circle',
					pointRadius: 5,
					pointBorderColor: 'rgba(247, 63, 82, 1)',
					pointBackgroundColor: 'rgba(255,255,255,1)',
					tension: 0, // lineTension을 tension으로 변경
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: {
						display: false
					},
					tooltip: {
						mode: 'index',
						intersect: false,
						titleFont: {
							size: 12,
							family: 'Montserrat'
						},
						bodyFont: {
							size: 12,
							family: 'Montserrat'
						},
						backgroundColor: '#fff',
						titleColor: '#000',
						bodyColor: '#000',
						cornerRadius: 3,
					}
				},
				scales: {
					x: {
						display: true,
						grid: {
							display: false,
							drawBorder: false
						}
					},
					y: {
						display: true,
						grid: {
							display: false,
							drawBorder: false
						},
						min: 0, // Y축의 최소값 설정
						max: 50, // Y축의 최대값 설정
					}
				}
			}
		});
	}

	// 초기화: 첫 번째 탭의 차트를 그림
	drawLineChart("singleLineChart1");

	// 이벤트: 탭 클릭
	$(".tab__item").click(function(e) {
		e.preventDefault(); // 기본 이벤트 방지
		var href = $(this).find("a").attr("href"); // 클릭된 탭의 href 값 (#tabN)
		var chartId = $(href).find("canvas").attr("id");

		$(".tab__item").removeClass("active");
		$(".tab__content").removeClass("active");

		$(this).addClass("active");
		$(href).addClass("active");

		drawLineChart(chartId);
	});
})(jQuery);