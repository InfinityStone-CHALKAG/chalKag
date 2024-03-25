package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;

@Controller
public class CheckIdController {

  @Autowired
  private MemberService memberService;

  @RequestMapping("/checkId")
  public @ResponseBody int checkId(MemberDTO memberDTO) {
    System.out.println("CheckIdController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("checkId");

    MemberDTO result = memberService.selectOne(memberDTO);
    if (result == null) {
      System.out.println("CheckIdController Out로그");
      return 1;
    }
    System.out.println("CheckIdController Out로그");
    return 0;
  }

}
