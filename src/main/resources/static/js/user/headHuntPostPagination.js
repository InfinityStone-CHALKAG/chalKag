// 필요한 변수 및 함수 선언
var filterData; // 필터 데이터를 저장함는 함수
var loadReviewData; // 데이터를 로드하는 함수
const dataContainer = document.getElementById('postDatasContainer'); // 데이터 컨테이너 요소를 가져옴
const postDatas = JSON.parse(dataContainer.dataset.displayreviewdata); // 게시글 데이터를 가져옴  
console.log("postDatas : " + postDatas);
var currentPage = 0;        // 첫 페이지
var totalPages = 0;         // 총 페이지

// 현재 시간
const now = new Date();


$(document).ready(function() {
	
// 페이징을 화면에 표시하는 함수
function displayPagination(page) {

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
	
$("#paginationContainer").on("click", "a", function(event) {
    event.preventDefault(); // 기본 동작 방지
    var page = $(this).data("page"); // 클릭된 페이지 번호를 가져와 currentPage에 저장
    window.scrollTo(0, 0);
    loadReviewData(page, postDatas); // 해당 페이지 데이터 로드
     if (isFiltered) {
    console.log("필터진입");
     loadReviewData(page, filterData); // 필터링된 데이터가 있으면 필터링된 데이터 사용
    console.log(postDatas);
}
});


// 데이터를 로드하는 함수 정의
loadReviewData = function(loadPage, dataList) {
  
 
  
    console.log("loadPage : " + loadPage);
    
    var displayDatas = dataList || []; // 출력할 데이터를 일반 데이터로 초기화
    
    var pageDataSize = 10; // 페이지당 데이터 크기
    var totalSize = displayDatas.length; // 전체 데이터 크기
    totalPages = Math.ceil(totalSize / pageDataSize); // 전체 페이지 수 계산
    console.log(totalPages);

    var startIndex = (loadPage - 1) * pageDataSize; // 시작 인덱스 계산
    var endIndex = Math.min(startIndex + pageDataSize, totalSize); // 끝 인덱스 계산

    var currentPageDatas = displayDatas.slice(startIndex, endIndex); // 현재 페이지에 나타낼 데이터 

    	displayReviewData(currentPageDatas); // 데이터 표시 함수 호출
    	
    	console.log("currentPageDatas : " + currentPageDatas);

        displayPagination(loadPage);
 
}


	
 // 데이터를 화면에 표시하는 함수
    function displayReviewData(pageDatas) {
		console.log("pageDatas" + pageDatas);
        const div = document.getElementById('postDatasContainer');
    	div.classList.add('headHuntPostList', 'div');
    let innerHTML = ''; // 새로운 내용을 담을 변수
		console.log("pageDatas.legnth"+ pageDatas.length);
    
    if (pageDatas.length <= 0) {

        innerHTML = '<div class="inner"><h3 style="margin-left:1.5%;">there are no registered posts...</h3></div>'; // 데이터가 없을 때 메시지 출력
    } else {
        pageDatas.forEach(function(headHuntPostList) {
			
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
			
		    
               innerHTML +=  `
               	<article class="col-md-12 article-list">
               		<div class="inner">
                        <figure>
                        	<a href="/headHuntPostSingle?headHuntPostId=${headHuntPostList.headHuntPostId}">
                               <img src="/postImg/${headHuntPostList.postImgName}"  style="width: 100%; height: 100%; object-fit: cover;">
                            </a>
                        </figure>
                        <div class="details">
                            <div class="detail">
                                <div class="category">
                                    <a href="memberPage?memberId=${headHuntPostList.memberId}" style="font-size:14px; font-weight:600;">${headHuntPostList.memberNickname}</a>
                                </div>
                                	 &nbsp;&nbsp;<time>${timeString}</time>
                            </div>
                            <h1 style="width: 522.5px; max-height: 56px; overflow: hidden; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;"><a href="/headHuntPostSingle?headHuntPostId=${headHuntPostList.headHuntPostId}">${headHuntPostList.headHuntPostTitle}</a></h1>
                             <p style="width: 522.5px; max-height: 52px; overflow: hidden; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;">${headHuntPostList.headHuntPostContent}</p>
                            <footer>
                                <a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${headHuntPostList.recommendCnt}</div></a>
                                <a class="btn btn-primary more" href="/headHuntPostSingle?headHuntPostId=${headHuntPostList.headHuntPostId}">
                                    <div>More</div>
                                    <div><i class="ion-ios-arrow-thin-right"></i></div>
                                </a>
                            </footer>
                    	</div>
                	</div>
                </article>`
                ;
            });
        }
        
        div.innerHTML = innerHTML;
    }

   
    // 초기 페이지 로드
    loadReviewData(1, postDatas);
    

});
