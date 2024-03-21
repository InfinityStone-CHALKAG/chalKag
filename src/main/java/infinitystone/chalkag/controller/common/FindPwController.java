package infinitystone.chalkag.controller.common;

import infinitystone.chalkag.biz.member.MemberDTO;
import infinitystone.chalkag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FindPwController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "/findPw")
  public String findPwPage() {
    return "findPw/findPw";
  }

  @RequestMapping(value = "/findPw", method = RequestMethod.POST)
  public String findPw(MemberDTO memberDTO, Model model) {

    System.out.println("FindPwController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("findPw");

    MemberDTO result = memberService.selectOne(memberDTO);

    if (result == null) {
      System.out.println("FindPwController Out로그 = [" + result + "]");
      return "findPw/findPwResult";
    }

    model.addAttribute("memberPw", result.getMemberPw());

    System.out.println("FindIdController Out로그 = [" + result + "]");

    return "findPw/findPwResult";

  }

}
