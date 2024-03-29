package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberPageController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "memberPage")
  public String memberPage(MemberDTO memberDTO, Model model, HttpSession session) {

    System.out.println("MemberPageController In로그 + [" + memberDTO + "]");

    if (memberDTO.getMemberId().equals(session.getAttribute("member"))) {
      return "redirect:myPage";
    }

    memberDTO.setSearchCondition("myPage");

    MemberDTO result = memberService.selectOne(memberDTO);

    model.addAttribute("memberInfo", result);

    System.out.println("MemberPageController Out로그");

    return "myPage/memberPage";
  }

}
