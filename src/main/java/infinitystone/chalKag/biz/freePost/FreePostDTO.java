package infinitystone.chalKag.biz.freePost;

import lombok.Data;

@Data
public class FreePostDTO {
	private String freePostId;			// 자유글 아이디
	private String memberId;				// 회원 아이디
	private String memberNickname;			// 회원 닉네임
	private String memberIntroduction;		// 회원 소개글	  
	private String profileImgName;			// 프로필 이미지 이름
	private String freePostDate;		// 자유글 작성 시간
	private String freePostConcept;		// 자유글 촬영 컨셉
	private String freePostTitle;		// 자유글 제목
	private String freePostContent;		// 자유글 내용
	private String freePostViewcnt;		// 자유글 조회수
	
	private String postId;					// 게시글 아이디
	
	private String postImgName;				// 게시글 이미지 이름
	
	private String postCategory;			// 게시글 카테고리
	
	private int	   recommendCnt;			// 게시글의 좋아요 수 
	
	private String titleAndContents;		// 제목 내용 검색 DTO		
	
	private String fromday;					// 작성일 검색 범위 시작
	private String today;					// 작성일 검색 범위 끝
	
	private String sortOrder;				// 정렬 (명칭 바꿈)
	
	private String searchField;				// 검색 키워드
	private String searchInput;				// 검색어
	
	private String searchCondition;			// 게시글 검색 조건
	
}
