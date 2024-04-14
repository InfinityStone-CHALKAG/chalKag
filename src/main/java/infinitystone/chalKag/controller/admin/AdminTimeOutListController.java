package infinitystone.chalKag.controller.admin;

import infinitystone.chalKag.biz.admin.AdminDTO;
import infinitystone.chalKag.biz.admin.AdminService;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminTimeOutListController {

  @Autowired
  private MemberService memberService;
  @Autowired
  private AdminService adminService;

// 정지 당한 회원 목록 출력
  @RequestMapping(value = "/adminTimeOutList", method = RequestMethod.GET)
  public String timeOutPage(MemberDTO memberDTO,  AdminDTO adminDTO, Model model) {
// 검색 상태 설정
    memberDTO.setSearchCondition("timeOutList");
// 설정한 검색 상태로 selectAll에 연결
    model.addAttribute("timeOutList", memberService.selectAll(memberDTO));
    
    // 헤더 정보 출력
    adminDTO.setSearchCondition("adminHeader");

    model.addAttribute("adminHeader", adminService.signUpCountByGenderGroup(adminDTO));
    
// 목록 값을 View로 전달
    return "admin/adminTimeOutList";
  }

}