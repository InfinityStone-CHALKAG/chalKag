<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="webReply"%>

	<div class="reply-list">

			<c:forEach var="replyList" items="${replyList}">
				<!-- 댓글 내용 출력 -->
				<div class="item">
					<div class="user">
					
						<!-- 회원 프로필 이미지 -->
						<figure>
							<img src="images/img01.jpg">
						</figure>
						
						<div class="details">
							<h5 class="title">${replyList.memberId}
								<c:if test="${member == null || member != replyList.memberId}">
									<a href="/memberPage?memberId=${replyList.memberId}">${replyList.memberId}</a>
								</c:if>
								<c:if test="${member == replyList.memberId}">
									<a href="/myPage?memberId=${member}">${replyList.memberId}</a>
								</c:if>
							</h5>
							<div class="time"></div>
							<div class="description">${replyList.replyContent}</div>
							
							<!-- 답글 달기 -->
							<footer style="display: flex;">
								<c:if test="${member != null}">
									<input class="btn btn-primary btn-rounded" type="button" value="Reply" onclick="toggleReplyWrite()"/>
										<!-- 답글 입력 -->
										<div class="updateContents" id="writeReplyForm" style="display: none; text-align: center;">
											<form action="/writeReply" method="post" class="row">
				   							 <input type="hidden" name="commentId" value="${commentIdList.commentId}">
												<div class="form-group col-md-12">
													<label for="message">Reply <span class="required"></span></label>
													<textarea class="form-control" id="replyContent" name="replyContent"
														placeholder="Write your Reply ..."></textarea>
												</div>
												<div class="form-group col-md-12">
													<button class="btn btn-primary">Send Reply</button>
												</div>
											</form>
										</div>									
								</c:if>
								<!-- 미 로그인 시 경고 버튼 -->
								<c:if test="${member == null}">
									<input  class="btn btn-primary btn-rounded"  type="button" value="Reply" onclick="message()" />
								</c:if>
								
								<!-- 작성자가 자기 댓글을 수정할 경우 -->
								<c:if test="${replyList.memberId == member}">
									<input class="btn btn-primary btn-rounded" type="button"
										value="Reply" id="replyMoreBtn" onclick="toggleReply()">
										
									<input class="btn btn-primary btn-rounded" type="button"
										value="Update" id="replyUpdateBtn" onclick="toggleUpdate()">
										
									<input class="btn btn-primary btn-rounded" type="button"
										value="Delete" id="replyDeleteBtn" onclick="toggleDelete()">
								</c:if>
								
							</footer>
						</div>
					</div>
				</div>
			</c:forEach>
	</div>
	
	
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	function toggleReplyWrite() {
		$("#writeReplyForm").toggle(); // jQuery를 사용하여 토글
	}

	function message() {
		swal("fail", "로그인 후 이용해주세요.", "error", {
			button : "OK",
		});
	}

	function toggleReplies(replyId) {
		var replyList = document.getElementById("replyList-" + replyId);
		if (replyList.style.display === "none") {
			replyList.style.display = "block";
		} else {
			replyList.style.display = "none";
		}
	}

	//현재 시간
	const now = new Date();

	function toggleEdit(replyId, event) {

		// 클릭된 요소
		var clickedElement = $(event.target);

		// 수정을 클릭한 경우
		if (clickedElement.hasClass('updateContents')) {
			// '수정'인 경우
			if (clickedElement.val() === 'replyMoreBtn') {
				// '수정'을 '저장'으로 변경
				clickedElement.text('Save');

				// 해당 댓글Div에 있는 내용을 divContent에 저장
				var divContent = $('#reviewContents_' + reviewNum).text();

				 // 텍스트 영역 엘리먼트 생성
	            var textarea = $('<textarea>').val(divContent.trim()).attr('rows', 5);

	            // div 엘리먼트의 내용을 텍스트 영역으로 완전히 대체
	            var divElement = $('#replyContent_' + replyId);

	            if (divElement.length) {
	                // 선택된 div 요소를 비우고, 그 안에 새로운 textarea 요소를 추가
	                divElement.empty().append(textarea);
	            }
			}
			// '수정'이 아닌 경우
			else {

				// 텍스트 영역에서 업데이트된 내용 가져오기
				var updatedContents = $(
						'#replyContent_' + replyId + ' textarea').val();

				// 유효성 검사: 공백이나 내용이 없는 경우 저장하지 않음
				if (updatedContents.trim() === '') {
					alert('내용을 입력하세요.'); // 또는 다른 사용자 피드백 방식으로 변경
					return; // 저장 중단
				}

				// AJAX 요청 수행
				$.ajax({
					url : "/updateReply", // 실제 서버 엔드포인트로 변경
					type : "POST", // 요청 방식 (GET, POST 등)
					data : {
						// update를 하기 위한 요소들을 보냄
						'replyId' : replyId,
						'replyContent' : updatedContents
					},
					dataType : 'text',
					success : function(response) {
						// response 확인
						// console.log('서버 응답:' + response);

						// 성공적으로 업데이트된 경우
						// div 엘리먼트의 내용을 업데이트된 내용으로 교체
						var replyContentsDiv = $('#replyContent_'
								+ replyId);
						if (replyContentsDiv.length) {
							replyContentsDiv.text(response);
							// 현재 db에 저장된 데이터
							// 서버에서 응답을 줄 때 인자인 Response에 태워서 보내줘야함
						}
						// '저장'을 '수정'으로 변경

						clickedElement.text('Update');

					},
					error : function(error) {
						console.error('에러 발생:', error);
						// 에러 처리 로직 추가
					}
				});

				// 이벤트 전파 막기
				event.stopPropagation();
			}
		}
	}

</script>
	