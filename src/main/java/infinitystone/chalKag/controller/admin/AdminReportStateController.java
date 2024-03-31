package infinitystone.chalKag.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.member.MemberDTO;
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
      System.out.println("AdminTimeOutController reportUpdate failed");
    }

    System.out.println("AdminReportListController Out로그");
    return "redirect:adminReportList";
  }

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
      System.out.println("AdminTimeOutController reportUpdate failed");
    }
    reportDTO.setSearchCondition("reportStateHold");
    if (!reportService.update(reportDTO)) {
      System.out.println("AdminTimeOutController reportUpdate failed");
    }
    
    System.out.println("AdminReportListController Out로그");

    return "redirect:adminReportList";
  }
}
