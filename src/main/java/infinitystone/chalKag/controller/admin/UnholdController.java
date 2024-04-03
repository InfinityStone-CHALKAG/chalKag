package infinitystone.chalKag.controller.admin;

import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnholdController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "/unHold")
  public String unHold() {

    return null;
  }

}
