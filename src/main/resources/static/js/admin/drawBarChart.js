const dataContainer = document.getElementById("dataContainer");
var signInCountByDayOfWeek = JSON.parse(dataContainer.getAttribute("data-signInCountByDayOfWeek"));
var signInCountByYearMonthDate = JSON.parse(dataContainer.getAttribute("data-signInCountByYearMonthDate"));
var signUpCountByYear = JSON.parse(dataContainer.getAttribute("data-signUpCountByYear"));
var signUpCountByAgeGroup = JSON.parse(dataContainer.getAttribute("data-signUpCountByAgeGroup"));
var signUpCountByGenderGroup = JSON.parse(dataContainer.getAttribute("data-signUpCountByGenderGroup"));

// 현재 날짜 객체 생성
var currentDate = new Date();

// 현재 년도 받기
var currentYear = currentDate.getFullYear();

// 현재 월 받기 (월은 0부터 시작하므로 1을 더해줌)
var currentMonth = currentDate.getMonth() + 1;
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('year').value = currentYear; // 연도 초기값 설정
    document.getElementById('month').value = currentMonth; // 월 초기값 설정
});
 var maleCount = parseInt(signUpCountByGenderGroup.maleGroup);
 var femaleCount = parseInt(signUpCountByGenderGroup.femaleGroup);
         
 var totalGenderCount = maleCount + femaleCount;
         
         var malePercentage = Math.round((maleCount / totalGenderCount) * 100);
		var femalePercentage = Math.round((femaleCount / totalGenderCount) * 100);
		console.log(malePercentage);
		console.log(femalePercentage);
		 // Display male and female percentages
    $("#malePercent").text(malePercentage);
    $("#femalePercent").text(femalePercentage);

//year 사이드 그래프 


// 그래프 시작
(function($) {
    "use strict";
    // 차트 인스턴스를 저장할 객체
    window.myCharts = {};

    // 초기화: 첫 번째 탭의 차트를 그림 
    // 메인 이동시 출력, 탭클릭시 출력을 위한 모듈화
    var chartId = "signInCountByDayOfWeekBar";
    drawsignInCountByDayOfWeek(); 
    
    // 이벤트: 탭 클릭시 이벤트
    $(".tab__item").click(function(e) {
        e.preventDefault(); // 기본 이벤트 방지
        var href = $(this).find("a").attr("href"); // 클릭된 탭의 href 값 (#tabN)
        console.log("[로그]" + href);

        // 현재 활성화된 탭 내용에서 canvas id 가져오기
        chartId = $(href).find("canvas").attr("id");
        console.log("[로그]" + chartId);
        // 모든 탭 항목과 내용에서 active 클래스 제거
        $(".tab__item").removeClass("active");
        $(".tab__content").removeClass("active");

        // 클릭된 탭 항목에 active 클래스 추가
        $(this).addClass("active");

        // 연결된 탭 내용 활성화
        $(href).addClass("active");
       
    // 이미지와 백분율을 처음 크기로 확대
    $(".Icons img").css("transform", "scale(1)");
    $("span[name='percentage']").css("transform", "scale(1)");

    // 애니메이션 효과로 이미지와 백분율 확대
    setTimeout(function() {
      if (malePercentage > femalePercentage) {
    // maleIcon scale을 2로 transition
    $("#maleIcon").css({
        "transition": "transform 3s ease-in-out",
        "transform": "scale(2)"
    });
    // femaleIcon scale을 1.6로 transition
    $("#femaleIcon").css({
        "transition": "transform 3s ease-in-out",
        "transform": "scale(1.6)"
    });
} else if (femalePercentage > malePercentage) {
    // femaleIcon scale을 2로 transition
    $("#femaleIcon").css({
        "transition": "transform 3s ease-in-out",
        "transform": "scale(2)"
    });
    // maleIcon scale을 1.6로 transition
    $("#maleIcon").css({
        "transition": "transform 3s ease-in-out",
        "transform": "scale(1.6)"
    });
}

        
        $("span[name='percentage']").css({
            "transition": "transform 3s ease-in-out",
            "opacity": "1",
            "transform": "scale(1.5)"
        });
    }, 100);
    
    
// percentage 출력
document.getElementById('malePercent').innerHTML = malePercentage;
document.getElementById('femalePercent').innerHTML = femalePercentage;

function animateNumber(id) {
    // 초기값을 0으로 설정
    $('#' + id).prop('Counter', 0).stop().animate({
        Counter: $('#' + id).text()
    }, {
        duration: 3000,
        easing: 'swing',
        step: function (now) {
            $(this).text(Math.ceil(now));
        },
        complete: function() {
            // 애니메이션이 끝나면 Counter 속성을 다시 0으로 설정하여 초기화
            $(this).prop('Counter', 0);
        }
    });
}


animateNumber('malePercent');
animateNumber('femalePercent');
    

        

        // 이후 필요한 로직을 chartId와 함께 구현
        if (chartId == "signInCountByDayOfWeekBar") {

            drawsignInCountByDayOfWeek();


        } else if (chartId == "signInCountByYearMonthDateLine") {

            handleSelectChange();
            // 년, 월 선택시 그래프 변화
            function handleSelectChange() {
                const selectedYear = document.getElementById('year').value;
                const selectedMonth = document.getElementById('month').value;

                $.ajax({
                    type: "POST",
                    url: "/signInCountByYearMonthDate",
                    data: {
                        'Year': selectedYear,
                        'Month': selectedMonth
                    },
                    dataType: 'text', // 서버로부터 받을 데이터의 타입
                    success: function(data) {
                        if (data) {
                            signInCountByYearMonthDate = JSON.parse(data); // 데이터를 JSON 형태로 파싱하여 변수에 저장
                            var date = [];
                            var signInCount = [];

                            for (var i = 0; i < signInCountByYearMonthDate.length; i++) {
                                date.push(signInCountByYearMonthDate[i].date);
                                signInCount.push(signInCountByYearMonthDate[i].signInCount);
                            }

                            console.log("AJAX SUCCESS");
                            console.log(signInCountByYearMonthDate); // 콘솔에 결과 출력
                            // 최대값 찾기
                            var maxSignInCount = Math.max(...signInCount);

                            // 최대값의 일의 자리를 0으로 만들기
                            var maxWithZero = Math.ceil(maxSignInCount / 10) * 10;

                            // 최대값에 50 추가하여 max 값 설정
                            var max = maxWithZero + 50;

                            drawLineChart(chartId, date, signInCount, max, "DAYS", "SIGNINCOUNT");
                        } else {
                            console.log("[로그] 데이터 없음");
                        }
                    },
                    error: function(error) {
                        console.log('에러: ' + error);
                    }
                });
            }

            document.getElementById('year').addEventListener('change', handleSelectChange);
            document.getElementById('month').addEventListener('change', handleSelectChange);

        } else if (chartId == "signUpCountByYearLine") {
            console.log("3번탭 진입");

            var year = []; 
            var signUpCount = [];
            for (var i = 0; i < signUpCountByYear.length; i++) {
                year.push(signUpCountByYear[i].year);
                signUpCount.push(signUpCountByYear[i].signUpCount);
            }
            // 추가 데이터 생성
            var lastYear = parseInt(year[year.length - 1]); // 마지막 년도를 숫자로 변환
            for (var i = 1; i <= 2; i++) { // 2개의 추가 데이터 생성
                year.push((lastYear + i).toString()); // 년도를 문자열로 변환하여 추가
                signUpCount.push("0"); // 가입 수는 "0"으로 설정
            }

            // 최대값 찾기
            var maxSignUpCount = Math.max(...signUpCount);

            // 최대값의 일의 자리를 0으로 만들기
            var maxWithZero = Math.ceil(maxSignUpCount / 10) * 10;

            // 최대값에 50 추가하여 max 값 설정
            var max = maxWithZero + 50;
            console.log(signUpCount);
            console.log(year);

            drawLineChart(chartId, year, signUpCount, max, "YEAR", "SIGNUPCOUNT");
         
        }

        //나이별 회원 수
        else if (chartId == "signUpCountByAgeGroupBar") {

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
                "AGE",
                "USER", {
                    beginAtZero: true,
                    min: 0,
                    max: max,
                    stepSize: 10
                }
            )
        }

        //성별 회원 수
        else if (chartId == "signUpCountByGenderGroupBar") {
            var maleCount = parseInt(signUpCountByGenderGroup.maleGroup);
            var femaleCount = parseInt(signUpCountByGenderGroup.femaleGroup);


            var biggerGenderCount = (maleCount >= femaleCount) ? maleCount + 50 : femaleCount + 50;

            var maxTemp = biggerGenderCount + (10 - ((biggerGenderCount % 10 != 0) ? biggerGenderCount % 10 : 10));
            drawBarChart(
                chartId,
                ["Male", "Female"],
                [maleCount, femaleCount],
                "GENDER",
                "USER", {
                    beginAtZero: true,
                    min: 0,
                    max: maxTemp,
                    stepSize: maxTemp / (maxTemp / 10)
                }
            )
        }
    });

    // 함수: 바 차트 그리기
    function drawBarChart(chartId, labels, data, xTitleLabel, yTitleLabel, ticksOptions) {
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
                    label: "USER",
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
                            text: yTitleLabel
                        },
                        ticks: {
                            stepSize: ticksOptions.stepSize
                        }
                    },
                    x: { // X축 설정 변경
                        title: { // X축 라벨 설정
                            display: true,
                            text: xTitleLabel
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
    function drawLineChart(chartId, labels, data, max, xTitleLabel, yTitleLabel) {
        var ctx = document.getElementById(chartId);

        // window.myCharts 객체가 없으면 생성
        if (!window.myCharts) {
            window.myCharts = {};
        }

        // 기존 차트가 있으면 파괴
        if (window.myCharts[chartId]) {
            window.myCharts[chartId].destroy();
        }

        window.myCharts[chartId] = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'USER',
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
                         title: {
                    display: true,
                    text: xTitleLabel, // x축 라벨 설정
                    color: '#111',
                    font: {
                        size: 14,
                        family: 'Montserrat'
                    }
                },
                        grid: {
                            display: false,
                            drawBorder: false
                        }
                    },
                    y: {
                        display: true,
                         title: {
                    display: true,
                    text: yTitleLabel, // x축 라벨 설정
                    color: '#111',
                    font: {
                        size: 14,
                        family: 'Montserrat'
                    }
                },
                        grid: {
                            display: false,
                            drawBorder: false
                        },
                        min: -2, // Y축의 최소값 설정
                        max: max, // Y축의 최대값 설정
                        ticks: {
                        callback: function(value, index, values) {
                            // -1인 경우에만 라벨을 감추기
                            return value === -2 ? null : value;
                        }
                    }
                    }
                }
            }
        });
    }

    function drawsignInCountByDayOfWeek() {



        var dayOfWeek = [];
        var signInCount = [];

        for (var i = 0; i < signInCountByDayOfWeek.length; i++) {
            dayOfWeek.push(signInCountByDayOfWeek[i].dayOfWeek);
        }
        for (var i = 0; i < signInCountByDayOfWeek.length; i++) {
            signInCount.push(signInCountByDayOfWeek[i].signInCount);
        }

        // 최대값 찾기
        var maxSignInCount = Math.max(...signInCount);

        // 최대값의 일의 자리를 0으로 만들기
        var maxWithZero = Math.ceil(maxSignInCount / 10) * 10;

        // 최대값에 50 추가하여 max 값 설정
        var max = maxWithZero + 50;

        drawBarChart(
            chartId,
            dayOfWeek,
            signInCount,
            "SIGNINCOUNT", 
            "DAYOFWEEK", {
                beginAtZero: true,
                min: 0,
                max: max,
                stepSize: 10
            }
        )
    }


})(jQuery);

