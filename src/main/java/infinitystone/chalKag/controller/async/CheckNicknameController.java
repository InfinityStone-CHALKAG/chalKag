package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;

@Controller
public class CheckNicknameController {

  @Autowired
  private MemberService memberService;

  @RequestMapping("/checkNickname")
  public @ResponseBody int checkNickname(MemberDTO memberDTO) {
    System.out.println("CheckNicknameController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("checkNickname");

    MemberDTO result = memberService.selectOne(memberDTO);
    if (result == null) {
      System.out.println("CheckNicknameController Out로그 = [" + result + "]");
      return 1;
    }
    System.out.println("CheckNicknameController Out로그 = [" + result + "]");
    return 0;
  }

}
