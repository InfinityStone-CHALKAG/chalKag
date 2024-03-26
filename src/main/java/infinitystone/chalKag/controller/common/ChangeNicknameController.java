package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChangeNicknameController {

  @Autowired
  public MemberService memberService;

  @RequestMapping(value = "/changeNickname", method = RequestMethod.GET)
  public String changeNicknamePage(MemberDTO memberDTO, Model model, HttpSession session) {
    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("myPage");

    model.addAttribute("memberInfo", memberService.selectOne(memberDTO));

    return "changeNickname";
  }

  @RequestMapping(value = "/changeNickname", method = RequestMethod.POST)
  public String changeNickname(MemberDTO memberDTO, HttpSession session) {

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("changeNickname");

    System.out.println("ChangeNicknameController In로그");

    if (!memberService.update(memberDTO)) {
      System.out.println("[로그] Controller");
      return "redirect:error";
    }

    System.out.println("ChangeNicknameController Out로그");

    return "redirect:myPage";
  }
}
