// 필요한 변수 및 함수 선언
var filterData; // 필터 데이터를 저장함는 함수
var loadReviewData; // 데이터를 로드하는 함수
const dataContainer = document.getElementById('postDatasContainer'); // 데이터 컨테이너 요소를 가져옴
const postDatas = JSON.parse(dataContainer.dataset.displayreviewdata); // 게시글 데이터를 가져옴 
console.log(postDatas);
var currentPage = 0;        // 첫 페이지
var totalPages = 0;         // 총 페이지

// 현재 시간
const now = new Date();


$(document).ready(function() {
	
	   // 페이징을 화면에 표시하는 함수
  function displayPagination(page) {
    console.log("page : " + page);

    var paginationContainer = $("#paginationContainer"); // 페이지네이션 컨테이너 요소 가져오기
    paginationContainer.empty(); // 컨테이너 초기화

    var pageSize = 10; // 한 페이지 그룹에 표시할 페이지 수
    var currentGroup = Math.floor((page - 1) / pageSize); // 현재 페이지 그룹
    console.log("currentGroup : " + currentGroup);
    var startPage = currentGroup * pageSize + 1; // 시작 페이지
    console.log("startPage : " + startPage);

    var endPage = Math.min((currentGroup + 1) * pageSize, totalPages); // 끝 페이지
    console.log("endPage : " + endPage);

    // 이전 페이지 그룹으로 이동하는 버튼 추가
    if (startPage > 1) {
        var prevGroupPage = startPage - 1;
        var prevGroupLink = "<li class='prev'><a href='#' data-page='" + prevGroupPage + "'><i class='ion-ios-arrow-left'></i></a></li>";
        paginationContainer.append(prevGroupLink);
    }

// 페이지 버튼 추가
 for (var i = startPage; i <= endPage; i++) {
        var pageLinkClass = (i === page) ? "active" : ""; // 현재 페이지면 "active" 클래스 추가
        var pageLink = "<li class='" + pageLinkClass + "'><a href='#' data-page='" + i + "'>" + i + "</a></li>";
        paginationContainer.append(pageLink);
    }
    
    // 다음 페이지 그룹으로 이동하는 버튼 추가
    if (endPage < totalPages) {
        var nextGroupPage = endPage + 1;
        var nextGroupLink = "<li class='next'><a href='#' data-page='" + nextGroupPage + "'><i class='ion-ios-arrow-right'></i></a></li>";
        paginationContainer.append(nextGroupLink);
    }
}
	
    // 페이징 버튼 클릭 이벤트 리스너
    // 페이지네이션 버튼 클릭 이벤트
	// paginationContainer에는 처음에는 .page 클래스를 가진 요소가 없지만, 
	// displayPagination 함수에서 동적으로 생성
	// displayPagination 함수는 서버로부터 받아온 페이지네이션 데이터를 기반으로 페이지네이션 버튼을 생성하며,
	// 이 때 각 버튼에는 .page 클래스와 data-page 속성이 부여 됨
	// data-page 속성은 해당 버튼이 클릭될 때 이동해야 할 페이지 번호를 저장
 $("#paginationContainer").on("click", "a", function(event) {
    event.preventDefault(); // 기본 동작 방지
    var page = $(this).data("page"); // 클릭된 페이지 번호를 가져와 currentPage에 저장
    loadReviewData(page); // 해당 페이지 데이터 로드
});

// 데이터를 로드하는 함수 정의
loadReviewData = function(loadPage) {
    var dataToSend = {
        'postDatas': JSON.stringify(postDatas),
        'page': loadPage,
    };

    if (isFiltered) {
        dataToSend.filterData = JSON.stringify(filterData); // 필터링된 데이터가 있으면 필터링된 데이터 사용
    }
    
    var displayDatas = postDatas || []; // 출력할 데이터를 일반 데이터로 초기화
    
    	console.log(displayDatas);
    
    var pageDataSize = 10; // 페이지당 데이터 크기
    var totalSize = displayDatas.length; // 전체 데이터 크기
    totalPages = Math.ceil(totalSize / pageDataSize); // 전체 페이지 수 계산

    var startIndex = (loadPage - 1) * pageDataSize; // 시작 인덱스 계산
    var endIndex = Math.min(startIndex + pageDataSize, totalSize); // 끝 인덱스 계산

    var currentPageDatas = displayDatas.slice(startIndex, endIndex); // 현재 페이지에 나타낼 데이터 

    	displayReviewData(currentPageDatas); // 데이터 표시 함수 호출

        displayPagination(loadPage);
 
}
    // 데이터를 화면에 표시하는 함수
    function displayReviewData(pageDatas) {
		console.log(pageDatas);
        const div = document.getElementById('postDatasContainer');
    	div.classList.add('jobHuntPostList', 'div');
    
     let innerHTML = ''; // 새로운 내용을 담을 변수
    
     if (pageDatas === null  ||  pageDatas.length <= 0) {
        innerHTML = '<div class="inner"><p>there are no registered posts...</p></div>'; // 데이터가 없을 때 메시지 출력
    } else {
        pageDatas.forEach(function(jobHuntPostList) {
			
				 // 글 작성 시간을 Date 객체로 변환합니다.
		    const postDate = new Date(headHuntPostList.headHuntPostDate);
		    // 현재 시간과 글 작성 시간의 차이를 밀리초 단위로 계산합니다.
		    const diff = now - postDate;
		
		    // 계산된 시간 차이를 분, 시간, 일 단위로 변환합니다.
		    const mins = Math.floor(diff / 60000);
		    const hours = Math.floor(diff / 3600000);
		    const days = Math.floor(diff / 86400000);
		
		    let timeString = '';
		    if (days >= 1) {
		        // 1일 이상 차이날 경우, 날짜를 표시합니다.
		        timeString = postDate.toLocaleDateString();
		    } else if (hours >= 1) {
		        // 1~24시간 사이일 경우, 시간으로 표시합니다.
		        timeString = `${hours}시간 전`;
		    } else {
		        // 1시간 미만일 경우, 분으로 표시합니다.
		        timeString = `${mins}분 전`;
		    }
			
            // 텍스트 길이 제한 적용
		    let postContent = headHuntPostList.headHuntPostContent;
		    if (postContent.length > 30) {
		        postContent = postContent.substring(0, 30) + "...";
		    }
            
               innerHTML +=  `
                <article class="col-md-12 article-list">
               		<div class="inner">
                        <figure>
                            <a href="/jobHuntPostSingle?jobHuntPostId=${jobHuntPostList.jobHuntPostId}">
                                <img src="/postImg/${jobHuntPostList.postImgId}">
                            </a>
                        </figure>
                        <div class="details">
                            <div class="detail">
                                <div class="category" style="display:flex;">
                                    <p>${jobHuntPostList.memberNickname}</p>
	                                <time style="width: 100px; text-align: center; padding-top: 5px;">${timeString}</time>
                                </div>
                            </div>
                            <h1><a href="/jobHuntPostSingle?jobHuntPostId=${jobHuntPostList.jobHuntPostId}">${jobHuntPostList.jobHuntPostTitle}</a></h1>
                              <p class="postContentText">${postContent}</p>
                            <footer>
                            	<!-- 추천수 링크 추후 수정 -->
                                <a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${jobHuntPostList.recommendCnt}</div></a>
                                <a class="btn btn-primary more" href="/jobHuntPostSingle?jobHuntPostId=${jobHuntPostList.jobHuntPostId}">
                                    <div>
										More
									</div>
                                    <div><i class="ion-ios-arrow-thin-right"></i></div>
                                </a>
                            </footer>
                    </div>
                </div>
              </article>`;
            });
        }
        
        div.innerHTML = innerHTML;
    }
 
    // 초기 페이지 로드
    loadReviewData(1);
});
