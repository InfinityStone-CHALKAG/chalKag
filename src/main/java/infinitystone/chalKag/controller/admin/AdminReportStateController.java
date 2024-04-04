package infinitystone.chalKag.controller.admin;


import java.lang.reflect.Member;
import java.util.ArrayList;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
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
  @Autowired
  private MemberService memberService;


  @RequestMapping(value = "/reportStateReject", method = RequestMethod.GET)
  public String reportStateReject(ReportDTO reportDTO) {

    System.out.println("AdminReportListController In로그");

    reportDTO.setSearchCondition("reportStateReject");
    if (!reportService.update(reportDTO)) {
      System.out.println("AdminTimeOutListController reportUpdate failed");
    }

    System.out.println("AdminReportListController Out로그");
    return "redirect:adminReportList";
  }

  //이미 정지된 회원인지 체크
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
  public String reportStateHold(TimeOutDTO timeOutDTO, MemberDTO memberDTO) {

    System.out.println("AdminReportListController In로그");

    if (!timeOutService.insert(timeOutDTO)) {
      System.out.println("AdminTimeOutListController reportUpdate failed");
    }

    memberDTO.setSearchCondition("timeOut");

    if (!memberService.update(memberDTO)) {
      System.out.println("AdminTimeOutListController memberUpdate failed");
    }

    System.out.println("AdminReportListController Out로그");

    return "redirect:adminReportList";
  }

  // 신고목록 전체출력 에서 진행하는 메서드
  @RequestMapping(value = "/selectedStateUpdate", method = RequestMethod.GET)
  public String selectedStateRead(ReportDTO reportDTO,
                                  @RequestParam("selectedReportIds") String[] selectedReportIds, @RequestParam("state") String state) {
    System.out.println("AdminReportListController In로그");
    String SELCTEDSTATEUPDATESQL = "";
    ArrayList<String> reportIdList = new ArrayList<String>();
    for (int i = 0; i < selectedReportIds.length; i++) {
      reportIdList.add(selectedReportIds[i]);
      System.out.println("[로그] 받아온 reportId 파라미터 값 : " + selectedReportIds[i]);
    }
    System.out.println(reportIdList.toString());

    StringBuilder reportIdSb = new StringBuilder();
    if (reportIdList.size() > 0) {
      for (int i = 0; i < reportIdList.size(); i++) {
        reportIdSb.append("\'" + reportIdList.get(i) + "\'");
        if (i + 1 < reportIdList.size()) {
          reportIdSb.append(",");
        }
        SELCTEDSTATEUPDATESQL = "(" + reportIdSb.toString() + ")";
      }
    }
    System.out.println(SELCTEDSTATEUPDATESQL);

    reportDTO.setSelectedStateUpdateSQL(SELCTEDSTATEUPDATESQL);
    if (state.equals("toRead")) {
      reportDTO.setSearchCondition("selectedStateRead");
      if (!reportService.update(reportDTO)) {
        System.out.println("AdminReportListController Out로그");
      }
    } else {
      reportDTO.setSearchCondition("selectedStateDeleted");
      if (!reportService.update(reportDTO)) {
        System.out.println("AdminReportListController Out로그");
      }
    }

    System.out.println("AdminReportListController Out로그");
    return "redirect:adminReportList";
  }
}
