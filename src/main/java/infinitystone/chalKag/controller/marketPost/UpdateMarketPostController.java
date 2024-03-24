package infinitystone.chalKag.controller.marketPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;

@Controller
public class UpdateMarketPostController {
	@Autowired
	private MarketPostService marketPostService;
	
	@RequestMapping(value = "/updateMarketPost", method = RequestMethod.POST)
	public String writeMarketPost(MarketPostDTO marketPostDTO) {
		// 사용자의 글을 수정
		if(!marketPostService.delete(marketPostDTO)) {
			System.out.println("[UpdateMarketPostController] 수정 실패");
		}
		System.out.println("[UpdateMarketPostController] 수정 성공");
		return "marketPostSingle";
	}
}
