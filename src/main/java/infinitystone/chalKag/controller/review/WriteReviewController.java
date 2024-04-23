package infinitystone.chalKag.controller.review;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.review.ReviewDTO;
import infinitystone.chalKag.biz.review.ReviewService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WriteReviewController {

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private MemberService memberService;

  @Autowired
  private Gson gson;

  @RequestMapping(value = "writeReview")
  public @ResponseBody String writeReview(ReviewDTO reviewDTO, MemberDTO memberDTO, HttpSession session) {

    System.out.println("WriteController In로그 = [" + reviewDTO + "]");

    reviewDTO.setMemberId((String) session.getAttribute("member"));

    reviewService.insert(reviewDTO);

    if (reviewDTO.getReviewScore().equals("5")) {
      memberDTO.setSearchCondition("get5StarExp");
      memberDTO.setMemberId(reviewDTO.getReviewPartner());
      memberService.update(memberDTO);
    } else if (reviewDTO.getReviewScore().equals("4") || reviewDTO.getReviewScore().equals("4.5")) {
      memberDTO.setSearchCondition("get4StarExp");
      memberDTO.setMemberId(reviewDTO.getReviewPartner());
      memberService.update(memberDTO);
    }

    return gson.toJson(reviewService.selectOne(reviewDTO));
  }

}