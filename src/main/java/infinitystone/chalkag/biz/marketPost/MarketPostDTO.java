package infinitystone.chalkag.biz.marketPost;

import lombok.Data;

@Data
public class MarketPostDTO {
	private String marketPostId;
	private String memberId;
	private String memberNickname;			// 회원 닉네임
	private String profileImgName;
	private String marketPostDate;
	private String marketPostPrice;
	private String marketPostCategory;
	private String marketPostCompany;
	private String marketPostStatus;
	private String marketPostTitle;
	private String marketPostContent;
	private String marketPostViewcnt;
	private String postImgName;			// 프리미엄 회원글 출력 시 이미지 출력
	
	private String recommendCnt;
	
	private String searchCondition;
	
	
}
