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

// 신고 거절 목록
  @RequestMapping(value = "/reportStateReject", method = RequestMethod.GET)
  public String reportStateReject(ReportDTO reportDTO) {

    System.out.println("AdminReportListController In로그");
// 검색 상태 설정 후 신고 update에 연결
    reportDTO.setSearchCondition("reportStateReject");
    if (!reportService.update(reportDTO)) {
      System.out.println("AdminTimeOutListController reportUpdate failed");
    }
// View 에 값 전달
    System.out.println("AdminReportListController Out로그");
    return "redirect:adminReportList";
  }

  //이미 정지된 회원인지 체크
  @RequestMapping(value = "/reportStateCheck", method = RequestMethod.POST)
  public @ResponseBody int reportStateCheck(MemberDTO memberDTO) {

    System.out.println("AdminReportListController In로그");
// 정지된 회원인지 selectOne 으로 탐색 후 아닐경우 0 정지된 회원일 경우 1로 반환
    memberDTO.setSearchCondition("myPage");
    MemberDTO result = memberService.selectOne(memberDTO);
    if (result.getMemberGrade().equals("TIMEOUT")) {
      System.out.println("AdminReportListController Out로그");
      return 0;
    }
    System.out.println("AdminReportListController Out로그");
    return 1;


  }
// 회원의 계정 정지 
  @RequestMapping(value = "/reportStateHold", method = RequestMethod.POST)
  public String reportStateHold(TimeOutDTO timeOutDTO, MemberDTO memberDTO) {

    System.out.println("AdminReportListController In로그");
// 신고 정보 저장
    if (!timeOutService.insert(timeOutDTO)) {
      System.out.println("AdminTimeOutListController reportUpdate failed");
    }
// 검색 상태 설정(계정 정지)
    memberDTO.setSearchCondition("timeOut");
// 회원의 정보 변경
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

// SQL 쿼리를 생성하기 위한 StringBuilder 인스턴스 생성
    StringBuilder reportIdSb = new StringBuilder();
  // 선택된 신고 ID가 있는 경우, SQL 쿼리를 생성
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
// DTO에 SQL 쿼리 문자열 설정
    reportDTO.setSelectedStateUpdateSQL(SELCTEDSTATEUPDATESQL);

 // 관리자가 읽었을 경우
    if (state.equals("toRead")) {
      reportDTO.setSearchCondition("selectedStateRead");
// 신고 정보 수정
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
