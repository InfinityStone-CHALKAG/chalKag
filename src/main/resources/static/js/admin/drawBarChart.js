(function($) {
	"use strict";
	// 차트 인스턴스를 저장할 객체
	window.myCharts = {};
	
	// 함수: 바 차트 그리기
	function drawBarChart(chartId, labels, data, xAxesLabelString, yAxesLabelString, ticksOptions) {
		var ctx = document.getElementById(chartId);
		console.log("[로그] ctx: " + ctx);
		console.log("[로그] chartId: " + chartId);
		// 기존 차트가 있으면 파괴
		if (window.myCharts[chartId]) {
			window.myCharts[chartId].destroy();
		}

		// 새 차트 생성
		window.myCharts[chartId] = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: labels,
				datasets: [{
					label: "My First dataset",
					data: data,
					borderColor: "rgba(247, 63, 82, 1)",
					borderWidth: "0",
					backgroundColor: "rgba(247, 63, 82, 1)",
				}]
			},
			options: {
				scales: {
					y: { // Y축 설정 변경
						beginAtZero: ticksOptions.beginAtZero,
						min: ticksOptions.min,
						max: ticksOptions.max,
						stepSize: ticksOptions.stepSize,
						title: { // Y축 라벨 설정
							display: true,
							text: yAxesLabelString
						},
						ticks: {
							stepSize: ticksOptions.stepSize
						}
					},
					x: { // X축 설정 변경
						title: { // X축 라벨 설정
							display: true,
							text: xAxesLabelString
						}
					}
				},
				plugins: {
					legend: { // 범례 설정
						display: true
					}
				}
			}
		});

	}
	// 초기화: 첫 번째 탭의 차트를 그림
	drawBarChart(
		'singleBarChart1',
		["03/17", "03/18", "03/19", "03/20", "03/21", "03/22", "03/23"],
		[55, 50, 75, 80, 56, 55, 60],
		"Day",
		"Users",
		{
			beginAtZero: true,
			min: 0,
			max: 100,
			stepSize: 20
		}
	)




	// 이벤트: 탭 클릭
	$(".tab__item").click(function(e) {
		e.preventDefault(); // 기본 이벤트 방지
		var href = $(this).find("a").attr("href"); // 클릭된 탭의 href 값 (#tabN)
		// 현재 활성화된 탭 내용에서 canvas id 가져오기
		var chartId = $(href).find("canvas").attr("id");
		var lables = ["First", "Second", "Third", "Fourth"];
		var data = [400, 200, 300, 259];
		var xAxesLabelString = "Week";
		var yAxesLabelString = "Users";
		var ticksOptions = {
			beginAtZero: true,
			min: 0,
			max: 400,
			stepSize: 100
		};
		// 모든 탭 항목과 내용에서 active 클래스 제거
		$(".tab__item").removeClass("active");
		$(".tab__content").removeClass("active");

		// 클릭된 탭 항목에 active 클래스 추가
		$(this).addClass("active");

		// 연결된 탭 내용 활성화
		$(href).addClass("active");

		// 이후 필요한 로직을 chartId와 함께 구현
		if (chartId == "singleBarChart1") {

			drawBarChart(
				chartId,
				["03/17", "03/18", "03/19", "03/20", "03/21", "03/22", "03/23"],
				[55, 50, 75, 80, 56, 55, 60],
				"Day",
				"Users",
				{
					beginAtZero: true,
					min: 0,
					max: 100,
					stepSize: 20
				}
			)
		}
		else if (chartId == "singleBarChart2") {


			drawBarChart(chartId, lables, data, xAxesLabelString, yAxesLabelString, ticksOptions);
		}
		else if (chartId == "singleBarChart3") {

			drawBarChart(
				chartId,
				["0~10대", "20대", "30대", "40대", "50대", "60대", "70대"],
				[130, 80, 50, 90, 40, 20, 1],
				"Age",
				"Users",
				{
					beginAtZero: true,
					min: 0,
					max: 130,
					stepSize: 20
				}
			)
		} else if (chartId == "singleBarChart4") {

			drawBarChart(
				chartId,
				["Male", "Female"],
				[190, 300],
				"Gender",
				"Users",
				{
					beginAtZero: true,
					min: 0,
					max: 500,
					stepSize: 100
				}
			)
		}
	});
})(jQuery);