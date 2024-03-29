package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

  @Autowired
  private MemberService memberService;

  @RequestMapping("/myPage")
  public String myPage(MemberDTO memberDTO, HttpSession session, Model model) {

    memberDTO.setMemberId(session.getAttribute("member").toString());
    memberDTO.setSearchCondition("myPage");

    System.out.println("MyPageController In로그 = [" + memberDTO + "]");

    MemberDTO result = memberService.selectOne(memberDTO);

    model.addAttribute("memberInfo", result);

    System.out.println("MyPageController Out로그 = [" + result + "]");

    return "myPage/myPage";

  }

}
