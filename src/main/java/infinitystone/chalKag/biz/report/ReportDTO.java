package infinitystone.chalKag.biz.report;

import lombok.Data;

@Data
public class ReportDTO { // 신고 정보 관리 DTO 생성. 전미지
	private String reportId;			// 신고 아이디
	private String memberId;			// 회원 아이디	
	private String reportSuspector;		// 피신고자 아이디
	private String reportDate;			// 신고 작성 시간
	private String reportContent;		// 신고 내용
	private String reportState;			// 신고 상태 (관리자 확인용)
	
	private String searchCondition;		// 신고글 검색 조건

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReportSuspector() {
		return reportSuspector;
	}

	public void setReportSuspector(String reportSuspector) {
		this.reportSuspector = reportSuspector;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getReportState() {
		return reportState;
	}

	public void setReportState(String reportState) {
		this.reportState = reportState;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override // 디버깅용
	public String toString() {
		return "ReportDTO [reportId=" + reportId + ", memberId=" + memberId + ", reportSuspector=" + reportSuspector
				+ ", reportDate=" + reportDate + ", reportContent=" + reportContent + ", reportState=" + reportState
				+ ", searchCondition=" + searchCondition + "]";
	}
}
