package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChangePhController {

  @Autowired
  public MemberService memberService;

  @RequestMapping(value = "/changePh", method = RequestMethod.GET)
  public String changePhPage() {
    return "changePh";
  }

  @RequestMapping(value = "/changePh", method = RequestMethod.POST)
  public String changePh(MemberDTO memberDTO, HttpSession session) {

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("changePh");

    System.out.println("ChangePhController In로그 = [" + memberDTO + "]");

    if (!memberService.update(memberDTO)) {
      System.out.println("[로그] Controller");
      return "redirect:error";
    }

    System.out.println("ChangePhController Out로그");

    return "redirect:myPage";
  }
}
