// 필요한 변수 및 함수 선언
var filterData; // 필터 데이터를 저장함는 함수
var loadReviewData; // 데이터를 로드하는 함수
const dataContainer = document.getElementById('postDatasContainer'); // 데이터 컨테이너 요소를 가져옴
const postDatas = dataContainer.getAttribute('displayReviewData'); // 게시글 데이터를 가져옴 
var currentPage;        // 첫 페이지
var totalPages;         // 총 페이지

$(document).ready(function() {
    // 페이징 버튼 클릭 이벤트 리스너
    // 페이지네이션 버튼 클릭 이벤트
	// paginationContainer에는 처음에는 .page 클래스를 가진 요소가 없지만, 
	// displayPagination 함수에서 동적으로 생성
	// displayPagination 함수는 서버로부터 받아온 페이지네이션 데이터를 기반으로 페이지네이션 버튼을 생성하며,
	// 이 때 각 버튼에는 .page 클래스와 data-page 속성이 부여 됨
	// data-page 속성은 해당 버튼이 클릭될 때 이동해야 할 페이지 번호를 저장
    $(document).on("click", "#paginationContainer .page", function(event) {
        event.preventDefault(); // 기본 동작 방지
        currentPage = $(this).data("page"); // 클릭된 페이지 번호를 가져와 currentPage에 저장
        loadReviewData(currentPage); // 해당 페이지 데이터 로드
        // console.log("[로그] currentPage :" + currentPage); 
});
    // 데이터를 로드하는 함수 정의
    loadReviewData = function(currentPage) {
        var displayDatas = postDatas; // 출력할 데이터를 일반 데이터로 초기화
        if (isFiltered) {
            displayDatas = filterData; // 필터링된 데이터가 있으면 필터링된 데이터 사용
        }

        var pageDataSize = 20; // 페이지당 데이터 크기
        var totalSize = displayDatas.length; // 전체 데이터 크기
        totalPages = Math.ceil(totalSize / pageDataSize); // 전체 페이지 수 계산

        var startIndex = (currentPage - 1) * pageDataSize; // 시작 인덱스 계산
        var endIndex = Math.min(startIndex + pageDataSize, totalSize); // 끝 인덱스 계산

        var currentPageDatas = displayDatas.slice(startIndex, endIndex); // 현재 페이지에 나타낼 데이터 

        displayReviewData(currentPageDatas); // 데이터 표시 함수 호출
        displayPagination(currentPage); // 페이지네이션 표시 함수 호출
    }

      // 데이터를 화면에 표시하는 함수
    function displayReviewData(pageDatas) {
		console.log(pageDatas);
        const div = document.getElementById('postDatasContainer');
    	div.classList.add('marketPostList', 'div');
    
     let innerHTML = ''; // 새로운 내용을 담을 변수
    
        if (pageDatas === null || pageDatas.length <= 0) {
        innerHTML = '<div class="inner"><p>there are no registered posts...</p></div>'; // 데이터가 없을 때 메시지 출력
    } else {
        pageDatas.forEach(function(marketPostList) {
            let postContent = marketPostList.marketPostContent;
            if (postContent.length > 50) {
                postContent = postContent.substring(0, 50) + "...";
            }
            
               innerHTML =  `<div class="inner">
                        <figure>
                            <a href="/marketPostSingle/marketPostId=${marketPostList.marketPostId}">
                                <img src="/${marketPostList.postImgId}">
                            </a>
                        </figure>
                        <div class="details">
                            <div class="detail">
                                <div class="category">
                                    <p>${marketPostList.memberId}</p>
                                </div>
                                <time>${marketPostList.marketPostDate}</time>
                            </div>
                            <h1><a href="/marketPostSingle/marketPostId=${marketPostList.marketPostId}">${marketPostList.marketPostTitle}</a></h1>
                            <p>가격 : ${marketPostList.marketPostPrice}</p>
                            <p>상품 종류 : ${marketPostList.marketPostPrice}</p>
                            <p>제조사 : ${marketPostList.marketPostPrice}</p>
                            <p>판매 상태 : ${marketPostList.marketPostPrice}</p>
                            <p id="postContent"></p>
                            <footer>
                            	<!-- 추천수 링크 추후 수정 -->
                                <a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${marketPostList.recommendCnt}</div></a>
                                <a class="btn btn-primary more" href="${marketPostList.marketPostId}">
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


