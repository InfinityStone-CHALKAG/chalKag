package infinitystone.chalKag.biz.jobHuntPost;

import lombok.Data;

@Data
public class JobHuntPostDTO {
	private String jobHuntPostId;
	private String memberId;
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 한줄평 소개
	private String profileImgName;
	private String jobHuntPostDate;
	private String jobHuntPostRole;
	private String jobHuntPostRegion;
	private int    jobHuntPostPay;
	
	// 제목 내용 검색 DTO
	private String titleAndContents;
	
	// 최대 금액
	private int	   maxPay;
	// 최소 금액
	private int	   minPay;
	
	
	// 작업 기간
	private String		startWorkDate;
	private String		endWorkDate;
	
	// 정렬
	private String OrderColumnDirection;
	
	
	private String searchField;
	
	private String jobHuntPostWorkDate;
	private String jobHuntPostConcept;
	private String jobHuntPostTitle;
	private String jobHuntPostContent;
	private String jobHuntPostViewcnt;
	private String postId;
	private String postImgName;			// 프리미엄 회원글 출력 시 이미지 출력
	
	private String postCategory;
	
	private int recommendCnt;
	
	private String searchCondition;
	
}
