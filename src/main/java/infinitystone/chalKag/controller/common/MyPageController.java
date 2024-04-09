package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.review.ReviewDTO;
import infinitystone.chalKag.biz.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class MyPageController {

  @Autowired
  private MemberService memberService;
  @Autowired
  private ReviewService reviewService;
  @Autowired
  private Gson gson;

  @RequestMapping("/myPage")
  public String myPage(MemberDTO memberDTO, ReviewDTO reviewDTO, HttpSession session, Model model) {

    memberDTO.setMemberId(session.getAttribute("member").toString());
    memberDTO.setSearchCondition("myPage");

    System.out.println("MyPageController In로그 = [" + memberDTO + "]");

    MemberDTO result = memberService.selectOne(memberDTO);

    model.addAttribute("memberInfo", result);

    reviewDTO.setReviewPartner(memberDTO.getMemberId());
    reviewDTO.setReviewStart(0);
    reviewDTO.setReviewCnt(10);
    
    model.addAttribute("reviewList", reviewService.selectAll(reviewDTO));
    
    System.out.println("MyPageController Out로그 = [" + result + "]");
    return "myPage/myPage";

  }

}
