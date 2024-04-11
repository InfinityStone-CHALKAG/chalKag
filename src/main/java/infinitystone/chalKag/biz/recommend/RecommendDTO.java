package infinitystone.chalKag.biz.recommend;

import lombok.Data;

@Data
public class RecommendDTO {	// 게시글 좋아요 DTO 생성.전미지
	private String recommendId; 		// 게시글 좋아요 아이디
	private String postId;				// 게시글 아이디
	private String memberId;			// 회원 아이디
	private int	   recommendCnt;		// 좋아요수
	private String postCategory; 		// 게시판 종류 (HEADHUNTPOST, JOBHUNTPOST, FREEPOST, MARKETPOST)
	private String postTitle; 			// 게시글 제목
	private String postContent; 		// 게시글 내용
	private String postDate; 			// 게시글 작성일	
	private String postViewcnt; 		// 게시글 조회수
	private String postRecommendCnt; 	// 게시글 좋아요 수
	private String postImgName;			// 게시글 이미지 이름
	
	private String headHuntPostId;		// 구인글 아이디
	private String headHuntPostTitle; 	// 구인글 제목
	private String headHuntPostContent;	// 구인글 내용
	private String headHuntPostDate; 	// 구인글 작성일	
	private String headHuntPostViewcnt; // 구인글 조회수
	
	private String jobHuntPostId;		// 구직글 아이디
	private String jobHuntPostTitle; 	// 구직글 제목
	private String jobHuntPostContent;	// 구직글 내용
	private String jobHuntPostDate; 	// 구직글 작성일	
	private String jobHuntPostViewcnt; 	// 구직글 조회수
	
	private String freePostId;			// 자유글 아이디
	private String freePostTitle; 		// 자유글 제목
	private String freePostContent;		// 자유글 내용
	private String freePostDate; 		// 자유글 작성일	
	private String freePostViewcnt; 	// 자유글 조회수	
	
	private String marketPostId;		// 장터글 아이디
	private String marketPostTitle;	 	// 장터글 제목
	private String marketPostContent;	// 장터글 내용
	private String marketPostDate; 		// 장터글 작성일	
	private String marketPostViewcnt; 	// 장터글 조회수	
	
	private String searchCondition;		// 검색 조건
}