package infinitystone.chalKag.controller.async;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;

@Controller
public class CheckPhController {

  @Autowired
  private MemberService memberService;

  @RequestMapping("/checkPh")
  public @ResponseBody int checkPw(MemberDTO memberDTO) {

    System.out.println("CheckPhController In로그 = [" + memberDTO + "]");

    memberDTO.setSearchCondition("checkPh");

    MemberDTO result = memberService.selectOne(memberDTO);
    if (result == null) {
      System.out.println("CheckPhController Out로그");
      return 1;
    }
    System.out.println("CheckPhController Out로그");
    return 0;

  }

}
