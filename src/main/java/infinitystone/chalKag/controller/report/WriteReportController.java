package infinitystone.chalKag.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.report.ReportDTO;
import infinitystone.chalKag.biz.report.ReportService;

@Controller
public class WriteReportController {
	
	  @Autowired
	  private ReportService reportService;


  @RequestMapping(value = "/writeReport", method = RequestMethod.GET)
  public String writeReportPage(ReportDTO reportDTO, Model model) {
	  
	  System.out.println("writeReportController In로그 = [" + reportDTO + "]");
	  model.addAttribute("reportInfo", reportDTO);
	  
	  System.out.println("writeReportController Out로그 = [" + reportDTO + "]");
	  
    return "report/writeReport";
  }

  @RequestMapping(value = "/writeReport", method = RequestMethod.POST)
  public String writeReport(ReportDTO reportDTO) {
	  
	  System.out.println("writeReportController In로그 = [" + reportDTO + "]");
	  
	  if (!reportService.insert(reportDTO)) {
	      System.out.println("[로그] Controller : 회원신고 실패");
	      return "redirect:error";
	    }
	  
	  System.out.println("writeReportController Out로그 = [" + reportDTO + "]");

    return "redirect:memberPage";
  }
}
