package infinitystone.chalKag.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.review.ReviewDTO;
import infinitystone.chalKag.biz.review.ReviewService;

@Controller
public class UpdateReviewController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value="/updateReview", method=RequestMethod.POST)
	public @ResponseBody ReviewDTO updatereview2(ReviewDTO reviewDTO) {
		
		boolean reviewUpdateResult = reviewService.update(reviewDTO);
		
		if(reviewUpdateResult) {
			System.out.println("[UpdatereviewController] 리뷰 수정 성공");
			return reviewDTO;
		}
		System.out.println("[UpdatereviewController] 리뷰 수정 실패");
		return null;
	}
	
	 @RequestMapping(value = "moreReview")
	  public @ResponseBody String moreReview(ReviewDTO reviewDTO) {

		    System.out.println("UpdatereviewController In로그 + [" + reviewDTO + "]");
		  
		    // 멤버페이지 이동시 출력 할 리뷰 

		    reviewDTO.setReviewPartner(reviewDTO.getReviewPartner());
		    reviewDTO.setReviewStart(reviewDTO.getReviewStart());
		    reviewDTO.setReviewCnt(10);
		    
	 	    return gson.toJson(reviewService.selectAll(reviewDTO));      
		  }
	
	
	
}
