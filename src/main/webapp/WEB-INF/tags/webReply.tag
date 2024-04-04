<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="webReply"%>

<div class="reply-list">
	<c:if test="${fn:length(replyList) > 0}">
		<a href="javascript:void(0);" onclick="toggleReply()"
			style="justify-content: left;">Reply List ▼</a>
		<div id="webReply" style="display: none;">
			<!-- 답글 목록 출력 -->
			<c:forEach var="replyList" items="${replyList}">
				<!-- 답글 내용 출력 -->
				<div class="item">
					<div class="user">

						<!-- 회원 프로필 이미지 -->
						<figure>
							<img src="profileImg/${replyList.profileImgName}">
						</figure>

						<div class="details">
							<h5 class="title">${replyList.memberId}
								<a href="/memberPage?memberId=${replyList.memberId}">${replyList.memberId}</a>
							</h5>
							<div class="time"></div>
							<div class="description">${replyList.replyContent}</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
</div>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	//토글 기능을 수행하는 JavaScript 함수
	function toggleReply() {
		var replyElement = document.getElementById("webReply");
		if (replyElement.style.display === "none") {
			replyElement.style.display = "block"; // 요소를 보여줍니다.
		} else {
			replyElement.style.display = "none"; // 요소를 숨깁니다.
		}
	}
	
	$(document).ready(function() {
		   // 함수 정의
	    window.toggleReplyUpdate = function(replyId, replyContent) {
	    	   var updateBtn = $('#replyUpdateBtn_' + replyContentId);

	   	    // '수정' 버튼이 클릭된 경우
	   	    if (updateBtn.val() === 'Update') {
	   	        // 내용을 텍스트 에리어로 변환
	   	        var divContent = $('#replyContent_' + replyId).text();
	   	        var textarea = $('<textarea class="form-control">').val(divContent.trim()).attr('rows', 5).attr('id', 'textarea_' + replyId);
	   	        $('#replyContent_' + replyId).empty().append(textarea);

	   	        // 버튼의 텍스트를 '저장'으로 변경
	   	        updateBtn.val('Save');
	   	    } else {
	   	        // '저장' 버튼이 클릭된 경우, 수정된 내용을 가져와서 저장 로직 수행
	   	        var updatedContents = $('#textarea_' + replyId).val();

	   	        if (updatedContents.trim() === '') {
	   	            swal("fail", "내용을 입력하세요.", "error", {
	   					button: "OK",
	   				});
	   	            return;
	   	        }

	   	        // AJAX 요청을 통해 서버에 수정된 내용 저장
	   	        $.ajax({
	   	            url: "/updateReply",
	   	            type: "POST",
	   	            data: {
	   	                'replyId': replyId,
	   	                'updatedContents': updatedContents
	   	            },
	   	            dataType: 'text',
	   	            success: function(response) {
	   	                $('#replyContent_' + replyId).text(response);
	   	                updateBtn.val('Update');
	   	            },
	   	            error: function(error) {
	   	                console.error('에러 발생:', error);
	   	            }
	   	        });
	   	    }
	    };
	    
    window.toggleReplyDelete = function(replyId) {
	    	   // AJAX 요청을 통해 서버에 삭제 요청
	    	    $.ajax({
	    	        url: "/deleteReply",
	    	        type: "POST",
	    	        data: {
	    	            'replyId': replyId
	    	        },
	    	        dataType: 'text',
	    	        success: function(response) {
	    	            // 성공적으로 삭제되었다는 메시지를 콘솔에 출력
	    	            console.log('삭제 완료:', response);
	    	            // UI에서 삭제된 댓글 제거 또는 사용자에게 성공 알림
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

	
</script>
