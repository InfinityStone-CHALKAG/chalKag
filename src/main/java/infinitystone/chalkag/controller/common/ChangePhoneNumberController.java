package infinitystone.chalkag.controller.common;

import infinitystone.chalkag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChangePhoneNumberController {

  @Autowired
  public MemberService memberService;

}
