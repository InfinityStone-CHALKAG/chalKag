package infinitystone.chalKag.biz.marketPost;

import lombok.Data;

@Data
public class MarketPostDTO {
	private String marketPostId;
	private String memberId;
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 한줄평 소개
	private String profileImgName;
	private String marketPostDate;
	private int marketPostPrice;
	private String marketPostCategory;
	private String marketPostCompany;
	private String marketPostStatus;
	private String marketPostTitle;
	private String marketPostContent;
	private String marketPostViewcnt;
	private String postImgName;			// 프리미엄 회원글 출력 시 이미지 출력
	
	private String postCategory;
	
	private String recommendCnt;
	
	private String searchCondition;
	
	
}
