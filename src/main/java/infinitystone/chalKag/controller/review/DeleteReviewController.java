package infinitystone.chalKag.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.review.ReviewDTO;
import infinitystone.chalKag.biz.review.ReviewService;

@Controller
public class DeleteReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/deleteReview", method=RequestMethod.POST)
	public @ResponseBody boolean deleteComment2(ReviewDTO reviewDTO) {
		return reviewService.delete(reviewDTO);
	}
}
