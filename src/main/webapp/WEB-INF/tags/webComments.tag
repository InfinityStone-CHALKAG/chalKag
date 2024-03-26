<%@ tag language="java" pageEncoding="UTF-8" import="infinitystone.chalKag.biz.comment.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="webComments"%>

<div class="comments">
	<h5>${fn:length(commentList)} Responses</h5>
	<div style="text-align: center;"">
		<input style=" display: inline-block;" class="btn btn-primary btn-rounded" type="button" value="Write Responses" onclick="toggleWrite()">
	</div>
	<br>
	<div class="comment-list">
		<!-- 댓글이 없을 경우 출력 문구 -->
		<c:if test="${commentList == null || fn:length(commentList) <= 0}">
			<h5 style="text-align: center;">댓글이 없습니다. 가장 먼저 댓글을 남겨보세요!</h5>
		</c:if>
		<!-- 댓글이 있을 경우 출력 -->
		<c:if test="${fn:length(commentList) > 0 }">
			<c:forEach var="commentList" items="${commentList}">
				<!-- 댓글 내용 출력 -->
				<div class="item">
					<div class="user">
						<!-- 회원 프로필 이미지 -->
						<figure>
							<img src="images/img01.jpg">
						</figure>
						<div class="details">
							<h5 class="title">${commentList.memberId}
								<c:if test="${member == null || member != commentList.memberId}">
									<a href="/memberPage/memberId=${commentList.memberId}">${commentList.memberId}</a>
								</c:if>
								<c:if test="${member == commentList.memberId}">
									<a href="/myPage/memberId=${member}">${commentList.memberId}</a>
								</c:if>
							</h5>
							<div class="time"></div>
							<div class="description">${commentList.commentContent}</div>
							<!-- 답글 달기 -->
							<footer style="display: flex;">
								<c:if test="${fn:length(replyList) > 0}">
									<a style="justify-content: left;" > Reply ▼</a>
								</c:if>
								
								<c:if test="${member == null}">
									<input class="btn btn-primary btn-rounded" type="button" value="Reply" disabled="disabled">
								</c:if>
								<c:if test="${member != null}">
									<input type="button" value="Reply" onclick="toggleReply()">
								</c:if>
								<c:if test="${commentList.memberId == member}">
									<input class="btn btn-primary btn-rounded" type="button" value="Reply" onclick="toggleReply()">
									<input class="btn btn-primary btn-rounded" type="button" value="Update" onclick="toggleUpdate()">
									<input class="btn btn-primary btn-rounded" type="button" value="Delete" onclick="toggleDelete()">
								</c:if>
							</footer>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>

	</div>

</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	//현재 시간
	const now = new Date();
	
	window.onscroll = function() {
	    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
	    	loadMoreComments();
	    }
	};

	
</script>