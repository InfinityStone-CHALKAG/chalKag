package infinitystone.chalKag.biz.headHuntPost;

import lombok.Data;

@Data
public class HeadHuntPostDTO { // 구인글 게시판 DTO 생성.전미지
	private String headHuntPostId;			// 구인글 아이디
	private String memberId;				// 회원 아이디
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 회원 닉네임 		  
	private String profileImgName;			// 프로필 이미지 이름
	private String headHuntPostDate;		// 구인글 작성 시간
	private String headHuntPostRole;		// 구인글 직업 (모델/사진작가)
	private String headHuntPostRegion;		// 구인글 작업 지역
	private int	   headHuntPostPay;			// 구인글 작업 페이
	private String headHuntPostWorkDate;	// 구인글 작업 날짜
	private String headHuntPostConcept;		// 구인글 촬영 컨셉
	private String headHuntPostTitle;		// 구인글 제목
	private String headHuntPostContent;		// 구인글 내용
	private String headHuntPostViewcnt;		// 구인글 조회수
	private String postId;					// 게시글 아이디
	private String postImgName;				// 게시글 이미지 이름
	
	private String postCategory;			// 게시글 카테고리
	
	private String recommendCnt;			// 게시글의 좋아요 수 
	
	private String titleAndContents;		// 제목 내용 검색 DTO		
	
	private int	   minPay;					// 최소 금액
	private int	   maxPay;					// 최대 금액
	
	private int	   startWorkDate;			// 작업 시작일
	private int	   endWorkDate;				// 작업 마감일
	
	private String OrderColumnDirection;	// 정렬
	
	private String searchField;				
	
	private String searchCondition;			// 게시글 검색 조건

	public String getHeadHuntPostId() {
		return headHuntPostId;
	}

	public void setHeadHuntPostId(String headHuntPostId) {
		this.headHuntPostId = headHuntPostId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberIntroduction() {
		return memberIntroduction;
	}

	public void setMemberIntroduction(String memberIntroduction) {
		this.memberIntroduction = memberIntroduction;
	}

	public String getProfileImgName() {
		return profileImgName;
	}

	public void setProfileImgName(String profileImgName) {
		this.profileImgName = profileImgName;
	}

	public String getHeadHuntPostDate() {
		return headHuntPostDate;
	}

	public void setHeadHuntPostDate(String headHuntPostDate) {
		this.headHuntPostDate = headHuntPostDate;
	}

	public String getHeadHuntPostRole() {
		return headHuntPostRole;
	}

	public void setHeadHuntPostRole(String headHuntPostRole) {
		this.headHuntPostRole = headHuntPostRole;
	}

	public String getHeadHuntPostRegion() {
		return headHuntPostRegion;
	}

	public void setHeadHuntPostRegion(String headHuntPostRegion) {
		this.headHuntPostRegion = headHuntPostRegion;
	}

	public int getHeadHuntPostPay() {
		return headHuntPostPay;
	}

	public void setHeadHuntPostPay(int headHuntPostPay) {
		this.headHuntPostPay = headHuntPostPay;
	}

	public String getHeadHuntPostWorkDate() {
		return headHuntPostWorkDate;
	}

	public void setHeadHuntPostWorkDate(String headHuntPostWorkDate) {
		this.headHuntPostWorkDate = headHuntPostWorkDate;
	}

	public String getHeadHuntPostConcept() {
		return headHuntPostConcept;
	}

	public void setHeadHuntPostConcept(String headHuntPostConcept) {
		this.headHuntPostConcept = headHuntPostConcept;
	}

	public String getHeadHuntPostTitle() {
		return headHuntPostTitle;
	}

	public void setHeadHuntPostTitle(String headHuntPostTitle) {
		this.headHuntPostTitle = headHuntPostTitle;
	}

	public String getHeadHuntPostContent() {
		return headHuntPostContent;
	}

	public void setHeadHuntPostContent(String headHuntPostContent) {
		this.headHuntPostContent = headHuntPostContent;
	}

	public String getHeadHuntPostViewcnt() {
		return headHuntPostViewcnt;
	}

	public void setHeadHuntPostViewcnt(String headHuntPostViewcnt) {
		this.headHuntPostViewcnt = headHuntPostViewcnt;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostImgName() {
		return postImgName;
	}

	public void setPostImgName(String postImgName) {
		this.postImgName = postImgName;
	}

	public String getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}

	public String getRecommendCnt() {
		return recommendCnt;
	}

	public void setRecommendCnt(String recommendCnt) {
		this.recommendCnt = recommendCnt;
	}

	public String getTitleAndContents() {
		return titleAndContents;
	}

	public void setTitleAndContents(String titleAndContents) {
		this.titleAndContents = titleAndContents;
	}

	public int getMinPay() {
		return minPay;
	}

	public void setMinPay(int minPay) {
		this.minPay = minPay;
	}

	public int getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(int maxPay) {
		this.maxPay = maxPay;
	}

	public int getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(int startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public int getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(int endWorkDate) {
		this.endWorkDate = endWorkDate;
	}

	public String getOrderColumnDirection() {
		return OrderColumnDirection;
	}

	public void setOrderColumnDirection(String orderColumnDirection) {
		OrderColumnDirection = orderColumnDirection;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override // 디버깅용
	public String toString() {
		return "HeadHuntPostDTO [headHuntPostId=" + headHuntPostId + ", memberId=" + memberId + ", memberNickname="
				+ memberNickname + ", memberIntroduction=" + memberIntroduction + ", profileImgName=" + profileImgName
				+ ", headHuntPostDate=" + headHuntPostDate + ", headHuntPostRole=" + headHuntPostRole
				+ ", headHuntPostRegion=" + headHuntPostRegion + ", headHuntPostPay=" + headHuntPostPay
				+ ", headHuntPostWorkDate=" + headHuntPostWorkDate + ", headHuntPostConcept=" + headHuntPostConcept
				+ ", headHuntPostTitle=" + headHuntPostTitle + ", headHuntPostContent=" + headHuntPostContent
				+ ", headHuntPostViewcnt=" + headHuntPostViewcnt + ", postId=" + postId + ", postImgName=" + postImgName
				+ ", postCategory=" + postCategory + ", recommendCnt=" + recommendCnt + ", titleAndContents="
				+ titleAndContents + ", minPay=" + minPay + ", maxPay=" + maxPay + ", startWorkDate=" + startWorkDate
				+ ", endWorkDate=" + endWorkDate + ", OrderColumnDirection=" + OrderColumnDirection + ", searchField="
				+ searchField + ", searchCondition=" + searchCondition + "]";
	}
	
}	