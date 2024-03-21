package infinitystone.chalkag.controller.common;

import infinitystone.chalkag.biz.member.MemberDTO;
import infinitystone.chalkag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FindIdController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "/findId")
  public String findIdPage() {
    return "findId/findId";
  }

  @RequestMapping(value = "/findId", method = RequestMethod.POST)
  public String findId(MemberDTO memberDTO, Model model) {

    System.out.println("FindIdController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("findId");

    MemberDTO result = memberService.selectOne(memberDTO);

    if (result == null) {
      System.out.println("FindIdController Out로그 = [" + result + "]");
      return "findId/findIdResult";
    }

    model.addAttribute("memberId", result.getMemberId());

    System.out.println("FindIdController Out로그 = [" + result + "]");

    return "findId/findIdResult";

  }

}
