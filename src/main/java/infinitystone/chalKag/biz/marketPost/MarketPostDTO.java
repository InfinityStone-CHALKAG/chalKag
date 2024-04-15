package infinitystone.chalKag.biz.marketPost;

import lombok.Data;

@Data
public class MarketPostDTO {
	private String marketPostId;			// 거래글 아이디
	private String memberId;				// 회원 아이디
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 회원 소개글	  
	private String profileImgName;			// 프로필 이미지 이름
	private String marketPostDate;			// 거래글 작성 시간
	private int	   marketPostPrice;			// 카메라 가격
	private String marketPostCompany;		// 카메라 제작회사
	private String marketPostCategory;		// 카메라 종류
	private String marketPostStatus;		// 거래글 상태
	private String marketPostTitle;		// 거래글 제목
	private String marketPostContent;		// 거래글 내용
	private String marketPostViewcnt;		// 거래글 조회수
	private int    myRecommend; 			// 로그인한 회원이 좋아요를 눌렀는지 체크
	
	private String postId;					// 게시글 아이디
	
	private String postImgName;				// 게시글 이미지 이름
	
	private String postCategory;			// 게시글 카테고리
	
	private int	   recommendCnt;			// 게시글의 좋아요 수 
	
	private String titleAndContents;		// 제목 내용 검색 DTO		
	
	private int	   minPrice;					// 최소 금액
	private int	   maxPrice;					// 최대 금액
	
	private String fromday;					// 작성일 검색 범위 시작
	private String today;					// 작성일 검색 범위 끝
	
	private String sortOrder;				// 정렬 (명칭 바꿈)
	
	private String searchField;				// 검색 키워드
	private String searchInput;				// 검색어
	
	private String searchCondition;			// 게시글 검색 조건

	
	
}
