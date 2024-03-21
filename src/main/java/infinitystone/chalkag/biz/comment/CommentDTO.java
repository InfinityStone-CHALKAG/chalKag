package infinitystone.chalkag.biz.comment;

import lombok.Data;

@Data
public class CommentDTO { 			// 댓글 DTO 생성.전미지
	private String commentId;		// 댓글 아이디
	private String postId;			// 게시글 아이디
	private String memberId;		// 회원 아이디
	private String commentDate;		// 댓글 작성 시간
	private String commentContent; 	// 댓글 내용
	private String memberNickname;	// 작성자 닉네임
	private String profileImgName;	// 작성자 프로필사진
	
	private String searchCondition;
}