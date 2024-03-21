package infinitystone.chalkag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalkag.biz.member.MemberDTO;
import infinitystone.chalkag.biz.member.MemberService;

import java.util.List;

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
      System.out.println("CheckIdController Out로그 = [" + result + "]");
      return 1;
    }
    System.out.println("CheckIdController Out로그 = [" + result + "]");
    return 0;
  }

}
