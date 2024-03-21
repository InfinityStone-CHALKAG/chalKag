package infinitystone.chalKag.biz.report;

import lombok.Data;

@Data
public class ReportDTO { // 신고 정보 관리 DTO 생성. 전미지
	private String reportId;			// 신고 아이디
	private String memberId;			// 회원 아이디	
	private String reportSuspector;		// 피신고자 아이디
	private String reportDate;			// 신고 작성 시간
	private String reportContent;		// 신고 내용
	
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
	@Override
	public String toString() {
		return "ReportDTO [reportId=" + reportId + ", memberId=" + memberId + ", reportSuspector=" + reportSuspector
				+ ", reportDate=" + reportDate + ", reportContent=" + reportContent + "]";
	}

	

}
