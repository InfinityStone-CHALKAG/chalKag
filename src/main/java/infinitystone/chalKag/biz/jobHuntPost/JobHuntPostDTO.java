package infinitystone.chalKag.biz.jobHuntPost;

import lombok.Data;

@Data
public class JobHuntPostDTO {
	private String jobHuntPostId;
	private String memberId;
	private String memberNickname;			// 회원 닉네임
	private String profileImgName;
	private String jobHuntPostDate;
	private String jobHuntPostRole;
	private String jobHuntPostRegion;
	private int jobHuntPostPay;
	private String jobHuntPostWorkDate;
	private String jobHuntPostConcept;
	private String jobHuntPostTitle;
	private String jobHuntPostContent;
	private String jobHuntPostViewcnt;
	private String postImgName;			// 프리미엄 회원글 출력 시 이미지 출력
	
	private String recommendCnt;
	
	private String searchCondition;
	
}
