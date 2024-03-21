package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChangePhoneNumberController {

  @Autowired
  public MemberService memberService;

}
