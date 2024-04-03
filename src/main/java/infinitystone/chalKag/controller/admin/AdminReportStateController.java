package infinitystone.chalKag.controller.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.report.ReportDTO;
import infinitystone.chalKag.biz.report.ReportService;
import infinitystone.chalKag.biz.timeOut.TimeOutDTO;
import infinitystone.chalKag.biz.timeOut.TimeOutService;

@Controller
public class AdminReportStateController {
	@Autowired
	private TimeOutService timeOutService;
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/reportStateReject", method = RequestMethod.GET)
	public String reportStateReject(ReportDTO reportDTO) {

		System.out.println("AdminReportListController In로그");

		reportDTO.setSearchCondition("reportStateReject");
		if (!reportService.update(reportDTO)) {
			System.out.println("AdminTimeOutController reportStateReject failed");
		}

		System.out.println("AdminReportListController Out로그");
		return "redirect:adminReportList";
	}

	// 이미 정지된 회원인지 체크
	@RequestMapping(value = "/reportStateCheck", method = RequestMethod.POST)
	public @ResponseBody int reportStateCheck(TimeOutDTO timeOutDTO) {

		System.out.println("AdminReportListController In로그");

		TimeOutDTO result = timeOutService.selectOne(timeOutDTO);
		if (result != null) {
			System.out.println("AdminReportListController Out로그");
			return 0;
		}
		System.out.println("AdminReportListController Out로그");
		return 1;

	}

	@RequestMapping(value = "/reportStateHold", method = RequestMethod.POST)
	public String reportStateHold(TimeOutDTO timeOutDTO, ReportDTO reportDTO) {

		System.out.println("AdminReportListController In로그");

		if (!timeOutService.insert(timeOutDTO)) {
			System.out.println("AdminTimeOutController reportStateHold failed");
		}
		reportDTO.setSearchCondition("reportStateHold");
		if (!reportService.update(reportDTO)) {
			System.out.println("AdminTimeOutController reportStateHold failed");
		}

		System.out.println("AdminReportListController Out로그");

		return "redirect:adminReportList";
	}

	@RequestMapping(value = "/reportStateUnHold", method = RequestMethod.GET)
	public String reportStateUnHold(TimeOutDTO timeOutDTO, ReportDTO reportDTO) {
		
		System.out.println("AdminReportListController In로그");
		System.out.println("[로그] :" + timeOutDTO);
		System.out.println("[로그] :" + reportDTO);

		if (!timeOutService.delete(timeOutDTO)) {
			System.out.println("AdminTimeOutController reportStateUnHold failed");
		}
		reportDTO.setSearchCondition("reportStateRead");
		if (!reportService.update(reportDTO)) {
			System.out.println("AdminTimeOutController reportStateUnHold failed");
		}

		System.out.println("AdminReportListController Out로그");

		return "redirect:adminReportList";
	}

//	// 신고목록 전체출력 에서 진행하는 메서드
//	@RequestMapping(value = "/selectedStateRead", method = RequestMethod.GET)
//	public String selectedStateRead(ReportDTO reportDTO,
//			@RequestParam("selectedReportIds") String[] selectedReportIds) {
//		System.out.println("AdminReportListController In로그");
//		String SELCTEDSTATEREADSQL = "";
//		ArrayList<String> reportIdList = new ArrayList<String>();
//		for (int i = 0; i < selectedReportIds.length; i++) {
//			reportIdList.add(selectedReportIds[i]);
//			System.out.println("[로그] 받아온 reportId 파라미터 값 : " + selectedReportIds[i]);
//		}
//		System.out.println(reportIdList.toString());
//
//		StringBuilder reportIdSb = new StringBuilder();
//		if (reportIdList.size() > 0) {
//			for (int i = 0; i < reportIdList.size(); i++) {
//				reportIdSb.append("\'" + reportIdList.get(i) + "\'");
//				if (i + 1 < reportIdList.size()) {
//					reportIdSb.append(",");
//				}
//				SELCTEDSTATEREADSQL = "(" + reportIdSb.toString() + ")";
//			}
//		}
//		System.out.println(SELCTEDSTATEREADSQL);
//
//		reportDTO.setSelectedStateReadSQL(SELCTEDSTATEREADSQL);
//		reportDTO.setSearchCondition("selectedStateRead");
//		if (!reportService.update(reportDTO)) {
//			System.out.println("AdminReportListController Out로그");
//		}
//
//		System.out.println("AdminReportListController Out로그");
//		return "redirect:adminReportList";
//	}
}
