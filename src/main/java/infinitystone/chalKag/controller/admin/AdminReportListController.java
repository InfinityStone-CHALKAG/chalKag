package infinitystone.chalKag.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.report.ReportDTO;
import infinitystone.chalKag.biz.report.ReportService;

@Controller
public class AdminReportListController {
  @Autowired
  private ReportService reportService;
	// 신고 목록
  @RequestMapping("/adminReportList")
  public String adminReportList(ReportDTO reportDTO, Model model) {

    System.out.println("AdminReportListController In로그");
	// 신고 목록을 selectAll에서 List 타입으로 받은 뒤
    List<ReportDTO> reportListResult = reportService.selectAll(reportDTO);

// View 에 값 전달
    model.addAttribute("reportList", reportListResult);

    System.out.println("AdminReportListController Out로그");

    return "admin/adminReportList";
  }
}
