
var isFiltered = false; // 필터링된 게시글 데이터를 저장할 변수
var minPay;		// 최소 페이
var maxPay;		// 최소 페이
var role;		// 직업
var region;		// 지역
var startDate;	// 작업 시작일 
var endDate;	// 작업 종료일
var concept;	// 촬영컨셉
var minDate;
var maxDate;

document.getElementById('minPay').addEventListener('input', handleFilterChange);
document.getElementById('maxPay').addEventListener('input', handleFilterChange);
document.getElementById('role').addEventListener('input', handleFilterChange);
document.getElementById('region').addEventListener('input', handleFilterChange);
document.getElementById('startDate').addEventListener('input', handleFilterChange);
document.getElementById('endDate').addEventListener('input', handleFilterChange);
document.getElementById('concept').addEventListener('input', handleFilterChange);

document.getElementById('filterReset').addEventListener('click', function() {
			// 검색어 초기화
							document.getElementById('searchInput').value = '';
					
							// 검색 필드 초기화
							document.getElementById('searchField').selectedIndex = 0;
					
							// 날짜 필터 초기화

							document.getElementById('Anytime').checked = true; 
					
							// 직업 필터 초기화
							document.getElementById('role').selectedIndex = 0;
					
							// 지역 필터 초기화
							document.getElementById('region').selectedIndex = 0;
					
							// Pay 필터 초기화
							document.getElementById('minPay').value = 1;
							document.getElementById('maxPay').value = 1;
					
							// 작업 날짜 필터 초기화
							document.getElementById('startDate').value = '';
							document.getElementById('endDate').value = '';
					
							// 촬영 컨셉 필터 초기화
							document.getElementById('concept').selectedIndex = 0;
    
        
        // 필요하다면, 변수 업데이트 및 데이터 요청 로직도 여기서 호출
        updateVariables(); // 필터링 및 정렬에 사용되는 변수들 업데이트
        performAjaxRequest(); // 필터링된 데이터 요청
    
});

$(document).ready(function() {
  // 검색 필드와 입력값, 정렬 순서 업데이트 이벤트 리스너 추가
  $('#searchField, #searchInput, #sortOrder').change(function() {
    updateVariables();
    performAjaxRequest();
  });
});

// 날짜 라디오 버튼 이벤트 리스너 추가
$('input[type=radio][name=date]').change(function() {
  const today = new Date();
  const lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7);
  const lastMonth = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());


  switch(this.value) {
      case 'Today':
          minDate = formatDate(today);
          maxDate = formatDate(today);
          break;
      case 'Last Week':
          minDate = formatDate(lastWeek);
          maxDate = formatDate(today);
          break;
      case 'Last Month':
          minDate = formatDate(lastMonth);
          maxDate = formatDate(today);
          break;
      default:
          // "Anytime"이 선택된 경우, minDate와 maxDate를 초기화합니다.
          minDate = '';
          maxDate = '';
  }

  updateVariables(); // 필터링 및 정렬에 사용되는 변수들 업데이트
  performAjaxRequest(); // 필터링된 데이터 요청
});


// 상품 필터 검색
function handleFilterChange() {
    updateVariables(); // 필터링 및 정렬에 사용되는 변수들 업데이트
    performAjaxRequest(); // 필터링된 데이터 요청
}


// 조회수, 추천수로 정렬 할 수 있게 jsp에서 받아온 값을 보내준다.
function sortItems(type) {
    $('#sortOrder').val(type); // 정렬 순서 선택에 따라 sortOrder 업데이트
    updateVariables(); // 변수 업데이트
    performAjaxRequest(); // AJAX 요청으로 데이터 업데이트
}

// 날짜를 YYYY-MM-DD 형식의 문자열로 포맷하는 함수
function formatDate(date) {
  let d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

  if (month.length < 2) 
      month = '0' + month;
  if (day.length < 2) 
      day = '0' + day;

  return [year, month, day].join('-');
}

// 날짜와 카테고리에 따른 변수 업데이트 함수
function updateVariables() {  
	
  	// 검색 카테고리와 입력값
  	searchField = $('#searchField').val();
  	searchInput = $('#searchInput').val();
  	// 상품 필터
	minPay = $('minPay').val();
	maxPay = $('maxPay').val();
	role = $('role').val();
	region = $('region').val();
	startDate = $('startDate').val(); 
	endDate = $('enddate').val(); 
	concept = $('concept').val(); 
	
   // 정렬 순서
  sortOrder = $('#sortOrder').val();
  
  // 전역 변수나 필터링에 사용될 변수에 선택된 카테고리들, 검색 카테고리, 입력값, 정렬 순서 저장
}

// AJAX 요청 수행 함수
function performAjaxRequest() {
    // 서버에 보낼 데이터 준비. 예를 들어, minDate, maxDate
    const requestData = {
    minDate: minDate,
    maxDate: maxDate,
    searchField: searchField,
    searchInput: searchInput,
    sortOrder: sortOrder,
    minPay : minPay,
    maxPay : maxPay,
    role : role, 
    region : region,
    startDate : startDate,
    endDate : endDate,
    concept : concept
    };

    // jQuery를 사용한 AJAX 요청
    $.ajax({
        url: '/headHuntPostSearchList', // 서버의 엔드포인트 URL
        type: 'GET', // 또는 'POST', 서버의 요구 사항에 따라
        data: requestData, // 서버에 보낼 데이터
        success: function(filterData) {
            // 성공 시, 응답 처리.  검색 결과를 화면에 표시
            if (filterData != null) { // filterDatas가 존재하는 경우
                window.filteredData = filterData; // 서버에서 받은 데이터를 변수에 할당
                isFiltered = true; // 데이터가 존재하므로 isFiltered를 true로 설정
                loadReviewData(1);
            }
        },
        error: function(xhr, status, error) {
            // 오류 처리
            console.error("An error occurred: " + status + ", " + error);
        }
    });
}
