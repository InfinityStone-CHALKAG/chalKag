package infinitystone.chalKag.controller.review;

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
  private Gson gson;

  @RequestMapping(value = "writeReview")
  public @ResponseBody String writeReview(ReviewDTO reviewDTO, HttpSession session) {

    System.out.println("WriteController In로그 = [" + reviewDTO + "]");

    reviewDTO.setMemberId((String) session.getAttribute("member"));

    reviewService.insert(reviewDTO);
    
    return gson.toJson(reviewService.selectOne(reviewDTO)); 
  }

}