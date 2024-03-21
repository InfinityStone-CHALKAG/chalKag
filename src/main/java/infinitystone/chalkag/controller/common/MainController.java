package infinitystone.chalkag.controller.common;

import infinitystone.chalkag.biz.freePost.FreePostService;
import infinitystone.chalkag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalkag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalkag.biz.marketPost.MarketPostService;
import infinitystone.chalkag.biz.member.MemberDTO;
import infinitystone.chalkag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @Autowired
  private JobHuntPostService jobHuntPostService;

  @Autowired
  private MarketPostService marketPostService;

  @Autowired
  private FreePostService freePostService;

  @RequestMapping("/main")
  public String main(MemberDTO memberDTO, Model model, HttpSession session) {

    System.out.println("MainController In로그");

    if (session.getAttribute("member") != null) {

      memberDTO.setSearchCondition("myPageSimple");
      memberDTO.setMemberId(session.getAttribute("member").toString());

      MemberDTO myPageSimpleResult = memberService.selectOne(memberDTO);

      model.addAttribute("memberInfo", myPageSimpleResult);

      System.out.println("MainController Out로그 = [" + myPageSimpleResult + "]");

    }

    System.out.println("MainController Out로그");

    return "main";
  }
}
