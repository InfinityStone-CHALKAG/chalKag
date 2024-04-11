package infinitystone.chalKag.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import infinitystone.chalKag.biz.report.ReportDTO;
import infinitystone.chalKag.biz.report.ReportService;

@Controller
public class AdminReportSingleController {

	@Autowired
	private ReportService reportService;
// 회원 신고 상세 페이지 이동
	@RequestMapping("/adminReportSingle")
	public String adminReportSingle(ReportDTO reportDTO, Model model) {
		
		System.out.println("AdminReportSingleController In로그");
// 신고 상세 정보를 selectOne 에서 받은 뒤
		reportDTO = reportService.selectOne(reportDTO);
// 관리자가 안읽은 경우
		if (reportDTO.getReportState().equals("UNREAD")) {
// 검색 상태를 읽음으로 변경
			reportDTO.setSearchCondition("reportStateRead");
			if (!reportService.update(reportDTO)) {
				System.out.println("AdminTimeOutListController reportUpdate failed");
			}
		}

// View에 값 전달
		model.addAttribute("reportSingle", reportDTO);

		System.out.println("AdminReportSingleController Out로그");

		return "admin/adminReportSingle";
	}

}
