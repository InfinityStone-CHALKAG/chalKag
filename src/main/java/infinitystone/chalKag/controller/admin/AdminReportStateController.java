package infinitystone.chalKag.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(value = "/reportReject", method = RequestMethod.GET)
	public String reportReject(ReportDTO reportDTO) {
		
		
		 if (!reportService.update(reportDTO)) {
			 System.out.println("AdminTimeOutController reportUpdate failed");
		 }
		
		return "redirect:admin/adminReportList";
	}
	@RequestMapping(value = "/userHold", method = RequestMethod.POST)
	public String userHold(TimeOutDTO timeOutDTO , ReportDTO reportDTO, Model model) {
		
		System.out.println("AdminReportListController In로그");
		
		 if (!timeOutService.insert(timeOutDTO)) {
		      System.out.println("AdminTimeOutController reportUpdate failed");
		    }
		 
		 if (!reportService.update(reportDTO)) {
			 System.out.println("AdminTimeOutController reportUpdate failed");
		 }
		
		return "redirect:admin/adminReportList";
	}
}
