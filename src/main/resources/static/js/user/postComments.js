document.addEventListener('DOMContentLoaded', function() {
    let isLoading = false;

    // 무한 스크롤 구현을 위한 스크롤 이벤트 리스너 추가
    window.addEventListener('scroll', () => {
        // 페이지 끝에 도달했는지 확인
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            loadMoreComments();
        }
    });

    function loadMoreComments() {
        if (isLoading) return; // 중복 로딩 방지
        isLoading = true;

        // AJAX 요청을 통해 댓글 데이터 가져오기 (여기서는 예시로 setTimeout 사용)
        setTimeout(() => {
            // 서버로부터 받아온 댓글 데이터를 기반으로 DOM 업데이트
            const commentsContainer = document.querySelector('.comment-list');
            const newItem = document.createElement('div');
            newItem.className = 'item';
            newItem.innerHTML = `
                <div class="user">                                
                    <figure>
                        <img src="/${}">
                    </figure>
                    <div class="details">
                        <h5 class="name">새로운 댓글</h5>
                        <div class="time"></div>
                        <div class="description">
                            여기에 새로운 댓글 내용이 들어갑니다. <a href="#">링크</a>도 추가할 수 있습니다.
                        </div>
                        <footer>
                            <a href="#">Reply</a>
                        </footer>
                    </div>
                </div>`
                ;
            commentsContainer.appendChild(newItem);

            isLoading = false; // 로딩 상태 해제
        }, 1000); // 실제 구현에서는 서버로부터 데이터를 받아오는 AJAX 호출로 대체
    }
});