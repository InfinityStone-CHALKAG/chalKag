package infinitystone.chalKag.biz.freePost;

import lombok.Data;

@Data
public class FreePostDTO {
	private String freePostId;
	private String memberId;
	private String memberNickname;			// 회원 닉네임
	private String profileImgName;
	private String freePostDate;
	private String freePostTitle;
	private String freePostContent;
	private String freePostViewcnt;
	private String postImgName;			// 프리미엄 회원글 출력 시 이미지 출력
	
	private String postCategory;
	
	private String recommendId;
	private String recommendCnt;
	
	
	private String searchCondition;
	
}
