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
	
	@RequestMapping("/adminReportSingle")
	public String adminReportSingle(ReportDTO reportDTO, Model model) {
		
		System.out.println("AdminReportSingleController In로그");
		
		reportDTO = reportService.selectOne(reportDTO);
		
		 if (!reportService.update(reportDTO)) {
			 System.out.println("AdminTimeOutController reportUpdate failed");
		 }
		
		model.addAttribute("reportSingle", reportDTO);
		
		System.out.println("AdminReportSingleController Out로그");

		return "admin/adminReportSingle";
	}
	
}
