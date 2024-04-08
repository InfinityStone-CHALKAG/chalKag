const today = new Date(); // 현재날짜 설정

console.log("today : " + today);

var isFiltered = false; // 필터링된 게시글 데이터를 저장할 변수
var minPay = 0;		// 최소 페이
var maxPay = 0;		// 최소 페이
var role = "";		// 직업
var region = "";		// 지역
var concept = "";		// 촬영컨셉
var searchField = "";		
var searchInput="";
var startDate =	today;// 작업 시작일
var endDate = today;// 작업 종료일
var minDate = "";// 검색 시작일 설정
var maxDate = "";// 오늘날짜
console.log('초기화 : ' + startDate.toDateString());
console.log('초기화 : ' + endDate.toDateString());

console.log(document.getElementById('startDate'));
$(document).ready(function() {
  // 검색 필드와 입력값, 정렬 순서 업데이트 이벤트 리스너 추가
  $('#searchField, #searchInput, #sortOrder').change(function() {
    updateVariables();
    performAjaxRequest();
  });
  
	document.getElementById('minPay').addEventListener('mouseup', handleFilterChange);
	
	document.getElementById('maxPay').addEventListener('mouseup', handleFilterChange);
	
	document.getElementById('role').addEventListener('change', handleFilterChange);
	
	document.getElementById('region').addEventListener('change', handleFilterChange);
	
	document.getElementById('startDate').addEventListener('input', handleFilterChange);
	
	document.getElementById('endDate').addEventListener('input', handleFilterChange);
	
	document.getElementById('concept').addEventListener('change', handleFilterChange);

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
	
	// 날짜 라디오 버튼 이벤트 리스너 추가
	 $("input[type='radio'][name='headHuntPostDate']").on('ifChanged', function() {
		  if ($(this).prop('checked')) {
	  const lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7);
	  const lastMonth = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	  console.log("이건뭘까~" + $(this).val());
	
	
	   switch($(this).val()) {
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
	  
	  console.log("minDate : " + minDate);
	  console.log("manDate : " + maxDate);
	   updateVariables();
	  performAjaxRequest(); // 필터링된 데이터 요청
	  }
	});
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
  	
  		console.log("searchInput : " + searchInput);
  		
	minPay = $('#minPay').val();
	
	  	console.log("minPay : " + minPay);
	  	
	maxPay = $('#maxPay').val();
	
	  	console.log("maxPay : " + maxPay);
	  	
	role = $('#role').val();
	
		console.log("role : " + role);
		
	region = $('#region').val();
	
		console.log("region : " + region);
		
startDate = $('#startDate').val(); 
		console.log("startDate : " + startDate);
		
	endDate = $('#endDate').val(); 
	
		console.log("endDate : " + endDate);
		
	concept = $('#concept').val(); 
	
		console.log("concept : " + concept);
		
   // 정렬 순서
  sortOrder = $('#sortOrder').val();
  
  // 전역 변수나 필터링에 사용될 변수에 선택된 카테고리들, 검색 카테고리, 입력값, 정렬 순서 저장
}

// AJAX 요청 수행 함수
function performAjaxRequest() {
    // 서버에 보낼 데이터 준비.
    const requestData = {
  	fromday: minDate,
	today: maxDate,
    searchField: searchField,
    searchInput: searchInput,
    sortOrder: sortOrder,
    minPay : minPay,
    maxPay : maxPay,
    headHuntPostRole : role, 
    headHuntPostRegion : region,
    startWorkDate : startDate,
    endWorkDate : endDate,
    headHuntPostConcept : concept
    };
    console.log("requestData!!!!!!!!!!!!!!!!!!" + JSON.stringify(requestData));

    // jQuery를 사용한 AJAX 요청
    $.ajax({
        url: '/headHuntPostFilterSearch', // 서버의 엔드포인트 URL
        type: 'post', // 또는 'POST', 서버의 요구 사항에 따라
        data: requestData, // 서버에 보낼 데이터
        dataType: 'json',
        success: function(filterData) {
			console.log("필터 AJAX 콘솔진입!!!!!!!!!" + JSON.stringify(filterData));
			
            // 성공 시, 응답 처리.  검색 결과를 화면에 표시
            if (filterData != null) { // filterDatas가 존재하는 경우
            	console.log("filterData!!!!!!!!!!:" + filterData);
                window.filterData = filterData; // 서버에서 받은 데이터를 변수에 할당
                isFiltered = true; // 데이터가 존재하므로 isFiltered를 true로 설정
                loadReviewData(1, filterData);
            }
        },
        error: function(xhr, status, error) {
            // 오류 처리
            console.error("An error occurred: " + status + ", " + error);
        }
    });
}
