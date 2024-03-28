package infinitystone.chalKag.biz.timeOut;

import lombok.Data;

@Data
public class TimeOutDTO { // 신고 정보 관리 DTO 생성. 전미지
	private String timeOutId;			// 신고 아이디
	private String memberId;			// 회원 아이디	
	private String timeOutStartDate;		// 피신고자 아이디
	private String timeOutDuration;			// 신고 작성 시간
	
}
