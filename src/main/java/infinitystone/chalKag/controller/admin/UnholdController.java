package infinitystone.chalKag.controller.admin;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnholdController {

  @Autowired
  private MemberService memberService;

// 계정 정지 해제
  @RequestMapping(value = "/unHold")
  public String unHold(MemberDTO memberDTO) {
// 검색 상태 설정
    memberDTO.setSearchCondition("unHold");
// 회원 정보 수정으로 연결
    memberService.update(memberDTO);
// View 에 값 전달
    return "redirect:adminTimeOutList";
  }

}
