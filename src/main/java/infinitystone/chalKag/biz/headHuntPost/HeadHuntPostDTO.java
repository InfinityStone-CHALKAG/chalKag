package infinitystone.chalKag.biz.headHuntPost;

import lombok.Data;

@Data
public class HeadHuntPostDTO { // 구인글 게시판 DTO 생성.전미지
	private String headHuntPostId;			// 구인글 아이디
	private String memberId;				// 회원 아이디
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 회원 소개글	  
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
	
	private int	   recommendCnt;			// 게시글의 좋아요 수 
	
	private String titleAndContents;		// 제목 내용 검색 DTO		
	
	private int	   minPay;					// 최소 금액
	private int	   maxPay;					// 최대 금액
	
	private String startWorkDate;			// 작업 시작일
	private String endWorkDate;				// 작업 마감일
	
	private String OrderColumnDirection;	// 정렬
	
	private String searchField;				
	
	private String searchCondition;			// 게시글 검색 조건

}	