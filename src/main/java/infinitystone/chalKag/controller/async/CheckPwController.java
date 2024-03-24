package infinitystone.chalKag.controller.async;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckPwController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "/checkPw")
  public @ResponseBody int checkPw(MemberDTO memberDTO, HttpSession session) {

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("checkPw");

    MemberDTO result = memberService.selectOne(memberDTO);

    if (result.getMemberPw() == memberDTO.getMemberPw()) {
      System.out.println("CheckPwController Out로그");
      return 1;
    }

    System.out.println("CheckPwController Out로그");
    return 0;

  }

}
