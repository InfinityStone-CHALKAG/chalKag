package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangePwController {

  @Autowired
  public MemberService memberService;

  @RequestMapping(value = "/changePw", method = RequestMethod.GET)
  public String changePwPage() {
    return "changePw";
  }

  @RequestMapping(value = "/changePw", method = RequestMethod.POST)
  public String changePw(MemberDTO memberDTO, @RequestParam("newPw") String newPw, HttpSession session) {

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setMemberPw(newPw);
    memberDTO.setSearchCondition("changePw");

    System.out.println("ChangePwController In로그");

    if (!memberService.update(memberDTO)) {
      System.out.println("[로그] Controller");
      return "redirect:error";
    }

    session.invalidate();

    System.out.println("ChangePwController Out로그");

    return "redirect:main";
  }
}
