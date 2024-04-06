package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.review.ReviewDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberPageController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "memberPage")
  public String memberPage(MemberDTO memberDTO, ReviewDTO reviewDTO, Model model, HttpSession session) {

    System.out.println("MemberPageController In로그 + [" + memberDTO + "]");

    // 본인이 본인 페이지로 이동하고 싶어할 때는 마이페이지로 이동
    if (memberDTO.getMemberId().equals(session.getAttribute("member"))) {
      return "redirect:myPage";
    }

    // 페이지 이동시 출력 할 사용자의 정보
    memberDTO.setSearchCondition("myPage");

    MemberDTO result = memberService.selectOne(memberDTO);

    model.addAttribute("memberInfo", result);

    // 사용자에 대한 후기
  reviewDTO.getReviewPartner();

    System.out.println("MemberPageController Out로그");

    return "myPage/memberPage";
  }

}
