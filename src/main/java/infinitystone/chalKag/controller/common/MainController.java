package infinitystone.chalKag.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;

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

  @Autowired
  private RecommendService recommendService;
  
  @RequestMapping("/main")
  public String main(MemberDTO memberDTO, HeadHuntPostDTO headHuntPostDTO, Model model, HttpSession session,
		  MarketPostDTO marketPostDTO,JobHuntPostDTO jobHuntPostDTO,FreePostDTO freePostDTO, RecommendDTO recommendDTO) {

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

    // 메인페이지 사이드 바 프리미엄 회원의 구인글
    headHuntPostDTO.setSearchCondition("headHuntPostPremiumList");
    List<HeadHuntPostDTO> headHuntPostPremiumList = headHuntPostService.selectAll(headHuntPostDTO);
    model.addAttribute("headHuntPostPremiumList", headHuntPostPremiumList);

    // 메인페이지 최신 구인글
    headHuntPostDTO.setSearchCondition("headHuntPostRecentPostSingle");
    HeadHuntPostDTO latestHeadHuntPost = headHuntPostService.selectOne(headHuntPostDTO);
    model.addAttribute("latestHeadHuntPost", latestHeadHuntPost);

    // 메인페이지 주간 베스트 글
    headHuntPostDTO.setSearchCondition("headHuntPostWeeklyBestList");
    model.addAttribute("headHuntPostWeeklyBestList", headHuntPostService.selectAll(headHuntPostDTO));
    
    // -------------------------------------------------
    
    
    // 메인페이지 사이드 바 프리미엄 회원의 구인글
    jobHuntPostDTO.setSearchCondition("jobHuntPostPremiumList");
    List<JobHuntPostDTO> jobHuntPostPremiumList = jobHuntPostService.selectAll(jobHuntPostDTO);
    model.addAttribute("jobHuntPostPremiumList", jobHuntPostPremiumList);

    // 메인페이지 최신 구인글
    jobHuntPostDTO.setSearchCondition("jobHuntPostRecentPostSingle");
    JobHuntPostDTO latestJobHuntPost = jobHuntPostService.selectOne(jobHuntPostDTO);
    model.addAttribute("latestJobHuntPost", latestJobHuntPost);

    // 메인페이지 주간 베스트 글
    jobHuntPostDTO.setSearchCondition("jobHuntPostWeeklyBestList");
    model.addAttribute("jobHuntPostWeeklyBestList", jobHuntPostService.selectAll(jobHuntPostDTO));
    
    
//-----------------------------------------------------
    
    // 메인페이지 사이드 바 프리미엄 회원의 구인글
    marketPostDTO.setSearchCondition("marketPostPremiumList");
    List<MarketPostDTO> marketPostPremiumList = marketPostService.selectAll(marketPostDTO);
    model.addAttribute("marketPostPremiumList", marketPostPremiumList);

    // 메인페이지 최신 구인글
    marketPostDTO.setSearchCondition("marketPostRecentPostSingle");
    MarketPostDTO latestMarketPost = marketPostService.selectOne(marketPostDTO);
    model.addAttribute("latestMarketPost", latestMarketPost);

    // 메인페이지 주간 베스트 글
    marketPostDTO.setSearchCondition("marketPostWeeklyBestList");
    model.addAttribute("marketPostWeeklyBestList", marketPostService.selectAll(marketPostDTO));
    
    //----------------------------------------------------
    
    // 메인페이지 사이드 바 프리미엄 회원의 구인글
    freePostDTO.setSearchCondition("freePostPremiumList");
    List<FreePostDTO> freePostPremiumList = freePostService.selectAll(freePostDTO);
    model.addAttribute("freePostPremiumList", freePostPremiumList);

    // 메인페이지 최신 구인글
    freePostDTO.setSearchCondition("freePostRecentPostSingle");
    FreePostDTO latestFreePost = freePostService.selectOne(freePostDTO);
    model.addAttribute("latestFreePost", latestFreePost);

    // 메인페이지 주간 베스트 글
    freePostDTO.setSearchCondition("freePostWeeklyBestList");
    model.addAttribute("freePostWeeklyBestList", freePostService.selectAll(freePostDTO));

    //----------------------------------------------------
    
    // 메인페이지 추천순 게시글 목록 출력 (게시판 통합. 8개 출력)
 
    recommendDTO.setSearchCondition("recommendBestList");
    List<RecommendDTO> recommendBestList = recommendService.selectAll(recommendDTO);
    model.addAttribute("recommendBestList", recommendBestList);
	
    System.out.println("MainController Out로그");
	
    return "common/main";
  }
}
