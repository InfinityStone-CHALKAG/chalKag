package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
  public String main(MemberDTO memberDTO, HeadHuntPostDTO headHuntPostDTO, Model model, HttpSession session) {

    System.out.println("MainController In로그");

    if (session.getAttribute("member") != null) {

      memberDTO.setSearchCondition("myPageSimple");
      memberDTO.setMemberId(session.getAttribute("member").toString());

      MemberDTO myPageSimpleResult = memberService.selectOne(memberDTO);

      model.addAttribute("memberInfo", myPageSimpleResult);

    }

    // 메인페이지 회원 레벨 순위 출력.안승준
    memberDTO.setSearchCondition("levelRank");
    List<MemberDTO> levelRankResult = memberService.selectAll(memberDTO);
    model.addAttribute("levelRank", levelRankResult);

    // 메인페이지 최신 구인글
    headHuntPostDTO.setSearchCondition("headHuntPostRecentPostSingle");
    HeadHuntPostDTO latestHeadHuntPost = headHuntPostService.selectOne(headHuntPostDTO);
    model.addAttribute("latestHeadHuntPost", latestHeadHuntPost);

    System.out.println("MainController Out로그");

    return "common/main";
  }
}
