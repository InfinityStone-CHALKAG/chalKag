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
	
	
		//라인 차트 그리기
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




	// 이벤트: 탭 클릭시 이벤트
	$(".tab__item").click(function(e) {
		e.preventDefault(); // 기본 이벤트 방지
		var href = $(this).find("a").attr("href"); // 클릭된 탭의 href 값 (#tabN)
		
		// 현재 활성화된 탭 내용에서 canvas id 가져오기
		var chartId = $(href).find("canvas").attr("id");
	
		// 모든 탭 항목과 내용에서 active 클래스 제거
		$(".tab__item").removeClass("active");
		$(".tab__content").removeClass("active");

		// 클릭된 탭 항목에 active 클래스 추가
		$(this).addClass("active");

		// 연결된 탭 내용 활성화
		$(href).addClass("active");







		// 이후 필요한 로직을 chartId와 함께 구현
		if (chartId == "singleLineChart1") {

		}
		else if (chartId == "singleLineChart2") {


			
		} else if (chartId == "singleLineChart3") {
		drawLineChart("singleLineChart3");

			
		} 	
		
		
		
		
		
		
		//나이별 회원 수
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
		
		
		//성별 회원 수
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