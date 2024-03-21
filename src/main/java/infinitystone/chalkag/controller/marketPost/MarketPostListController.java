package infinitystone.chalkag.controller.marketPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.marketPost.MarketPostDTO;
import infinitystone.chalkag.biz.marketPost.MarketPostService;

@Controller
public class MarketPostListController {
	
	@Autowired
	private MarketPostService marketPostService;
	
	@RequestMapping("/marketPostList")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO) {
		
		List<MarketPostDTO> marketPostDatas = marketPostService.selectAll(marketPostDTO);
		model.addAttribute("marketPostList",marketPostDatas);
		
		return "marketPostList";
	}
	
}
