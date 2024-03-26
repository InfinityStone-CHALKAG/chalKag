const dataContainerAgeGender = document.getElementById("dataContainer");
var signUpCountByAgeGroup = JSON.parse(dataContainerAgeGender.getAttribute("data-signUpCountByAgeGroup"));
var signUpCountByGenderGroup =  JSON.parse(dataContainerAgeGender.getAttribute("data-signUpCountByGenderGroup"));

console.log(signUpCountByAgeGroup);

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
		else if (chartId == "singleBarChart4") {
			
			var ageGroup = ["10", "20", "30", "40", "50", "60"];
var signUpCount = new Array(6).fill(0); // signUpCount 배열을 0으로 초기화


			signUpCountByAgeGroup.forEach(function(item) {
    var index = ageGroup.indexOf(item.ageGroup);
    if (index !== -1) {
        signUpCount[index] = parseInt(item.signUpCount); // parseInt를 사용하여 문자열을 숫자로 변환
    }
});                       

 // 최대값 찾기
    var maxSignUpCount = Math.max(...signUpCount);

    // 최대값의 일의 자리를 0으로 만들기
    var maxWithZero = Math.ceil(maxSignUpCount / 10) * 10;

    // 최대값에 50 추가하여 max 값 설정
    var max = maxWithZero + 50;

			drawBarChart(
				chartId,
				ageGroup,
				signUpCount,
				"Age",
				"Users",
				{
					beginAtZero: true,
					min: 0,
					max: max,
					stepSize: 10
				}
			)
		}
	 else if (chartId == "singleBarChart5") {
			var maleCount = parseInt(signUpCountByGenderGroup.maleGroup);
			var femaleCount = parseInt(signUpCountByGenderGroup.femaleGroup);
			
			
var biggerGenderCount = (maleCount >= femaleCount) ? maleCount+50 : femaleCount + 50;

var maxTemp = biggerGenderCount + (10 - ((biggerGenderCount%10 !=0) ? biggerGenderCount%10 : 10 ));
			drawBarChart(
				chartId,
				["Male", "Female"],
				[maleCount, femaleCount],
				"Gender",
				"Users",
				{
					beginAtZero: true,
					min: 0,
					max: maxTemp,
					stepSize: maxTemp/(maxTemp/10)
				}
			)
		}
	});
})(jQuery);