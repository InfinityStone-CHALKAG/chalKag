<%@ tag language="java" pageEncoding="UTF-8"
	import="infinitystone.chalKag.biz.comment.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="webComments"%>

<div class="comments">
	<h5>${fn:length(commentList)}Responses</h5>
	<div style="text-align: center;">
		<!-- 로그인 했을때 -->
		<c:if test="${member != null}">
			<input style="display: inline-block;"
				class="btn btn-primary btn-rounded" id="toggleWriteBtn1"
				type="button" value="Write Responses" onclick="toggleWrite(this)">
		</c:if>
		<!-- 비 로그인 유저일때 -->
		<c:if test="${member == null}">
			<input style="display: inline-block;"
				class="btn btn-primary btn-rounded" id="toggleWriteBtn2"
				type="button" value="Write Responses" onclick="message()">
		</c:if>
	</div>
	<br>
	<!-- 댓글 입력 -->
	<div id="writeResponseForm" style="display: none; text-align: center;">
		<form id="commentForm" action="/writeComment" method="post"
			class="row">
			<c:if test="${not empty headHuntPostSingle}">
				<input type="hidden" name="postId"
					value="${headHuntPostSingle.headHuntPostId}">
			</c:if>
			<c:if test="${not empty jobHuntPostSingle}">
				<input type="hidden" name="postId"
					value="${jobHuntPostSingle.jobHuntPostId}">
			</c:if>
			<c:if test="${not empty freePostSingle}">
				<input type="hidden" name="postId"
					value="${freePostSingle.freePostId}">
			</c:if>
			<c:if test="${not empty marketPostSingle}">
				<input type="hidden" name="postId"
					value="${marketPostSingle.marketPostId}">
			</c:if>

			<div class="form-group col-md-12">
				<label for="message">Response <span class="required"></span></label>
				<textarea class="form-control" name="commentContent"
					placeholder="Write your response ..."></textarea>
			</div>
			<div class="form-group col-md-12">
				<button class="btn btn-primary">Send Response</button>
			</div>
		</form>
	</div>

	<div class="comment-list">
		<!-- 댓글이 없을 경우 출력 문구 -->
		<c:if test="${commentList == null || fn:length(commentList) <= 0}">
			<h5 style="text-align: center;">There are no comments. Be the first to leave a comment!</h5>
		</c:if>

		<!-- 댓글이 있을 경우 목록 출력 -->
		<c:if test="${fn:length(commentList) > 0 }">
			<c:forEach var="commentList" items="${commentList}">

				<!-- 댓글 내용 출력 -->
				<div class="item">
					<div class="user">

						<!-- 회원 프로필 이미지 -->
						<figure>
							<img src="profileImg/${commentList.profileImgName}">
						</figure>

						<!-- 댓글 내용 출력 -->
						<div class="details">
							<!-- 댓글 제목 -->
							<h5 class="title">${commentList.memberNickname}
								<c:if test="${member == null || member != commentList.memberId}">
									<a href="/memberPage?memberId=${commentList.memberId}">${commentList.memberId}</a>
								</c:if>
								<c:if test="${member == commentList.memberId}">
									<a href="/myPage?memberId=${member}">${commentList.memberId}</a>
								</c:if>
							</h5>
							<div class="time"></div>
							<div class="description"
								id="commentContent_${commentList.commentId}">${commentList.commentContent}</div>

							<!-- 답글 달기 -->
							<footer style="display: flex; flex-wrap: wrap;">
								<!-- 미 로그인 시 답글 버튼에 경고 버튼 -->
								<c:if test="${member == null}">
									<input class="btn btn-primary btn-rounded" type="button"
										style="width: 25%;" value="Reply" onclick="message()" />
								</c:if>

								<!-- 작성자가 자기 댓글을 수정 또는 삭제를 할 경우 -->
								<c:if test="${commentList.memberId == member}">
									<!-- 수정 버튼 -->
									<input class="btn btn-primary btn-rounded" type="button"
										style="width: 25%; margin-right: 10px;" value="Update"
										id="commentUpdateBtn_${commentList.commentId}"
										onclick="toggleUpdate(${commentList.commentId}, '${commentList.commentContent}')">
									<!-- 삭제 버튼 -->
									<input class="btn btn-primary btn-rounded" type="button"
										style="width: 25%; margin-right: 10px;" value="Delete"
										id="commentDeleteBtn_${commentList.commentId}"
										onclick="toggleDelete('${commentList.commentId}')">
								</c:if>

								<!-- 답글 작성 버튼 클릭 이벤트 -->
								<c:if test="${member != null}">
									<input class="btn btn-primary btn-rounded" type="button"
										style="width: 25%;" value="Reply"
										onclick="toggleReplyWrite(this)"
										data-comment-id="${commentList.commentId}" />
									<!-- 답글 입력 -->
									<div id="writeReplyForm_${commentList.commentId}"
										style="display: none; flex-basis: 100%;">
										<form id="replyForm" action="/writeReply" method="post"
											class="row">
											<input type="hidden" name="commentId"
												value="${commentList.commentId}">
											<div class="form-group col-md-12" style="text-align: left;">
												<label for="message">Reply <span class="required"></span></label>
												<textarea class="form-control" id="replyContent"
													name="replyContent" placeholder="Write your Reply ..."></textarea>
											</div>
											<div class="form-group col-md-12" style="text-align: right;">
												<button class="btn btn-primary reply-submit-btn">Send
													Reply</button>
											</div>
										</form>
									</div>
								</c:if>
							</footer>
							
						</div>
					</div>
					<div style="margin-top: 20px;">
						<div class="reply-list">
							<a href="javascript:void(0);" onclick="toggleReply('${commentList.commentId}')" style="justify-content: left;">Reply List ▼</a>
							<div id="webReply-${commentList.commentId}" class="webReply" style="display: none;"></div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	// 댓글 작성
$(document).ready(function() {
    $('#commentForm').submit(function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작 방지

        var formData = $(this).serialize(); // 폼 데이터 직렬화

        $.ajax({
            type: "POST",
            url: "/writeComment",
            data: formData,
            dataType: "json", // 서버로부터 응답을 JSON으로 받음
            success: function(respose) {
            	history.go(0);
            },
            error: function() {
                // 댓글 작성 실패 시 동작
                swal({
                    title: "실패!",
                    text: "댓글 작성에 실패했습니다.",
                    icon: "error",
                    button: "확인",
                });
            }
        });
    });
});

	function toggleWrite() {
		$("#writeResponseForm").toggle(); // jQuery를 사용하여 토글
	}
	function toggleReplyWrite(element) {
		// 클릭된 답글 버튼에서 data-comment-id 속성 값을 받아온다.
		var commentId = $(element).data('comment-id');

		// 해당 댓글 ID에 기반한 위치에 답글 입력창을 표시
		// 입력창을 해당 댓글의 바로 아래에 표시하고 싶다면, 해당 댓글의 마크업 구조에 따라 적절한 선택자를 사용하여 위치를 지정
		$("#writeReplyForm_" + commentId).toggle();
	}

	
	$(document).on('click', '.reply-submit-btn', function(e) {
	    e.preventDefault(); // 폼 기본 제출 방지

	    var form = $(this).closest('form'); // 'Send Reply' 버튼이 속한 폼 선택
	    var formData = form.serialize(); // 폼 데이터 직렬화

	    $.ajax({
	        type: "POST",
	        url: "/writeReply",
	        data: formData,
	        success: function(response) {
	            // 성공적으로 답글이 작성된 후 필요한 동작 수행, 예를 들어 페이지를 새로고침하거나, 답글 목록을 업데이트
	            location.reload(); // 페이지를 새로고침하여 답글을 보여줄 수 있음
	        },
	        error: function() {
	            // 답글 작성 실패 시 로직
	            swal({
	    	        title: "fail",
	    	        text: "답글 작성에 실패했습니다.",
	    	        type: "error",
	    	        showCancelButton: false,
	    	        confirmButtonColor: "#DD6B55",
	    	        confirmButtonText: "OK",
	    	        closeOnConfirm: true
	    	    });
	        }
	    });
	});
	
	function message() {
	    swal({
	        title: "fail",
	        text: "로그인 후 이용해주세요.",
	        type: "error",
	        showCancelButton: false,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "OK",
	        closeOnConfirm: true
	    }, function() {
	        // "OK" 버튼 누르면 실행될 코드
	        window.location.href = "/signIn"; // 로그인 페이지로 이동
	    });
	}

	function toggleReplies(commentId) {
		var replyList = document.getElementById("replyList-" + commentId);
		if (replyList.style.display === "none") {
			replyList.style.display = "block";
		} else {
			replyList.style.display = "none";
		}
	}

	//현재 시간
	const now = new Date();

	// 댓글 수정 비동기 처리
	$(document).ready(function() {
	    // 함수 정의
	    window.toggleUpdate = function(commentId, commentContent) {
	    	   var updateBtn = $('#commentUpdateBtn_' + commentId);

	   	    // '수정' 버튼이 클릭된 경우
	   	    if (updateBtn.val() === 'Update') {
	   	        // 내용을 텍스트 에리어로 변환
	   	        var divContent = $('#commentContent_' + commentId).text();
	   	        var textarea = $('<textarea class="form-control">').val(divContent.trim()).attr('rows', 5).attr('id', 'textarea_' + commentId);
	   	        $('#commentContent_' + commentId).empty().append(textarea);

	   	        // 버튼의 텍스트를 '저장'으로 변경
	   	        updateBtn.val('Save');
	   	    } else {
	   	        // '저장' 버튼이 클릭된 경우, 수정된 내용을 가져와서 저장 로직 수행
	   	        var updatedContents = $('#textarea_' + commentId).val();

	   	        if (updatedContents.trim() === '') {
	   	            swal("fail", "내용을 입력하세요.", "error", {
	   					button: "OK",
	   				});
	   	            return;
	   	        }

	   	        // AJAX 요청을 통해 서버에 수정된 내용 저장
	   	        $.ajax({
	   	            url: "/updateComment",
	   	            type: "POST",
	   	            data: {
	   	                'commentId': commentId,
	   	                'updatedContents': updatedContents
	   	            },
	   	            dataType: 'text',
	   	            success: function(response) {
	   	                $('#commentContent_' + commentId).text(response);
	   	                updateBtn.val('Update');
	   	            },
	   	            error: function(error) {
	   	                console.error('에러 발생:', error);
	   	            }
	   	        });
	   	    }
	    };
	    
    window.toggleDelete = function(commentId) {
	    	   // AJAX 요청을 통해 서버에 삭제 요청
	    	    $.ajax({
	    	        url: "/deleteComment",
	    	        type: "POST",
	    	        data: {
	    	            'commentId': commentId
	    	        },
	    	        dataType: 'text',
	    	        success: function(response) {
	    	            // 성공적으로 삭제되었다는 메시지를 콘솔에 출력
	    	            console.log('삭제 완료:', response);
	    	            // UI에서 삭제된 댓글 제거 또는 사용자에게 성공 알림
	    	            // 예: $('#commentSection_' + commentId).remove();
	    	            swal({
	    	    	        title: "success",
	    	    	        text: "댓글이 성공적으로 삭제되었습니다.",
	    	    	        type: "success"
	    	    	    });
	    	        },
	    	        error: function(error) {
	    	            console.error('에러 발생:', error);
	    	            // 사용자에게 에러 발생을 알림
	    	            swal({
	    	    	        title: "fail",
	    	    	        text: "댓글을 삭제하는 도중 에러가 발생했습니다.",
	    	    	        type: "error",
	    	    	        showCancelButton: false,
	    	    	        confirmButtonColor: "#DD6B55",
	    	    	        confirmButtonText: "OK",
	    	    	        closeOnConfirm: true
	    	    	    });
	    	        }
	    	    });
	   	    } 
	    });
	
	
	// 답글 목록 출력
function toggleReply(commentId) {
    var replyElement = document.getElementById("webReply-" + commentId);
    if (replyElement.style.display === "none") {
        replyElement.style.display = "block"; // 요소를 보여줍니다.
       
        $.ajax({
        	  url: '/replyList',
        	  method: 'GET',
        	  data: {
        	       'commentId': commentId
        	  },
        	  dataType: 'json',
        	  success: function(data) {
        	        		
        	    if (data.length > 0) {
        	    	
        	    	
        	    	for(var i = 0; i < data.length; i++) {
        	    		  var itemHtml = 
        	    		    `<div class="item">
        	    		        <div class="user">
        	    		            <figure><img src="profileImg/${ data[i].profileImgName}"></figure>
        	    		            <div class="details">
        	    		            <h5 class="title">${data[i].memberId}<a href="/memberPage?memberId=${data[i].memberId}">${data[i].memberNickname}</a></h5>
        	    		            <div class="time"></div>
        	    		            <div class="description">${data[i].replyContent}</div>
        	    		            </div>
        	    		        </div>
        	    		      </div>`;
        	    		      
        	    		  // 기존 내용에 추가
        	    		  replyElement.innerHTML += itemHtml;
        	    		}
        	    	 
						/* data.forEach(function(reply) {
        	    		  const itemHtml = 
        	    		    `<div class="item">
        	    		        <div class="user">
        	    		            <figure><img src="profileImg/${reply.profileImgName}"></figure>
        	    		            <div class="details">
        	    		            <h5 class="title">${reply.memberId} APPLE<a href="/memberPage?memberId=${reply.memberId}">BANANA${reply.memberNickname}</a></h5>
        	    		            <div class="time"></div>
        	    		            <div class="description">${reply.replyContent}</div>
        	    		            </div>
        	    		        </div>
        	    		      </div>`;
        	    		      
        	    		  // 기존 내용에 추가
        	    		 replyElement.innerHTML += itemHtml;
        	    		  console.log("잘됨!");
        	    		}); */
        	          	    	
        	    	
        	    } else {
        	      console.log('데이터가 없습니다.');
        	      // 데이터가 없을 경우의 처리 로직
        	    }
        	  },
        	  error: function(request, status, error) {
        	    console.error('AJAX Error:', error);
        	  }
        	});
        
    }
}
	
</script>