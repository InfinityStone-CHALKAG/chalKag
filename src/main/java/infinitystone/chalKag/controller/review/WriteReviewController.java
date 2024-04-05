package infinitystone.chalKag.controller.review;

import infinitystone.chalKag.biz.review.ReviewDTO;
import infinitystone.chalKag.biz.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WriteReviewController {

  @Autowired
  private ReviewService reviewService;

  @RequestMapping(value = "writeReview")
  public @ResponseBody int writeReview(ReviewDTO reviewDTO, HttpSession session, Model model) {

    System.out.println("WriteController In로그 = [" + reviewDTO + "]");

    reviewDTO.setMemberId((String) session.getAttribute("member"));

    if (!reviewService.insert(reviewDTO)) {
      return 0;
    }

    model.addAttribute("review", reviewService.selectOne(reviewDTO));

    return 1;
  }

}