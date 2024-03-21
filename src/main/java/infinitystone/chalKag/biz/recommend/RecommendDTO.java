package infinitystone.chalKag.biz.recommend;

import lombok.Data;

@Data
public class RecommendDTO {	// 게시글 좋아요 DTO 생성.전미지
	private String recommendId; 		// 게시글 좋아요 아이디
	private String postId;				// 게시글 아이디
	private String memberId;			// 회원 아이디
//  private String postCategory; 		// 게시판 종류 (HEADHUNTPOST, JOBHUNTPOST, FREEPOST, MARKETPOST)
//  private String postTitle; 			// 게시글 제목
//  private String postDate; 			// 게시글 작성일
//  private String postViewcnt; 		// 게시글 조회수
//  private String postRecommendCnt; 	// 게시글 좋아요 수
	
	private String searchCondition;

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override // 디버깅용
	public String toString() {
		return "RecommendDTO [recommendId=" + recommendId + ", postId=" + postId + ", memberId=" + memberId
				+ ", searchCondition=" + searchCondition + "]";
	}
}