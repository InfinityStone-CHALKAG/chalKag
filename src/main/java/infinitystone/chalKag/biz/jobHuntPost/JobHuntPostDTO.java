package infinitystone.chalKag.biz.jobHuntPost;

import lombok.Data;

@Data
public class JobHuntPostDTO { // 구직글 게시판 DTO 생성.전미지
	private String jobHuntPostId;			// 구직글 아이디
	private String memberId;				// 회원 아이디
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 회원 소개글	  
	private String profileImgName;			// 프로필 이미지 이름
	private String jobHuntPostDate;			// 구직글 작성 시간
	private String jobHuntPostRole;			// 구직글 직업 (모델/사진작가)
	private String jobHuntPostRegion;		// 구직글 작업 지역
	private int	   jobHuntPostPay;			// 구직글 작업 페이
	private String jobHuntPostWorkDate;		// 구직글 작업 날짜
	private String jobHuntPostConcept;		// 구직글 촬영 컨셉
	private String jobHuntPostTitle;		// 구직글 제목
	private String jobHuntPostContent;		// 구직글 내용
	private String jobHuntPostViewcnt;		// 구직글 조회수
	private int    myRecommend; 			// 로그인한 회원이 좋아요를 눌렀는지 체크 
	
	private String postId;					// 게시글 아이디
	
	private String postImgName;				// 게시글 이미지 이름
	
	private String postCategory;			// 게시글 카테고리
	
	private int	   recommendCnt;			// 게시글의 좋아요 수 
	
	private String titleAndContents;		// 제목 및 내용 검색		
	
	private int	   minPay;					// 작업 페이 - 최소 금액
	private int	   maxPay;					// 작업 페이 - 최대 금액
	
	private String fromday;					// 작성일 검색 - 범위 시작
	private String today;					// 작성일 검색 - 범위 끝
	
	private String startWorkDate;			// 작업 시작일
	private String endWorkDate;				// 작업 마감일
	
	private String sortOrder;				// 정렬 (명칭 바꿈)
	
	private String searchField;				// 검색 키워드
	private String searchInput;				// 검색어
	
	private String searchCondition;			// 게시글 검색 조건

}	