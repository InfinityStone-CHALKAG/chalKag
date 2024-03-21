package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.signinlog.SignInLogDTO;
import infinitystone.chalKag.biz.signinlog.SignInLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SignInController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private SignInLogService signInLogService;

  @RequestMapping(value = "/signIn", method = RequestMethod.GET)
  public String signInPage() {
    return "signIn";
  }

  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  public @ResponseBody int signIn(MemberDTO memberDTO, SignInLogDTO signInLogDTO, HttpSession session) {

    System.out.println("SignInController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("signIn");

    MemberDTO result = memberService.selectOne(memberDTO);

    if (result == null) {
      return 0;
    }
    if (result.getMemberGrade().equals("ADMIN")) {
      return 2;
    }

    session.setAttribute("member", result.getMemberId());
    session.setAttribute("memberGrade", result.getMemberGrade());
    signInLogService.insert(signInLogDTO);
    System.out.println("SignInController Out로그 = [" + result + "]");
    return 1;
  }
}
