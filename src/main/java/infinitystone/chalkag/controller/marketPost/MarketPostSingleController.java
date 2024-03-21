package infinitystone.chalkag.controller.marketPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.marketPost.MarketPostDTO;
import infinitystone.chalkag.biz.marketPost.MarketPostService;

@Controller
public class MarketPostSingleController {
	
	@Autowired
	private MarketPostService marketPostService;
	
	@RequestMapping("/marketPostSingle")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO) {

		// marketPostDTO에 있는 정보로 게시글 내용 불러오기
		
		marketPostDTO = marketPostService.selectOne(marketPostDTO);
		
		model.addAttribute("marketPostSingle",marketPostDTO);
		
		return "marketPostSingle";
	}
	
}
