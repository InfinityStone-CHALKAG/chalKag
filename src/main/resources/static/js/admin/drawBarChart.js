const dataContainer = document.getElementById("dataContainer");
var signUpCountByAgeGroup = JSON.parse(dataContainer.getAttribute("data-signUpCountByAgeGroup"));
var signUpCountByGenderGroup = JSON.parse(dataContainer.getAttribute("data-signUpCountByGenderGroup"));
var signInCountByYearMonthDate = JSON.parse(dataContainer.getAttribute("data-signInCountByYearMonthDate"));


(function($) {
    "use strict";
    // 차트 인스턴스를 저장할 객체
    window.myCharts = {};

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

        } else if (chartId == "singleLineChart2") {

            //탭선택시 초기 그래프
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
                            console.log("AJAX SUCCESS");
                            console.log(signInCountByYearMonthDate); // 콘솔에 결과 출력
                        } else {
                            console.log("[로그] 데이터 없음");
                        }
                    },
                    error: function(error) {
                        console.log('에러: ' + error);
                    }
                });

                const daysInMonth = getDaysInMonth(selectedYear, selectedMonth);
                const daysArray = Array.from({
                    length: daysInMonth
                }, (_, i) => i + 1);
                console.log(daysArray);
                const monthName = getMonthName(selectedMonth);
                const signInCount = new Array(daysInMonth).fill(0);
                const labels = [];
                for (let day = 1; day <= daysInMonth; day++) {
                    if (day === 1) {
                        labels.push([`${day}`, `${monthName}`]); // 첫 번째 날짜에 월 정보 추가
                    } else {
                        labels.push(`${day}`);
                    }
                }
                signInCountByYearMonthDate.forEach(function(item) {
                    var index = daysArray.indexOf(parseInt(item.date));
                    if (index !== -1) {
                        signInCount[index] = parseInt(item.signInCount);
                    }
                });
                drawLineChart("singleLineChart2", labels, signInCount);
            }

            document.getElementById('year').addEventListener('change', handleSelectChange);
            document.getElementById('month').addEventListener('change', handleSelectChange);

        } else if (chartId == "singleLineChart3") {

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
                "Users", {
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


            var biggerGenderCount = (maleCount >= femaleCount) ? maleCount + 50 : femaleCount + 50;

            var maxTemp = biggerGenderCount + (10 - ((biggerGenderCount % 10 != 0) ? biggerGenderCount % 10 : 10));
            drawBarChart(
                chartId,
                ["Male", "Female"],
                [maleCount, femaleCount],
                "Gender",
                "Users", {
                    beginAtZero: true,
                    min: 0,
                    max: maxTemp,
                    stepSize: maxTemp / (maxTemp / 10)
                }
            )
        }
    });

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
    function drawLineChart(chartId, labels, data) {
        var ctx = document.getElementById(chartId);
        // 기존 차트가 있으면 파괴
        if (window.myCharts && window.myCharts[chartId]) {
            window.myCharts[chartId].destroy();
        }
        // 빈 배열 생성


        // 1에서 50까지의 샘플 데이터 생성 및 배열에 추가
        // 31개의 랜덤 숫자 생성하여 배열에 추가


        // 새 차트 생성
        window.myCharts = window.myCharts || {};
        window.myCharts[chartId] = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
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
                        min: -1, // Y축의 최소값 설정
                        max: 10, // Y축의 최대값 설정
                    }
                }
            }
        });
    }

    function getDaysInMonth(year, month) {
        return new Date(year, month, 0).getDate();
    }

    function getMonthName(monthNumber) {
        const date = new Date(0, monthNumber - 1);
        return date.toLocaleString('ko-KR', {
            month: 'long'
        });
    }

})(jQuery);