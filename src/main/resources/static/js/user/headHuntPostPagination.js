// 필요한 변수 및 함수 선언
var filterData; // 필터 데이터를 저장함는 함수
var loadReviewData; // 데이터를 로드하는 함수
const dataContainer = document.getElementById('postDatasContainer'); // 데이터 컨테이너 요소를 가져옴
const postDatas = JSON.parse(dataContainer.getAttribute('displayReviewData')); // 게시글 데이터를 가져옴 
console.log(postDatas);
var currentPage = 0;        // 첫 페이지
var totalPages = 0;         // 총 페이지

$(document).ready(function() {
    $(document).on("click", "#paginationContainer .page", function(event) {
        event.preventDefault(); // 기본 동작 방지
        var page = $(this).data("page"); // 클릭된 페이지 번호를 가져와 currentPage에 저장
        loadReviewData(page); // 해당 페이지 데이터 로드
        // console.log("[로그] currentPage :" + currentPage); 
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
    
    var displayDatas = postDatas; // 출력할 데이터를 일반 데이터로 초기화
    
    	console.log(displayDatas);
    
    var pageDataSize = 20; // 페이지당 데이터 크기
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
    	div.classList.add('headHuntPostList', 'div');
    
     let innerHTML = ''; // 새로운 내용을 담을 변수
    
        if (pageDatas === null || pageDatas.length <= 0) {
        innerHTML = '<div class="inner"><p>there are no registered posts...</p></div>'; // 데이터가 없을 때 메시지 출력
    } else {
        pageDatas.forEach(function(headHuntPostList) {
            let postContent = headHuntPostList.headHuntPostContent;
            if (postContent.length > 50) {
                postContent = postContent.substring(0, 50) + "...";
            }
            
               innerHTML =  `<div class="inner">
                        <figure>
                            <a href="/headHuntPostSingle/headHuntPostId=${headHuntPostList.headHuntPostId}">
                                <img src="/${headHuntPostList.postImgId}">
                            </a>
                        </figure>
                        <div class="details">
                            <div class="detail">
                                <div class="category">
                                    <p>${headHuntPostList.memberId}</p>
                                </div>
                                <time>${headHuntPostList.headHuntPostDate}</time>
                            </div>
                            <h1><a href="/headHuntPostSingle/headHuntPostId=${headHuntPostList.headHuntPostId}">${headHuntPostList.headHuntPostTitle}</a></h1>
                            <p>직업 : ${headHuntPostList.headHuntPostRole}</p>
                            <p>촬영 컨셉 : ${headHuntPostList.headHuntPostRegion}</p>
                            <p>작업 페이 : ${headHuntPostList.headHuntPostPay}</p>
                            <p>작업 날짜 : ${headHuntPostList.headHuntPostWorkdate}</p>
                            <p id="postContent"></p>
                            <footer>
                            	<!-- 추천수 링크 추후 수정 -->
                                <a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${headHuntPostList.recommendCnt}</div></a>
                                <a class="btn btn-primary more" href="${headHuntPostList.headHuntPostId}">
                                    <div>More</div>
                                    <div><i class="ion-ios-arrow-thin-right"></i></div>
                                </a>
                            </footer>
                    </div>
                </div>`;
    
            });
        }
        
        div.innerHTML = innerHTML;
    }
    // 페이징을 화면에 표시하는 함수
    function displayPagination(page) {
        var paginationContainer = $("#paginationContainer"); // 페이지네이션 컨테이너 요소 가져오기
        paginationContainer.empty(); // 컨테이너 초기화

        var pageSize = 10; // 한 페이지 그룹에 표시할 페이지 수
        var currentGroup = Math.floor((page - 1) / pageSize); // 현재 페이지 그룹
        var startPage = currentGroup * pageSize + 1; // 시작 페이지
        var endPage = Math.min((currentGroup + 1) * pageSize, totalPages); // 끝 페이지

        // 이전 페이지 그룹으로 이동하는 버튼 추가
        if (startPage > 1) {
            var prevGroupPage = startPage - 1;
            var prevGroupLink = "<ul class='pagination'><li class='prev'><a href='#' class='page' data-page='" + prevGroupPage + "'>&laquo; PREV</a></li>";
            paginationContainer.append(prevGroupLink);
        }
        // 페이지 버튼 추가
        for (var i = startPage; i <= endPage; i++) {
            var pageLinkClass = (i === page) ? "page active" : "page";
            var pageLink = "<li class='active'><a href='#' class='" + pageLinkClass + "' data-page='" + i + "'>" + i + "</a></li>";
            paginationContainer.append(pageLink);
        }
        // 다음 페이지 그룹으로 이동하는 버튼 추가
        if (endPage < totalPages) {
            var nextGroupPage = endPage + 1;
            var nextGroupLink = "<li class='next'><a href='#' class='page' data-page='" + nextGroupPage + "'>NEXT &raquo;</a></li></ul>";
            paginationContainer.append(nextGroupLink);
        }
    }
    // 초기 페이지 로드
    loadReviewData(1);
});
